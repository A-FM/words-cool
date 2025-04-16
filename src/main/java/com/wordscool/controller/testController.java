package com.wordscool.controller;

import com.wordscool.service.TPermissionService;
import com.wordscool.service.TUserService;
import com.wordscool.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    private final TUserService tUserService;
    private final TPermissionService tPermissionService;

    @Autowired
    public testController(TUserService tUserService, TPermissionService tPermissionService) {
        this.tUserService = tUserService;
        this.tPermissionService = tPermissionService;
    }

    @GetMapping("test")
    public String test() {
        System.out.println(tUserService.getUserByUsernameOrEmail("x-power"));
        RedisUtils.putObj("hello", "world");
        System.out.println(RedisUtils.getObj("hello"));
        return "test";
    }
}

