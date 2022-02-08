<%--
  Created by IntelliJ IDEA.
  User: bekzod5939
  Date: 07/02/22
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>

<%
    for (Cookie cookie : request.getCookies())
        if(cookie.getName().equals("phone")){
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
%>

<div class="container-fluid bg-dark p-5">
    <div class="row h-70">
        <div class="col-md-10">
            <h1 class="text-success">T E L E G R A M</h1>
        </div>
        <div class="col-md-2 float-right">
            <div class="row">
                <form action="/api/auth" class="mr-2" method="get">
                    <button class="btn btn-success">SIGN IN</button>
                </form>
                <form action="/api/auth" class="mr-2" method="post">
                    <button class="btn btn-primary">SIGN UP</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
