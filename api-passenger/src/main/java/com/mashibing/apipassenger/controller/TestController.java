package com.mashibing.apipassenger.controller;

import com.mashibing.internalcommon.dto.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "test api passenger";
    }
    public ResponseResult authTest(){
        return ResponseResult.success("auth test");
    }
    public ResponseResult noauthTest(){
        return ResponseResult.success("noauth test");
    }
}
