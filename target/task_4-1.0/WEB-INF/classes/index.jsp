<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ilya
  Date: 16.02.2021
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<html>
<head>
    <title>Start Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/login.css" type="text/css"/>
</head>
<body>
<div>
    <c:redirect url="/auth.jhtml">q</c:redirect>
</div>
</body>
</html>