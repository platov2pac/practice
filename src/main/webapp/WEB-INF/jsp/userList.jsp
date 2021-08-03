<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>

<%--
  Created by IntelliJ IDEA.
  User: Ilya
  Date: 23.02.2021
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users List</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <c:url var="contpath" value="/"/>
    <link rel="stylesheet" href="${contpath}style/general.css"/>
    <link rel="stylesheet" href="${contpath}style/userList.css"/>
</head>
<body>
<div class="main">
    <myTags:header login="${login}"
                   mainLink="${contpath}welcome.jhtml"
                   userListLink="${contpath}listUsers.jhtml"
                   logoutLink="${contpath}logout.jhtml"
                   roles="${roles}"/>
    <div class="content">
        <table class="main-table">
            <caption>
                <p>Список пользователей
                    <a href="${contpath}edituser.jhtml">
                        <span class="material-icons"
                              style="color: black; font-size: 32px; height: 32px">person_add</span>
                    </a>
                </p>
            </caption>

            <thead>
            <tr>
                <th>
                    <p>Логин</p>
                </th>
                <th><p>Роль</p></th>
                <th><p>Почта</p></th>
                <th><p>Дата рождения</p></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${requestScope.users}">
                <tr>
                    <td>${user.getLogin()}</td>

                    <td>
                        <c:forEach var="roleName" items=" ${user.getRole()}">
                            ${roleName}
                        </c:forEach>
                    </td>
                    <td>${user.getEmail()}</td>
                    <td>${user.getDob()}</td>
                    <td><a href="${contpath}edituser.jhtml?loginUser=${user.getLogin()}">
                        <span class="material-icons" style="color: black">create</span></a>
                        <a href="${contpath}deleteUser.jhtml?deletableLogin=${user.getLogin()}">
                            <span class="material-icons" style="color: black">delete</span></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <footer>footer</footer>
</div>
</body>
</html>
