package com.example.book_recommender.SecurityHandler;


import com.example.book_recommender.beans.AjaxResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
     ObjectMapper mapper = new ObjectMapper();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();

        String s = mapper.writeValueAsString(AjaxResult.success("登录成功"));
        out.write(s);
        out.flush();
        out.close();

    }
}
