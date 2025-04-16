package com.wordscool.security;

import com.wordscool.entity.TUser;
import com.wordscool.service.TPermissionService;
import com.wordscool.service.TUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Component
public class UserDetailServiceImpl implements UserDetailsService {

    private final TUserService tUserService;
    private final TPermissionService tPermissionService;

    public UserDetailServiceImpl(TUserService tUserService,TPermissionService tPermissionService) {
        this.tUserService = tUserService;
        this.tPermissionService =tPermissionService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TUser user = tUserService.getUserByUsernameOrEmail(username);
        if (user != null) {
            List<String> permissionCode = tPermissionService.getPermissionCodeByUserId(user.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            permissionCode.forEach(code->{
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(code);
                grantedAuthorities.add(simpleGrantedAuthority);
            });
            return new User(user.getUsername(),user.getPassword(),user.getEnabled(),user.getAccountNonExpired(),user.getCredentialsNonExpired(),user.getAccountNonLocked(),grantedAuthorities);
        }else {
            throw new UsernameNotFoundException("用户名不存在或密码不存在");
        }
    }
}
