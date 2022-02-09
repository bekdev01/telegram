<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="uz.pdp.payload.UserDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.utils.Word" %>
<%@ page import="org.bson.Document" %>
<%@ page import="uz.pdp.payload.UserMessageDTO" %>
<%@ page import="uz.pdp.payload.Response" %>
<%@ page import="uz.pdp.payload.MessageDTO" %>
<%@ page import="uz.pdp.model.Message" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <title>Title</title>

    <style>
        ul {
            padding: 0;
            list-style: none;
            background: #f2f2f2;
        }

        ul li {
            display: inline-block;
            position: relative;
            line-height: 21px;
            text-align: left;
        }

        ul li a {
            display: block;
            padding: 8px 25px;
            color: #ffffff;
            text-decoration: none;
        }

        ul li a:hover {
            color: #fff;
            background: #939393;
        }

        ul li ul.dropdown-menu {
            min-width: 100%; /* Set width of the dropdown */
            background: #f2f2f2;
            display: none;
            position: absolute;
            z-index: 999;
            left: 0;
        }

        ul li ul.dropdown-menu li {
            display: block;
        }
    </style>
</head>
<body>

<%
    UserMessageDTO userMessageDTO = (UserMessageDTO) request.getSession().getAttribute(Word.MESSAGE_LIST);
%>





<div class="container-fluid bg-white p-5 border h-100">
    <div class="row h-100 bg-light">
        <div class="chats col-4 order p">
            <div class="card h-100">
                <div class="card-header h-25">
                    <div class="row navbar navbar-light input-group bg-light p-0 m-0"
                         style="display: flex; justify-content: center; align-items: center">
                        <div class="input-group-prepend dropdown col-2 m-0">
                            <button class="navbar-toggler" type="button" data-toggle="collapse"
                                    data-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent"
                                    aria-expanded="false" aria-label="Toggle navigation">
                                <span class="navbar-toggler-icon"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a class="btn btn-outline-dark border-0" href="/api/me">Me</a></li>
                                <br>
                                <li><a class="btn btn-outline-dark border-0" href="/api/settings">Settings</a></li><br>
                                <li><a class="btn btn-outline-dark border-0" href="/">Log out</a></li>
                            </ul>
                        </div>
                        <form class="form-inline col-10 m-0" action="get_user" method="get">
                            <div class="row">
                                <div class="col-md-12">
                                    <input class="form-control" type="search" placeholder="Search" aria-label="Search">
                                </div>
                            </div>
                        </form>
                    </div>
                    <ul class="nav nav-tabs navbar-light rounded mt-2" id="myTab">
                        <li class="nav-item">
                            <a href="#main" class="nav-link btn btn-outline-success show active" data-bs-toggle="tab">All chats</a>
                        </li>
                        <li class="nav-item">
                            <a href="#profile" class="nav-link btn btn-outline-success" data-bs-toggle="tab">Personal</a>
                        </li>
                        <li class="nav-item">
                            <a href="#groups" class="nav-link btn btn-outline-success" data-bs-toggle="tab">Groups</a>
                        </li>
                    </ul>
                </div>
                <div class="card-body h-75" style="overflow-y:auto;">
                    <div class="m-3" >
                        <div class="tab-content mt-1" style="overflow-y:auto;">
                            <div class="tab-pane fade show active"  id="main">
                                <ul class="list-group" >
                                    <c:forEach items="<%=userMessageDTO.getUsers()%>" var="user">
                                        <a href="chat?id=${user.id}" class="btn btn-outline-success text-left my-1" >
                                            <li class="list-group-item list-group-item-action" style="overflow-y:auto;">
                                                <h6 class="p-0 my-0">${user.fullName}</h6>
                                                <p class="p-0 my-0 small text-muted">${user.phone}</p>
                                            </li>
                                        </a>
                                    </c:forEach>
                                </ul>
                            </div>
                            <div class="tab-pane fade" id="profile">
                                <h4 class="mt-2">Profile tab content</h4>


                            </div>
                            <div class="tab-pane fade" id="groups">
                                <h4 class="mt-2">Group tab content</h4>


                            </div>

                        </div>
                    </div>
                </div>
            </div>


        </div>


        <div class="messages  col-8 order p-1 w-100 h-100">
            <div class="card h-100">
                <div class="card-header h-25">
                    <h1 class="text-success">TELEGRAM</h1>
                </div>
                <div class="card-body h-50" style="overflow-y:auto;">


                        <c:forEach items="<%=userMessageDTO.getMessages().getMessageUser()%>" var="message">
                            <li class="list-group-item list-group-item-action border-0 ${message.mine?"text-right":"text-left"}">
                                <p class="p-0 my-0">${message.text}</p>
                                <p class="p-0 my-0 small text-muted"><i>${message.date.substring(11,19)}</i></p>
                            </li>
                        </c:forEach>
                </div>
                <div class="card-footer h-25">
                    <form action="/api/chat?id=<%=userMessageDTO.getMessages().getId()%>" method="post">
                        <input class=" p-3 form-control mt-5" type="text" placeholder="Message" name="text" required>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>


<script>
    $(document).ready(function () {
        $("#myTab a:first").tab("show");
    });

    $(document).ready(function () {
        // Show hide popover
        $(".dropdown").click(function () {
            $(this).find(".dropdown-menu").slideToggle("fast");
        });
    });
    $(document).on("click", function (event) {
        var $trigger = $(".dropdown");
        if ($trigger !== event.target && !$trigger.has(event.target).length) {
            $(".dropdown-menu").slideUp("fast");
        }
    });

</script>
</body>
</html>