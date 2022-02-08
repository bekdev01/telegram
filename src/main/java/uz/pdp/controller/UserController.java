package uz.pdp.controller;

import database.user.UserDatabase;
import model.User;
import org.bson.Document;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/user")
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String last_name = "";
        String email = req.getParameter("email");
        String password = req.getParameter("password");


        UserDatabase userDatabase = new UserDatabase();

        Document user = userDatabase.addUser(new User(name, last_name, email, password));

        if(user != null) {
//            Cookie cookie = new Cookie("user", user.toJson());
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(30);

            resp.sendRedirect("/profile");
        } else {
            resp.sendRedirect("/");
        }
    }
}


