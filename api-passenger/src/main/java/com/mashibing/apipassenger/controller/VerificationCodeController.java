package com.mashibing.apipassenger.controller;

import com.mashibing.apipassenger.request.VerificationCodeDTO;
import com.mashibing.apipassenger.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationCodeController {
    @Autowired
    private VerificationCodeService verificationCodeService;
    @GetMapping("/verification-code")
    public String verificationCode(@RequestBody VerificationCodeDTO verficationCodeDTO){
        String passengerPhone = verficationCodeDTO.getPassengerPhone();
        System.out.println("接收到的手机号参数："+passengerPhone);
        return verificationCodeService.generateCode(passengerPhone);
    }
}
