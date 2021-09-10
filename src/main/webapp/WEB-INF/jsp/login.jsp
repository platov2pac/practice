<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Ilya
  Date: 16.02.2021
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%--<fmt:setLocale value="${lang}"/>--%>
<%--<fmt:setBundle basename="lang.messages"/>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<myTags:head namePage="login"/>
<spring:message code="label.login" var="i18nLogin"/>
<spring:message code="label.password" var="i18nPassword"/>
<sec:authorize var="username"/>
<body>
<div class="loginForm">
    <div class="signup">
        <h1><spring:message code="label.signup"/></h1>
    </div>
    <c:if test="${param.error==true}">
        <div class="errorsStyle"><spring:message code="login.password.wrong"/> </div>
        <c:set value="inputError" var="error"/>
    </c:if>
    <form method="post" action="/login">
        <label>
            <input type="text" placeholder="${i18nLogin}" name="login" class="${error}" value="${sessionScope.SPRING_SECURITY_LAST_USERNAME}"/>

            <input type="password" placeholder="${i18nPassword}" name="pass" class="${error}"/>
            <button type="submit">login</button>
            <div class="selectLang">
                <a href="lang?=en">en</a>
                <a href="?lang=ru">ru</a>
            </div>
        </label>
    </form>
</div>
</body>
</html>
