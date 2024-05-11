package com.mashibing.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    //盐
    private static final String SIGN="CPFmsb!@#$$";
    //生成token
    public static String generateToken(Map<String,String> map){
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        Date date=calendar.getTime();
        JWTCreator.Builder builder= JWT.create();
        //整合map
        map.forEach(
                (key,value)->{
                    builder.withClaim(key,value);
                }
        );
        //整合过期时间
        builder.withExpiresAt(date);
        //生成token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));
        return sign;
    }
    //解析token
    public static void main(String[] args) {
        Map<String,String> map=new HashMap<>();
        map.put("name","张三");
        map.put("age","18");
        String s = generateToken(map);
        System.out.println("生成的token"+s);
    }
}
