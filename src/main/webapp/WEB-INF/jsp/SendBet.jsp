<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/login.css"/>"/>
</head>

<%@ include file="blocks/header.jsp" %>

<h1>Send bet</h1>

<div class="box">
    <sf:form method="POST" modelAttribute="pendingBet" action="/sendbet">
        <input type="hidden" value="${username}" name="sender">
        <legend>Name of bet</legend>
        <input type="text" placeholder="Bet name" name="title"/>
        <legend>Description</legend>
        <input type="text" placeholder="Description" name="description"/>

        <legend>Oppenent</legend>
        <input type="text" placeholder="Opponent" name="receiver">

        <legend>Your Odds</legend>
        <input id="your-odds" type="number" placeholder="Your Odds" name="oddsSender">
        <legend>Oppenent-odds</legend>
        <p id="opponent-odds"></p>

        <input type="submit" VALUE="Send bet"/>

    </sf:form>
</div>
</html>

<script>
    var yourOdds = document.getElementById("your-odds");
    var oppOdds = document.getElementById("opponent-odds");

    yourOdds.addEventListener("keyup", calcOdds);

    function calcOdds(e){
        console.log(e.target.value);
        var odds = e.target.value;
        //todo if brwoser not support number in input
        oppOdds.innerHTML = 4.0/parseFloat(odds);
        console.log(oppOdds);
        console.log(odds);
        console.log(4.0/parseFloat(odds));
    }
</script>