<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<body>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/login.css"/>"/>
</head>

<%@ include file="blocks/header.jsp" %>

<body>
<h1>Send bet</h1>

<div class="box">
    <sf:form method="POST" modelAttribute="pendingBet" action="/sendbet" autocomplete="off">
        <input type="hidden" value="${username}" name="sender">
        <legend>Name of bet</legend>
        <input type="text" placeholder="Bet name" name="title"/>
        <legend>Description</legend>
        <input type="text" placeholder="Description" name="description"/>

        <%--date and time virka ekki a ollum browserum t.d. firefox!!!!!!!--%>
        <lengend>Date and time for bet to be resolved</lengend>
        <legend>The format dd-MON-yyyyThh:mm</legend>
        <input type="datetime-local" name="dateAndTimeResolve">
        <legend>Opponent</legend>
        <div id="users-dropdown">
            <input type="text" id="opponent" placeholder="Opponent" name="receiver">
        </div>


        <c:set var="gottUserNAME" value='${pageContext.request.userPrincipal.name}' />
        <% System.out.println(pageContext.findAttribute("gottUserNAME") ); %>
        <c:forEach items="${users}" var="user">
            <c:set var="gottUserNAME" value='${user.getUsername()}' />
            <% System.out.println(pageContext.findAttribute("gottUserNAME") ); %>
            <c:if test="${pageContext.request.userPrincipal.name != user.getUsername()}">
                <span class="get-users" hidden>${user.getUsername()}</span>
            </c:if>
        </c:forEach>


        <legend>Your Amount</legend>
        <input id="your-amount" type="number" step="0.001" placeholder="Your amount" name="amountSender">

        <legend>Your Odds</legend>
        <input id="your-odds" type="number" step="0.001" placeholder="Your Odds" name="oddsSender">
        <span id="your-odds-percentage"></span>

        <span>Opponent-odds</span>
        <span id="opponent-odds"></span>
        <br>
        <br>

        <span>Opponent-amount</span>
        <span id="opponent-amount"></span>


        <input id="pending-bet-button" type="submit" VALUE="Send bet"/>

    </sf:form>
</div>
</body>
<script src="<c:url value="/javascript/autofillbox.js" />"></script>
<script src="<c:url value="/javascript/calcOdds.js" />"></script>
</html>
