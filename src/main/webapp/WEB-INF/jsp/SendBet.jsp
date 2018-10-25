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
    <sf:form method="POST" modelAttribute="pendingBet" action="/sendbet" autocomplete="off">
        <input type="hidden" value="${username}" name="sender">
        <legend>Name of bet</legend>
        <input type="text" placeholder="Bet name" name="title"/>
        <legend>Description</legend>
        <input type="text" placeholder="Description" name="description"/>

        <legend>Opponent</legend>
        <div id="users-dropdown">
            <input type="text" id="opponent" placeholder="Opponent" name="receiver">
        </div>

        <c:forEach items="${users}" var="user">
            <span class="get-users" hidden>${user.getUsername()}</span>
        </c:forEach>


        <legend>Your Amount</legend>
        <input id="your-amount" type="number" step="0.001" placeholder="Your amount" name="amountSender">

        <legend>Your Odds</legend>
        <input id="your-odds" type="number" step="0.001" placeholder="Your Odds" name="oddsSender">
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



    //dropdown fyrir opponent users
    var getUsers = document.getElementsByClassName("get-users");
    var opponent = document.getElementById("opponent");
    opponent.addEventListener("keyup", opponentDropdown);
    opponent.style.marginBottom = "0";
    opponent.style.paddingBottom = "0";

    var usersDropdown = document.getElementById("users-dropdown");


    var users = [];
    for(var i=0;i<getUsers.length;i++){
        users.push(getUsers[i].innerHTML);
    }
    function opponentDropdown(e){
        closeDropdown();

        var a = this.value;
        a = document.createElement("DIV");
        a.setAttribute("id", "autocomplete-list");
        a.setAttribute("class", "autocomplete-items");

        a.style.backgroundColor = "white";
        a.style.height = "auto";
        a.style.width = "inherit";


        this.parentNode.appendChild(a);


        //users that match the input
        var matchUsers = [];




        for(var i=0; i<users.length; i++){
            if(e.target.value.toUpperCase() === users[i].substring(0, e.target.value.length).toUpperCase() && e.target.value.length > 0){
                matchUsers.push(users[i]);
            }
        }
        for(var i=0; i<matchUsers.length; i++){
            var item = document.createElement("DIV");
            item.setAttribute("class", "autocomplete-text")

            item.innerHTML = "<b>" + matchUsers[i].substr(0,e.target.value.length) + "</b>";
            item.innerHTML += matchUsers[i].substr(e.target.value.length);
            //item.innerHTML = matchUsers[i];

            //item.value = matchUsers[i];
            item.innerHTML += "<input type='hidden' value='" + matchUsers[i] + "'>";
            item.addEventListener("click", function(e) {

                console.log(e);
                console.log(this)
                opponent.value = this.getElementsByTagName("input")[0].value;
                closeDropdown();
            });
            a.appendChild(item);
        }
    }

    function closeDropdown(e){
        var x = document.getElementsByClassName("autocomplete-items");
        for(var i=0; i<x.length;i++){
            x[i].parentNode.removeChild(x[i]);
        }
    }
</script>