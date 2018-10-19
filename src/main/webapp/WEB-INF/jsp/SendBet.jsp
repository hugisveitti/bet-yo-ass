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

        <legend>Opponent</legend>
        <input type="text" placeholder="Opponent" name="receiver">

        <legend>Credit Amount</legend>
        <input id="your-amount" type="number" placeholder="Credit amount" name="amount">

        <legend>Your Odds</legend>
        <input id="your-odds" type="number" placeholder="Your Odds" name="oddsSender">

        <legend>Opponent-odds</legend>
        <p id="opponent-odds"></p>

        <legend>Opponent-amount</legend>
        <p id="opponent-amount"></p>


        <input type="submit" VALUE="Send bet"/>

    </sf:form>
</div>
</html>

<script>
    var yourOdds = document.getElementById("your-odds");
    var oppOdds = document.getElementById("opponent-odds");

    var yourAmount = document.getElementById("your-amount");
    var oppAmount = document.getElementById("opponent-amount");


    yourOdds.addEventListener("keyup", calcOdds);
    yourAmount.addEventListener("keyup", calcAmount);



    function calcOdds(e){
        var odds = e.target.value;
        //todo if brwoser not support number in input



        oppOdds.innerHTML = 4.0/parseFloat(odds);
        console.log(yourAmount)
        console.log(oppOdds);
        console.log(odds);

        oppAmount.innerHTML = yourAmount.value*odds/parseFloat(oppOdds.innerHTML);
    }

    function calcAmount(e){
        oppAmount.innerHTML = yourAmount.value*odds/parseFloat(oppOdds.innerHTML);
    }

</script>