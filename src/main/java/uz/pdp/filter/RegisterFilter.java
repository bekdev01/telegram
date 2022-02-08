//package uz.pdp.filter;
//
//import uz.pdp.database.user.UserDatabase;
//import uz.pdp.utils.Word;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter(filterName = "regFilter", urlPatterns = "/*")
//public class RegisterFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//        if(!req.getRequestURI().equals("/api/auth"))
//        for (Cookie cookie : req.getCookies())
//            if (cookie.getName().equals("phone")) {
//                if (UserDatabase.getUserByPhone(cookie.getValue()) == null){
//                    cookie.setMaxAge(0);
//                    resp.addCookie(cookie);
//                    req.getSession().setAttribute(Word.ERROR_TEXT,"YOU HAVE NO ANY PERMISSION");
//                    req.getRequestDispatcher("/errorPage.jsp").forward(req, resp);
//                    return;
//                }
//                break;
//            }
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//}
