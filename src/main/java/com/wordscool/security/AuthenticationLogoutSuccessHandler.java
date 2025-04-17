package com.wordscool.security;

import com.wordscool.utils.BaseResult;
import com.wordscool.utils.RedisUtils;
import com.wordscool.utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        try {
            RedisUtils.removeObj(request.getHeader("Authorization"));
        }catch (IllegalArgumentException e){
            System.out.println("属于是查无此值。");
        }
        ResponseUtil.out(response,new BaseResult(HttpStatus.OK,"登出成功~",null));
    }
}
