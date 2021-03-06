<%@tag pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@attribute name="login" required="true" %>
<%@attribute name="mainLink" required="true" %>
<%@attribute name="userListLink" required="true" %>
<%@attribute name="logoutLink" required="true" %>
<%@attribute name="roles" required="true" type="java.util.List" %>
<header>
    <p>AdmApp</p>
    <div class="nav-bar">
        <form method="get" action="${mainLink}">
            <button type="submit" class="main-button">Главная</button>
        </form>

        <c:forEach var="role" items="${roles}">

            <c:if test="${role.getName() == 'admin'}">
                <form method='get' action="${userListLink}">
                    <button type="submit" class="list-users">Все пользователи</button>
                </form>
            </c:if>
        </c:forEach>
    </div>
    <div class="user-logout">
        <p>${login}</p>
        <form method="get" action="${logoutLink}">
            <button type="submit" id="logout">logout</button>
        </form>
    </div>
</header>