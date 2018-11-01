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

<body>
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
        <p><i>Receiver odds</i> ${pendingBet.oddsReceiver}</p>
        <p><i>Receiver amount</i> ${pendingBet.amountReceiver}</p>
        <p><i>Sender odds</i> ${pendingBet.oddsSender}</p>
        <p><i>Sender amount</i> ${pendingBet.amountSender}</p>
        <p><i>Date and time created</i> ${pendingBet.dateAndTimeCreated}</p>
        <p><i>Date and time for bet to be resolved</i> ${pendingBet.dateAndTimeResolve}</p>

        <c:choose>
            <c:when test="${pendingBet.sender == user.getUsername()}">
                <c:choose>
                    <c:when test="${pendingBet.acceptSender == true}">
                        You are the sender. You are waiting for other user to respond the bet.
                    </c:when>
                    <c:otherwise>
                        You are the sender. You have to respond to the bet.
                        <sf:form method="POST" action="/accept-pending-bet">
                            <input type="hidden" name="pendingBetId" value="${pendingBet.id}">
                            <input type="submit" value="Accept bet">
                        </sf:form>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${pendingBet.acceptReceiver == true}">
                        You are the receiver. You are waiting for other user to respond the bet.
                    </c:when>
                    <c:otherwise>
                        You are the receiver. You have to respond to the bet
                        <sf:form method="POST" action="/accept-pending-bet">
                            <input type="hidden" name="pendingBetId" value="${pendingBet.id}">
                            <input type="submit" value="Accept bet">
                        </sf:form>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </div>
</c:forEach>

<br>
<h3>Active bets</h3>
<c:forEach items="${activeBets}" var="activeBet">
    <div class="box">
        <h4><i>Title of bet</i> ${activeBet.title}</h4>
        <p><i>Description:</i> ${activeBet.description}</p>
        <p><i>Sender:</i> ${activeBet.sender}</p>
        <p><i>Receiver</i> ${activeBet.receiver}</p>
    </div>
</c:forEach>

<br>
<h3>Resolved bets</h3>
<c:forEach items="${resolvedBets}" var="resolvedBet">
    <div class="box">
        <h4><i>Title of bet</i> ${resolvedBet.title}</h4>
        <p><i>Description:</i> ${resolvedBet.description}</p>
        <p><i>Sender:</i> ${resolvedBet.sender}</p>
        <p><i>Receiver</i> ${resolvedBet.receiver}</p>
    </div>
</c:forEach>



<br>
Logged in as ${user.getUsername()}
<br>
You have ${user.getCredit()} credits


<br>
<br>
</body>
</html>