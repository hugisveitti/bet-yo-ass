<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<html>
<head>
    <%@ page isELIgnored="false" %>

    <link rel="stylesheet" type="text/css" href="<c:url value="/css/login.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>"/>
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
            <c:when test="${(pendingBet.sender == user.getUsername() and pendingBet.acceptSender)  or (pendingBet.receiver == user.getUsername() and pendingBet.acceptReceiver)}">
                You are waiting for other user to respond the bet.
            </c:when>
            <c:otherwise>
                You have to respond to the bet
                <sf:form method="POST" action="/accept-pending-bet">
                    <input type="hidden" name="pendingBetId" value="${pendingBet.id}">
                    <input type="submit" value="Accept bet">
                </sf:form>

                <sf:form method="POST" action="/decline-pending-bet">
                    <input type="hidden" name="pendingBetId" value="${pendingBet.id}">
                    <input type="submit" value="Decline">
                </sf:form>


                <button class="counter-btn" onclick="openCounter(${pendingBet.id})">Send counter offer</button>
                <sf:form method="POST" action="/make-counter-bet" class="counter-form ${pendingBet.id}" id="counter-form${pendingBet.id}">
                    <legend>Your Amount</legend>
                    <input id="your-amount" type="number" step="0.001" placeholder="Your amount" name="amountSender">

                    <legend>Your Odds</legend>
                    <input id="your-odds" type="number" step="0.001" placeholder="Your Odds" name="oddsSender">
                    <span>Opponent-odds</span>
                    <span id="opponent-odds"></span>
                    <br>
                    <br>

                    <span>Opponent-amount</span>
                    <span id="opponent-amount"></span>
                    <input type="hidden" name="pendingBetId" value="${pendingBet.id}">
                    <input type="submit" value="Make counter offer">
                </sf:form>
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

<script src="<c:url value="/javascript/calcOdds.js" />"></script>

<script>
    //to open counter offer form
    function openCounter(pendingBetId){
        console.log(pendingBetId);
        var form = document.getElementById("counter-form" + pendingBetId);
        console.log(form)
        form.style.display = "block";

    }
</script>