package com.changgou.test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Date;

public class TestBcrypt {

    public static void main(String[] args) {


        JwtBuilder builder= Jwts.builder().
                //设置唯一编号
                setId("888").
                //设置主题可以是JSON数据
                setSubject("吕布").
                //设置签发日期
                setIssuedAt(new Date()).
                setExpiration(new Date()).
                //设置签名 使用HS256算法， 并设置SecretKey(字符串)
                signWith(SignatureAlgorithm.HS256,"OceansWu");;
        //构建 并返回一个字符串
        System.out.println( builder.compact() );


        String compactJwt = builder.compact();
        Claims claims = Jwts.parser().setSigningKey("OceansWu").parseClaimsJws(compactJwt).getBody( );
        System.out.println(claims);


//        for(int i=0;i<10;i++){
//            //获取盐
//            String gensalt = BCrypt.gensalt();
//            System.out.println("盐:"+gensalt);
//            //基于当前的盐对密码进行加密
//            String saltPassword = BCrypt.hashpw("123456", gensalt);
//            System.out.println("加密后的密文:"+saltPassword);
//
//            //解密
//            boolean checkpw = BCrypt.checkpw("123456", saltPassword);
//            System.out.println("密码校验结果:"+checkpw);
//        }

    }
}
