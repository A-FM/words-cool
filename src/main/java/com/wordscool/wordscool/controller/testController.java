package com.wordscool.wordscool.controller;

import com.wordscool.wordscool.utils.RedisUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
    @GetMapping("test")
    public String test() {
        RedisUtils.putObj("hello", "world");
        System.out.println(RedisUtils.getObj("hello"));
        return "test";
    }
}

