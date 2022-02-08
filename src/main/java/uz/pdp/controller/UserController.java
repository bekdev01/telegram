package uz.pdp.controller;

import org.bson.Document;
import uz.pdp.database.user.UserDatabase;
import uz.pdp.model.User;
import uz.pdp.payload.Response;
import uz.pdp.payload.UserDTO;
import uz.pdp.payload.UserMessageDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static uz.pdp.utils.Word.*;

@WebServlet("/api/user")
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        Document user = UserDatabase.getUserByPhoneAndPassword(phone,password);
        if(user==null){
            req.getSession().setAttribute(ERROR_TEXT, "PASSWORD OR/AND PHONE NUMBER IS/ARE WRONG!");
            req.getRequestDispatcher("/errorPage.jsp").forward(req, resp);
            return;
        }
        Cookie cookie = new Cookie("phone", user.get("phone").toString());
        cookie.setMaxAge(86400);
        resp.addCookie(cookie);
        List<UserDTO> userList = UserDatabase.getUserList();
        userList.removeIf(item-> item.getPhone().equals(phone));
        req.getSession().setAttribute(MESSAGE_LIST, new UserMessageDTO(userList,null));
        req.getRequestDispatcher("/user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");

        Document user = UserDatabase.addUser(new User(firstname, lastname, phone, password));
        if (user == null) {
            req.getSession().setAttribute(ERROR_TEXT, "THIS PHONE NUMBER ALREADY EXIST!");
            req.getRequestDispatcher("/errorPage.jsp").include(req, resp);
            return;
        }
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}


