package com.example.book_recommender.SecurityHandler;


import com.example.book_recommender.beans.AjaxResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class UserAuthenticationFailureHandler implements AuthenticationFailureHandler {
    ObjectMapper mapper = new ObjectMapper();
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse httpServletResponse, AuthenticationException exception) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();

        String s = mapper.writeValueAsString(AjaxResult.error("登录失败"));
        out.write(s);
        out.flush();
        out.close();
    }
}
