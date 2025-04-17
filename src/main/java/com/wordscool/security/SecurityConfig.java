package com.wordscool.security;

import com.wordscool.entity.TPermission;
import com.wordscool.service.TPermissionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.cors.CorsUtils;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Value("${login.in}")
    private String loginUrl;
    @Value("${login.out}")
    private String logoutUrl;

    private final TPermissionService tPermissionService;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailHandler authenticationFailHandler;
    private final AuthenticationLogoutSuccessHandler authenticationLogoutSuccessHandler;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;


    public SecurityConfig(TPermissionService tPermissionService, AuthenticationSuccessHandler authenticationSuccessHandler, AuthenticationFailHandler authenticationFailHandler, AuthenticationLogoutSuccessHandler authenticationLogoutSuccessHandler, CustomAccessDeniedHandler customAccessDeniedHandler) {
        this.tPermissionService = tPermissionService;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailHandler = authenticationFailHandler;
        this.authenticationLogoutSuccessHandler = authenticationLogoutSuccessHandler;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 使用 BCrypt 加密
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        // 从数据库加载动态权限配置
        List<TPermission> tPermissions = tPermissionService.selectAll();

        http
                .authorizeHttpRequests(authorizeRequests -> {
                    // 允许跨域预检请求
                    authorizeRequests.requestMatchers(CorsUtils::isPreFlightRequest).permitAll();

                    // 登录和登出路径允许匿名访问
                    authorizeRequests.requestMatchers(loginUrl, logoutUrl).permitAll();

                    // 动态加载数据库中的权限配置
                    tPermissions.forEach(permission ->
                            authorizeRequests.requestMatchers(permission.getUrl()).hasAnyAuthority(permission.getCode())
                    );

                    // 其他所有请求需要认证
                    authorizeRequests.anyRequest().authenticated();
                })
                .formLogin(form ->
                        form.loginProcessingUrl(loginUrl)
                                .successHandler(authenticationSuccessHandler)
                                .failureHandler(authenticationFailHandler)
                )
                .logout(logout ->
                        logout.logoutUrl(logoutUrl)
                                .logoutSuccessHandler(authenticationLogoutSuccessHandler)
                )
                .addFilter(new JWTAuthorizationFilter(authenticationManager))
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .csrf(csrf ->
                        csrf.csrfTokenRepository(customCsrfTokenRepository()).requireCsrfProtectionMatcher(new CsrfSecurityRequestMatcher())
                ).exceptionHandling(exception ->exception.accessDeniedHandler(customAccessDeniedHandler));

        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public CsrfTokenRepository customCsrfTokenRepository() {
        // 使用 withHttpOnlyFalse() 方法创建实例øø
        CookieCsrfTokenRepository repository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        repository.setHeaderName("X-CSRF-TOKEN"); // 设置请求头字段名称
        repository.setCookieName("CSRF-TOKEN");   // 设置Cookie名称
        return repository;
    }
}