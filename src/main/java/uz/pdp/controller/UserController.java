package uz.pdp.controller;

import org.bson.Document;
import uz.pdp.database.user.UserDatabase;
import uz.pdp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static uz.pdp.utils.Word.ERROR_TEXT;

@WebServlet("/api/user")
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");

        Document user = UserDatabase.addUser(new User(firstname, lastname, phone, password));
        if (user == null) {
//            req.getSession().setAttribute(ERROR_TEXT,"THIS EMAIL ALREADY EXIST!");
            req.getRequestDispatcher("errorPage.jsp").include(req,resp);
            return;
        }
//        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }
}


