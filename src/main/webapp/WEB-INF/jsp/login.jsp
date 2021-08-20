<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Ilya
  Date: 16.02.2021
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="lang.messages"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<myTags:head namePage="login"/>
<body>
<div class="loginForm">
    <div class="signup">
        <h1><fmt:message key="label.signup"/></h1>
    </div>
    <c:if test="${authFailed}">
        <div style="color: red">Error</div>
    </c:if>
    <form:form method="post" action="${contpath}auth.jhtml" modelAttribute="loginForm">
        <label>
            <form:errors path="login" cssClass="errorsStyle"/>
            <form:input type="text" placeholder= path="login" value="${login}" cssErrorClass="inputError"/>
            <form:errors path="password" cssClass="errorsStyle"/>
            <form:input type="password" placeholder="Password" path="password" value="${password}"
                        cssErrorClass="inputError"/>
            <button type="submit">login</button>
        </label>
    </form:form>
</div>
</body>
</html>
