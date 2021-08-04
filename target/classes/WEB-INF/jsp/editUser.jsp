<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="myTags" tagdir="/WEB-INF/tags" %>
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
    <myTags:header login="${login}"
                   mainLink="${contpath}welcome.jhtml"
                   userListLink="${contpath}listUsers.jhtml"
                   logoutLink="${contpath}logout.jhtml"
                   roles="${roles}"/>
    <div class="content">
        <form class="editForm" method="post" action="${contpath}edituser.jhtml">
            <fieldset <c:if test="${param.editFailed}"> style="border:1px solid red"</c:if>>
                <legend>
                    <c:if test="${loginUser==null}">
                        <p>Вы добавляете пользователя</p>
                    </c:if>
                    <c:if test="${loginUser!=null}">
                        <p>Вы изменяете пользователя ${loginUser}</p>
                    </c:if>

                    <c:if test="${param.editFailed}">
                        <div class="error" style="color: red">Ошибки в полях: ${errors}
                        </div>
                    </c:if></legend>
                <label>
                    <input type="text" name="newLogin" value="${user.login}" placeholder="Login">
                    <c:if test="${loginUser==null}">
                        <input type="text" name="password" placeholder="Password">
                    </c:if>
                    <input type="text" name="email" value="${user.email}" placeholder="Email">
                    <input type="date" name="dob" value="${user.dob}" placeholder="Date of birthday">

                    <p><input type="checkbox" name="role" value="admin"
                    <c:forEach var="role" items="${user.roles}">
                    <c:if test="${role.name=='admin'}"> checked=checked </c:if>
                    </c:forEach>
                    > Admin</p>
                    <p><input type="checkbox" name="role" value="user"
                    <c:forEach var="role" items="${user.roles}">
                    <c:if test="${role.name=='user'}"> checked=checked </c:if>
                    </c:forEach>
                    > User</p>
                </label>
                <button type="submit" <c:if test="${loginUser!=null}"> name="loginUser" value="${loginUser}"</c:if>>
                    Внести изменения
                </button>
            </fieldset>
        </form>
    </div>
    <footer>footer</footer>
</div>
</body>
</html>
