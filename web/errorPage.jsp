<%@ page import="uz.pdp.utils.Word" %><%--
  Created by IntelliJ IDEA.
  User: bekzod5939
  Date: 08/02/22
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Title</title>
</head>
<body class="bg-default">
<div class="container p-5 bg-dark mt-5">
    <div class="row">
        <div class="col-md-4 offset-4 mt-5">
            <div class="card">
                <div class="card-body text-danger text-center">
                    <h3><%=request.getSession().getAttribute(Word.ERROR_TEXT)%></h3>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
