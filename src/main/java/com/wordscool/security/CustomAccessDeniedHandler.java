package com.wordscool.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        // 自定义处理权限拒绝的逻辑，可以返回自定义的错误信息或页面
        response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 设置HTTP状态为403 Forbidden
        response.getWriter().write("Access Denied - You don't have permission to access this resource.");
    }
}
