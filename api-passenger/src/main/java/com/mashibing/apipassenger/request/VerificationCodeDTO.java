package com.mashibing.apipassenger.request;

import org.springframework.web.util.pattern.PathPattern;

public class VerificationCodeDTO {
    private String passengerPhone;

    public String getPassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }
}
