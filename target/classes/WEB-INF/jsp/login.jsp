<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Ilya
  Date: 16.02.2021
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<myTags:head namePage="login"/>
<body>
<div class="loginForm">
    <div class="signup">
        <h1>Sign Up</h1>
    </div>
    <c:if test="${authFailed}">
        <div class="error" style="color: red">Wrong login or password</div>
    </c:if>
    <form method="post" action=<c:out value="${contpath}auth.jhtml"/>>
        <label>
            <input type="text" placeholder="Login" name="login"
                    <c:if test="${authFailed}"> value="${login}" </c:if>/>
            <input type="password" placeholder="Password" name="password"
                    <c:if test="${authFailed}"> value="${pass}" </c:if>/>
            <button type="submit">login</button>
        </label>
    </form>
</div>
</body>
</html>
