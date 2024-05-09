package com.mashibing.apipassenger.request;

import lombok.Data;
import org.springframework.web.util.pattern.PathPattern;
@Data
public class VerificationCodeDTO {
    private String passengerPhone;

    private String verificationCode;


}
