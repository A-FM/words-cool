package com.wordscool.utils;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ResponseUtil {
    public static void out(HttpServletResponse response, BaseResult baseResult) {

        response.setStatus(baseResult.getHttpStatus().value());
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(JacksonUtils.obj2jsonByte(baseResult));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
