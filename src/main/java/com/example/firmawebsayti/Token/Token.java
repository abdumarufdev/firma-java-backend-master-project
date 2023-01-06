package com.example.firmawebsayti.Token;

import com.example.firmawebsayti.Entity.Lavozim;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Token {
    public String key = "qwert";
    public  String tokin(String Username, Lavozim lavozimEntity){
        Integer vaqt=60*60*100*24;
        Date date=new Date(System.currentTimeMillis()+vaqt);
        String tokin = Jwts
                .builder()
                .setSubject(Username)
                .setIssuedAt(new Date())
                .setExpiration(date)
                .claim("ishchiEntity",lavozimEntity.getNomi())
                .signWith(SignatureAlgorithm.HS512,key)
                .compact();
        return tokin;
    }
    public boolean tokenCheck(String token){
        Jwts
                .parser()
                .setSigningKey(key)
                .parseClaimsJws(token);
        return true;
    }
    public String UsernameOlish(String auth){
        String subject = Jwts
                .parser()
                .setSigningKey(key)
                .parseClaimsJws(auth)
                .getBody()
                .getSubject();
        return subject;
    }


    public String userCheck(String auth) {
        return null;
    }
}
