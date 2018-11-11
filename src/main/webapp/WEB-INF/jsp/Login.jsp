<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

<head>
    <title>Login</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/css/button.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/login.css"/>"/>
    <link href="https://fonts.googleapis.com/css?family=Krub" rel="stylesheet">

</head>
<%@ include file="blocks/header.jsp" %>
<body>

<h1>Login</h1>
<div class="box">
    <sf:form method="POST" action="/login">
        <input type="text" name="username" placeholder="Username" />
        <input type="password" name="password" placeholder="Password" />
        <button class="button submit" type="submit">
            <span>
                Login
            </span>
        </button>
    </sf:form>
    <p class="no-account">Don't have an account? <a href="/signup">Signup here!</a></p>
    <div class="message">
        <p>${errorMsg}</p>
        <c:if test="${not empty param.error}">
            <p>Wrong username or password.</p>
        </c:if>
    </div>

</div>

</body>
</html>
