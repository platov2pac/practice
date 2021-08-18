<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<body>
<div class="main">
    <myTags:header login="${sessionLogin}"
                   mainLink="${contpath}welcome.jhtml"
                   userListLink="${contpath}listUsers.jhtml"
                   logoutLink="${contpath}logout.jhtml"
                   roles="${roles}"/>
    <div class="content">
        <form:form class="editForm" method="post" action="${contpath}edituser.jhtml" modelAttribute="user">
            <fieldset <c:if test="${editFailed}"> style="border:1px solid red"</c:if>>
                <legend>
                    <c:if test="${loginUser==null}">
                        <p>Вы добавляете пользователя</p>
                    </c:if>
                    <c:if test="${loginUser!=null}">
                        <p>Вы изменяете пользователя ${loginUser}</p>
                    </c:if>

                    <c:if test="${editFailed}">
                        <div class="error" style="color: red">Ошибки в полях: ${errors}
                        </div>
                    </c:if></legend>
                <label>
                    <form:input type="text" path="login" value="${login}" placeholder="Login"/>
                    <form:input type="password" path="password" placeholder="Password"/>
                    <form:input type="text" path="email" value="${email}" placeholder="Email"/>
                    <form:input type="date" path="dob" value="${dob}" placeholder="Date of birthday"/>
                    <form:checkboxes path="roles" items="${allRolesInApp}" itemLabel="name" itemValue="name" />

                </label>
                <button type="submit" <c:if test="${loginUser!=null}"> name="loginUser" value="${loginUser}"</c:if>>
                    Внести изменения
                </button>
            </fieldset>
        </form:form>
    </div>
    <footer>footer</footer>
</div>
</body>
</html>
