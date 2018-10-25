<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/login.css"/>"/>
</head>

<%@ include file="blocks/header.jsp" %>

<h1>Send bet</h1>

<div class="box">
    <sf:form onsubmit="testing()" method="POST" modelAttribute="pendingBet" action="/sendbet">
        <input type="hidden" value="${username}" name="sender">
        <legend>Name of bet</legend>
        <input type="text" placeholder="Bet name" name="title"/>
        <legend>Description</legend>
        <input type="text" placeholder="Description" name="description"/>

        <legend>Opponent</legend>
        <input type="text" placeholder="Opponent" name="receiver">

        <legend>Your Amount</legend>
        <input id="your-amount" type="number" step="any" placeholder="Your amount" name="amount">

        <legend>Your Odds</legend>
        <input id="your-odds" type="number" step="any" placeholder="Your Odds" name="oddsSender">
        <p id="your-odds-percentage"></p>

        </br>

        <p>Opponent-odds</p>
        <p class="opponent-show " id="opponent-odds"></p>

        <legend>Opponent-amount</legend>
        <p class="opponent-show" id="opponent-amount"></p>


        <input id="pending-bet-button" type="submit" VALUE="Send bet"/>

    </sf:form>
</div>
</html>

<script>
    var yourOdds = document.getElementById("your-odds");
    var oppOdds = document.getElementById("opponent-odds");
    var yourOddsPercentage = document.getElementById("your-odds-percentage");

    var yourAmount = document.getElementById("your-amount");
    var oppAmount = document.getElementById("opponent-amount");

    var submit = document.getElementById("pending-bet-button");

    yourOdds.addEventListener("keyup", calcOdds);
    yourAmount.addEventListener("keyup", calcAmount);


    function calcOdds(e) {

        if (yourAmount.value == "" || yourOdds.value == "") {
            oppOdds.className = "opponent-hidden";
            oppAmount.className = "opponent-hidden";
            return;
        }
        oppOdds.className = "opponent-show";
        oppAmount.className = "opponent-show";


        var odds = e.target.value;
        //todo if browser not support number in input

        var likur = Math.floor((1 / parseFloat(odds)) * 100 * 100) / 100;
        var oOdds = Math.floor((1 / (100.0 - likur)) * 100 * 100) / 100;

        console.log("you have " + likur + "% chance of winning");
        yourOddsPercentage.innerHTML = "you have " + likur + "% chance of winning";
        oppOdds.innerHTML = oOdds + " (" + (100 - likur) + "%)";
        oppAmount.innerHTML = Math.floor(parseFloat(yourAmount.value) * parseFloat(yourOdds.value) / parseFloat(oppOdds.innerHTML) * 100) /100;
    }

    function calcAmount(e) {
        console.log(yourAmount.value);
        console.log(yourOdds.value);

        if (yourAmount.value == "" || yourOdds.value == "") {
            oppOdds.className = "opponent-hidden";
            oppAmount.className = "opponent-hidden";
            return;
        }
        oppOdds.className = "opponent-show";
        oppAmount.className = "opponent-show";


        oppAmount.innerHTML =  Math.floor(parseFloat(yourAmount.value) * parseFloat(yourOdds.value) / parseFloat(oppOdds.innerHTML) *100)/100;
    }

</script>