package com.example.firmawebsayti.Token;

import com.example.firmawebsayti.Servise.IshchiServise;
import com.example.firmawebsayti.Token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class Filtr extends OncePerRequestFilter {
    @Autowired
    Token token;
    @Autowired
    IshchiServise ishchiServise;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String auth = request.getHeader("Auth");
        if (auth!=null){
            String a = token.UsernameOlish(auth);
            if(token!=null){
                System.out.println(auth);
                boolean s = token.tokenCheck(auth);
                if (s) {
                    UserDetails userDetails = ishchiServise.loadUserByUsername(a);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            else System.out.println("null");
        }
        filterChain.doFilter(request,response);
    }
}
