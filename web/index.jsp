<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.User" %><%--
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
<div class="container-fluid bg-dark p-5 ">
    <div class="row">
        <div class="col-md-4 offset-4 mt-5">
            <div class="card">
                <div class="card-header">
                    <h2 class="text-primary">Register</h2>
                </div>
                <div class="card-body">
                    <form action="/api/user" method="post">
                        <div class="form-group">
                            <input type="text" required class="form-control" name="name" placeholder="NAME">
                        </div>
                        <div class="form-group">
                            <input type="text" required class="form-control" name="email" placeholder="EMAIL">
                        </div>
                        <div class="form-group">
                            <input id="psw" type="password" required class="form-control" name="password" onkeyup="checkPassword()" placeholder="PASSWORD">
                        </div>
                        <div class="form-group">
                            <input id="prePsw" type="password" required class="form-control" onkeyup="checkPassword()" name="prePassword"
                                   placeholder="ENTER PASSWORD AGAIN">
                        </div>
                        <button id="register-btn" class="btn btn-primary float-right" >Save</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function checkPassword(){
        let psw=document.getElementById("psw")
        let prePsw=document.getElementById("prePsw")
        console.log(psw.value)
        console.log(prePsw.value)
        document.getElementById("register-btn").disabled=psw.value!==prePsw.value;
    }
</script>
</body>
</html>
