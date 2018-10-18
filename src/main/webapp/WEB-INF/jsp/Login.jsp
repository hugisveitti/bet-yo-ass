<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

<head>
    <title>Login</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/css/login.css"/>"/>
</head>
<body>

<h1><a href="/login">Login Page</a></h1>
<div class="box">
    <sf:form method="POST" modelAttribute="user" action="/login">
        <input type="text" name="username" placeholder="username" />
        <input type="password" name="password" placeholder="password" />
        <input type="submit" VALUE="Login"/>

    </sf:form>
    <p>${errorMsg}</p>
</div>

</body>
</html>
