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

<sf:form method="POST" modelAttribute="postitNote" action="/postit">



    <input type="submit" VALUE="Login"/>

</sf:form>


</body>
</html>
