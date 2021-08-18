<%@tag pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ attribute name="namePage" required="true" %>

<c:if test="${namePage=='login'}">
    <head>
        <title>Login</title>
        <c:url var="contpath" value="/"/>
        <link rel="stylesheet" href="${contpath}style/general.css"/>
        <link rel="stylesheet" href="${contpath}style/login.css"/>

        <c:if test="${authFailed}">
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
</c:if>

<c:if test="${namePage=='editUser'}">
    <head>
        <title>Edit</title>
        <c:url var="contpath" value="/"/>
        <link rel="stylesheet" href="${contpath}style/general.css"/>
        <link rel="stylesheet" href="${contpath}style/editUser.css"/>
    </head>
</c:if>

<c:if test="${namePage=='userList'}">
    <head>
        <title>Users List</title>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <c:url var="contpath" value="/"/>
        <link rel="stylesheet" href="${contpath}style/general.css"/>
        <link rel="stylesheet" href="${contpath}style/userList.css"/>
    </head>
</c:if>

<c:if test="${namePage=='welcome'}">
    <head>
        <title>Welcome</title>
        <c:url var="contpath" value="/"/>

        <link rel="stylesheet" href="${contpath}style/general.css"/>
        <link rel="stylesheet" href="${contpath}style/welcome.css"/>
    </head>
</c:if>