<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Ilya
  Date: 24.02.2021
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<myTags:head namePage="editUser"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="lang.messages"/>
<fmt:message key="label.login" var="i18nLogin"/>
<fmt:message key="label.password" var="i18nPassword"/>
<fmt:message key="label.email" var="i18nEmail"/>

<body>
<div class="main">
    <myTags:header login="${sessionLogin}"
                   userLang="${lang}"
                   mainLink="${contpath}welcome.jhtml"
                   userListLink="${contpath}listUsers.jhtml"
                   logoutLink="${contpath}logout.jhtml"
                   roles="${roles}"/>
    <div class="content">
        <form:form class="editForm" method="post" action="${contpath}edituser.jhtml" modelAttribute="user">
            <form:errors cssStyle="border:1px solid red" element="fieldset"/>
            <fieldset>
                <legend>
                    <c:if test="${loginUser==null}">
                        <p><fmt:message key="label.addUser"/> </p>
                    </c:if>
                    <c:if test="${loginUser!=null}">
                        <p><fmt:message key="label.editUser"/> ${loginUser}</p>
                    </c:if>
                </legend>
                <label>
                    <form:errors path="login" cssClass="errorsStyle" />
                    <form:input type="text" path="login" value="${login}" placeholder="${i18nLogin}" cssErrorClass="inputError"/>
                    <form:errors path="password" cssClass="errorsStyle"/>
                    <form:input type="password" path="password" placeholder="${i18nPassword}" cssErrorClass="inputError"/>
                    <form:errors path="email" cssClass="errorsStyle"/>
                    <form:input type="text" path="email" value="${email}" placeholder="${i18nEmail}" cssErrorClass="inputError"/>
                    <form:errors path="dob" cssClass="errorsStyle"/>
                    <form:input type="date" path="dob" value="${dob}" placeholder="Date of birthday" cssErrorClass="inputError" lang="en"/>
                    <form:errors path="roles" cssClass="errorsStyle"/>
                    <form:checkboxes path="roles" items="${allRolesInApp}" itemLabel="name" itemValue="name"/>

                </label>
                <button type="submit" <c:if test="${loginUser!=null}"> name="loginUser" value="${loginUser}"</c:if>>
                    <fmt:message key="label.acceptChanges"/>
                </button>
            </fieldset>
        </form:form>
    </div>
    <footer>footer</footer>
</div>
</body>
</html>
