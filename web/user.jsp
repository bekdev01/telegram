<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2/8/2022
  Time: 10:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>

    <%
        List<User> userList = (List<User>) request.getSession().getAttribute("users");

    %>
    <div class="container">
        <div class="row">
            <div class="col-4">
                <h1 class="ml-4 mb-4 ">Telegram</h1>

                <ul class="list-group">

                    <li href="#" class="list-group-item list-group-item-action active">
                        alisher
                    </li>
                    <li href="#" class="list-group-item list-group-item-action">
                        Alisher2
                    </li>
                    <li href="#" class="list-group-item list-group-item-action">
                        Alisher4
                    </li>
<%--                    <c:forEach items="<%=userList%>" var="user">--%>
<%--                        <a href="#" class="list-group-item list-group-item-action active">--%>
<%--                            ${user.firstName}--%>
<%--                        </a>--%>
<%--                        <span>${user.lastName} </span>--%>
<%--                    </c:forEach>--%>


                </ul>

            </div>
            <div class="col-8">
                <div class="">

                </div>


            </div>

        </div>


    </div>




</body>
</html>
