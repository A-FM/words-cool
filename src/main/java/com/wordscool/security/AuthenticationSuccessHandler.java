package com.wordscool.security;

import com.wordscool.utils.BaseResult;
import com.wordscool.utils.BaseUtils;
import com.wordscool.utils.RedisUtils;
import com.wordscool.utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Value("${login.state.timeout}")
    private Integer loginTimeout;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = BaseUtils.getUUIDTime();
        RedisUtils.putObj(token, authentication,loginTimeout,TimeUnit.SECONDS);
        response.setHeader(RedisUtils.TOKEN_HEAD, token);
        ResponseUtil.out(response, new BaseResult(HttpStatus.OK, "ok", token));
    }
}
