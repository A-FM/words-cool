package com.wordscool.security;

import com.wordscool.utils.BaseResult;
import com.wordscool.utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
            ResponseUtil.out(response, new BaseResult(HttpStatus.UNAUTHORIZED,"用户名或密码错误！",null));
        } else if (exception instanceof LockedException) {
            ResponseUtil.out(response, new BaseResult(HttpStatus.UNAUTHORIZED,"账号被锁定！",null));
        } else if (exception instanceof CredentialsExpiredException) {
            ResponseUtil.out(response, new BaseResult(HttpStatus.UNAUTHORIZED,"密码已过期！",null));
        } else if (exception instanceof AccountExpiredException) {
            ResponseUtil.out(response, new BaseResult(HttpStatus.UNAUTHORIZED,"账号已过期！",null));
        } else if (exception instanceof DisabledException) {
            ResponseUtil.out(response, new BaseResult(HttpStatus.UNAUTHORIZED,"账户被禁用，请联系管理员",null));
        } else {
            ResponseUtil.out(response, new BaseResult(HttpStatus.UNAUTHORIZED,"未知原因造成的登陆失败，请联系管理员！yuanchongyang@foxmail.com",null));
        }
    }
}
