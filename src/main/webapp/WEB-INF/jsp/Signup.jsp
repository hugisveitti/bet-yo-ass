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

<h1><a href="/signup">Signup Page</a></h1>

<div class="box">
<sf:form method="POST" modelAttribute="user" action="/signup">
    <input type="text" placeholder="Username" name="username">
    <input type="password" placeholder="Password" name="password">

    <input type="submit" VALUE="Login"/>

</sf:form>
</div>

</body>
</html>
