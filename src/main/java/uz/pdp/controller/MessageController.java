package uz.pdp.controller;

import uz.pdp.database.user.UserDatabase;
import uz.pdp.payload.UserDTO;
import uz.pdp.payload.Response;
import uz.pdp.payload.UserMessageDTO;
import uz.pdp.utils.Word;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/chat")
public class MessageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String myPhone = "";
        for (Cookie cookie : req.getCookies())
            if (cookie.getName().equals("phone")) {
                myPhone = cookie.getValue();
                break;
            }
        String toId = req.getParameter("id");
        List<UserDTO> userList = UserDatabase.getUserList();
        String finalMyPhone = myPhone;
        userList.removeIf(item -> item.getPhone().equals(finalMyPhone));
        Response messages = UserDatabase.getMessageByPhone(myPhone, toId);
        if(messages==null)messages=new Response(null,toId);
        req.getSession().setAttribute(Word.MESSAGE_LIST, new UserMessageDTO(userList, messages));

        req.getRequestDispatcher("/userFull.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String myPhone = "";
        for (Cookie cookie : req.getCookies())
            if (cookie.getName().equals("phone")) {
                myPhone = cookie.getValue();
                break;
            }
        String text = req.getParameter("text");
        String toId = req.getParameter("id");
        UserDatabase.addMessageByPhone(myPhone,toId,text);
        List<UserDTO> userList = UserDatabase.getUserList();
        String finalMyPhone = myPhone;
        userList.removeIf(item -> item.getPhone().equals(finalMyPhone));
        Response messages = UserDatabase.getMessageByPhone(myPhone, req.getParameter("id"));
        req.getSession().setAttribute(Word.MESSAGE_LIST, new UserMessageDTO(userList, messages));
        req.getRequestDispatcher(messages==null?"/user.jsp":"/userFull.jsp").forward(req, resp);
    }
}
