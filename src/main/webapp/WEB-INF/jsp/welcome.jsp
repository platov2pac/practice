<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Ilya
  Date: 16.02.2021
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <c:url var="contpath" value="/"/>

    <link rel="stylesheet" href="${contpath}style/general.css"/>
    <link rel="stylesheet" href="${contpath}style/welcome.css"/>
</head>
<body>

<div class="main">
    ${sessionScope.roles1}
    <myTags:header login="${sessionScope.login}"
                   mainLink="${contpath}welcome.jhtml"
                   userListLink="${contpath}listUsers.jhtml"
                   logoutLink="${contpath}logout.jhtml"
                   roles="${sessionScope.roles}"/>
    <div class="content">
        <p class="app-description">Привет, уважаемый ${sessionScope.login}. Ты попал на главную страницу приложения.
            Здесь ты можешь изменить свой пароль или перемещаться по другим страницам, если у тебя есть к ним
            доступ.</p>
        <form method="post" action="${contpath}editpassword.jhtml" id="form-edit-pass">
            <label>
                <input type="password" placeholder="new password" name="newPassword">
            </label>
            <button class="editPass" type="submit">edit password</button>
        </form>
    </div>

<%--    </myTags:header>--%>
    <footer>footer</footer>
</div>


</body>
</html>
