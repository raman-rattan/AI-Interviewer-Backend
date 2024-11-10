package com.aiinterviewer.interviewSession.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JwtFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String header = request.getHeader("Authorization");

        if(header == null || !header.startsWith("Bearer")){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.println("Missing or Invalid Token");
        }
        else {
            String token = header.substring(7);
            Claims claims = Jwts.parser().setSigningKey("MySecretKeyForUserAuthenticationOfAIInterviewer").build().parseClaimsJws(token).getBody();
            request.setAttribute("claims", claims);
            filterChain.doFilter(request, response);
        }
    }
}
