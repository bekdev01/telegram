package uz.pdp.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "regFilter", urlPatterns = "/*")
public class RegisterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String reqMethod = req.getMethod();
        if(reqMethod.equals("post")) {
            String email = req.getParameter("email");

            for (Cookie cookie : req.getCookies()) {
                if(cookie.getName().equals("email") && cookie.getValue().equals("777")) {
                    break;
                }
            }


        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
