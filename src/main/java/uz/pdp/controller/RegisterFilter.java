package uz.pdp.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "regFilter", urlPatterns = "/api/user")
public class RegisterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String reqMethod = req.getMethod();
        if(reqMethod.equals("POST")) {
            String email = req.getParameter("email");

            for (Cookie cookie : req.getCookies()) {
                if(cookie.getName().equals("email") && cookie.getValue().equals(email)) {
                    break;
                }
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }
}
