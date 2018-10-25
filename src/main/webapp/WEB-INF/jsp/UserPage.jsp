<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<html>
<head>
    <%@ page isELIgnored="false" %>
</head>

<%@ include file="blocks/header.jsp" %>

<h1>USERPAGE</h1>


<br>
Logged in as <sec:authentication property="principal.username" />
</html>