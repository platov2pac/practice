<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ilya
  Date: 16.02.2021
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <c:url var="contpath" value="/"/>
    <link rel="stylesheet" href="${contpath}style/general.css"/>
    <link rel="stylesheet" href="${contpath}style/login.css"/>

    <c:if test="${param.authFailed}">
        <style>
            input {
                border-bottom: 5px solid rgba(250, 0, 0, 0.73);
                color: rgba(250, 0, 0, 0.73);
            }

            input::placeholder {
                color: rgba(250, 0, 0, 0.73);
            }

            input:focus {
                border-bottom: 5px solid rgba(250, 0, 0, 1);
            }
        </style>
    </c:if>
</head>
<body>
<div class="loginForm">
    <div class="signup">
        <h1>Sign Up</h1>
    </div>
    <c:if test="${param.authFailed}">
        <div class="error" style="color: red">Wrong login or password</div>
    </c:if>
    <form method="post" action=<c:out value="${contpath}auth.jhtml"/>>
        <label>
            <input type="text" placeholder="Login" name="login"
                    <c:if test="${param.authFailed}"> value="${login}" </c:if>/>
            <input type="password" placeholder="Password" name="pass"
                    <c:if test="${param.authFailed}"> value="${pass}" </c:if>/>
            <button type="submit">login</button>
        </label>
    </form>
</div>
</body>
</html>
