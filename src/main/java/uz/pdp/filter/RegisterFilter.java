package uz.pdp.filter;

import uz.pdp.database.user.UserDatabase;
import uz.pdp.utils.Word;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "regFilter", urlPatterns = "/*")
public class RegisterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        boolean found=false;
        Cookie temp=new Cookie("phone","");
        if(!req.getRequestURI().equals("/api/auth") && (req.getCookies()!=null && req.getCookies().length!=0)){
            for (Cookie cookie : req.getCookies())
                if (cookie.getName().equals("phone")) {
                    found=true;
                    temp=cookie;
                    if (UserDatabase.getUserByPhone(cookie.getValue()) == null){
                        cookie.setMaxAge(0);
                        resp.addCookie(cookie);
                        req.getSession().setAttribute(Word.ERROR_TEXT,"YOU HAVE NO ANY PERMISSION");
                        req.getRequestDispatcher("/errorPage.jsp").forward(req, resp);
                        return;
                    }
                    break;
                }
        }else found=true;
        if(req.getRequestURI().equals("/api/user"))found=true;
        if(found)
        filterChain.doFilter(servletRequest, servletResponse);
        else {
            temp.setMaxAge(0);
            resp.addCookie(temp);
            req.getRequestDispatcher("/").forward(req, resp);
        }
    }
}
