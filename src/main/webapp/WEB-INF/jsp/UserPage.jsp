<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<html>
<head>
    <%@ page isELIgnored="false" %>

    <link rel="stylesheet" type="text/css" href="<c:url value="/css/login.css"/>"/>
</head>

<%@ include file="blocks/header.jsp" %>

<h1>USERPAGE</h1>

<c:choose>
    <c:when test="${pendingBets != null}">
        <h3>Pending Bets</h3>
    </c:when>
    <c:otherwise>
        <h5>You have no pending bets</h5>
    </c:otherwise>
</c:choose>

<c:forEach items="${pendingBets}" var="pendingBet">
    <div class="box">
        <h4><i>Title of bet</i> ${pendingBet.title}</h4>
        <p><i>Description:</i> ${pendingBet.description}</p>
        <p><i>Sender:</i> ${pendingBet.sender}</p>
        <p><i>Receiver</i> ${pendingBet.receiver}</p>
    </div>
</c:forEach>

<br>
Logged in as <sec:authentication property="principal.username" />
</html>