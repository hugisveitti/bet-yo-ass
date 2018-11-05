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
    <div class="bet" id="pending-bet${pendingBet.id}">
        <div class="bet-main-info" id="bet-more-info${pendingBet.id}">
            <h4>${pendingBet.title}</h4>
            <p>${pendingBet.sender} vs. ${pendingBet.receiver}</p>
        </div>
        <div class="bet-more-info" id="bet-more-info${pendingBet.id}">
            <p><i>Description:</i> ${pendingBet.description}</p>
            <p><i>Receiver odds</i> ${pendingBet.oddsReceiver}</p>
            <p><i>Receiver amount</i> ${pendingBet.amountReceiver}</p>
            <p><i>Sender odds</i> ${pendingBet.oddsSender}</p>
            <p><i>Sender amount</i> ${pendingBet.amountSender}</p>
            <p><i>Date and time created</i> ${pendingBet.dateAndTimeCreated}</p>
            <p><i>Date and time for bet to be resolved</i> ${pendingBet.dateAndTimeResolve}</p>
            <c:choose>
                <c:when test="${(pendingBet.sender == user.getUsername() and pendingBet.acceptSender) or (pendingBet.receiver == user.getUsername() and pendingBet.acceptReceiver)}">
                    You are waiting for other user to respond the bet.
                </c:when>
                <c:otherwise>
                    <p>You have to respond to the bet</p>

                    <sf:form method="POST" action="/accept-pending-bet">
                        <input type="hidden" name="pendingBetId" value="${pendingBet.id}">
                        <input type="submit" value="Accept bet">
                    </sf:form>

                    <sf:form method="POST" action="/decline-pending-bet">
                        <input type="hidden" name="pendingBetId" value="${pendingBet.id}">
                        <input type="submit" value="Decline">
                    </sf:form>

                    <button class="counter-btn" onclick="openCounter(${pendingBet.id})" id="counter-btn${pendingBet.id}">Send counter offer</button>
                    <sf:form method="POST" action="/counter-pending-bet" class="counter-form ${pendingBet.id}" id="counter-form${pendingBet.id}">
                        <legend>Your Amount</legend>
                        <input id="your-amount" type="number" step="0.001" placeholder="Your amount" name="counterAmount">

                        <legend>Your Odds</legend>
                        <input id="your-odds" type="number" step="0.001" placeholder="Your Odds" name="counterOdds">
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
    </div>

</c:forEach>

<br>
<h3>Active bets</h3>
<c:forEach items="${activeBets}" var="bet">
    <div class="bet active-bet" id="pending-bet${bet.id}">
        <div class="bet-main-info" id="bet-more-info${bet.id}">
            <h4>${bet.title}</h4>
            <p>${bet.sender} vs. ${bet.receiver}</p>
        </div>
        <div class="bet-more-info" id="bet-more-info${bet.id}">
            <p><i>Description:</i> ${bet.description}</p>
            <p><i>Receiver odds</i> ${bet.oddsReceiver}</p>
            <p><i>Receiver amount</i> ${bet.amountReceiver}</p>
            <p><i>Sender odds</i> ${bet.oddsSender}</p>
            <p><i>Sender amount</i> ${bet.amountSender}</p>
            <p><i>Date and time created</i> ${bet.dateAndTimeCreated}</p>
            <p><i>Date and time for bet to be resolved</i> ${bet.dateAndTimeResolve}</p>
        </div>
    </div>
</c:forEach>

<br>
<h3>Resolved bets</h3>
<c:forEach items="${resolvedBets}" var="bet">
    <div class="bet resolved-bet" id="pending-bet${bet.id}">
        <div class="bet-main-info" id="bet-more-info${bet.id}">
            <h4>${bet.title}</h4>
            <p>${bet.sender} vs. ${bet.receiver}</p>
        </div>
        <div class="bet-more-info" id="bet-more-info${bet.id}">
            <p><i>Description:</i> ${bet.description}</p>
            <p><i>Receiver odds</i> ${bet.oddsReceiver}</p>
            <p><i>Receiver amount</i> ${bet.amountReceiver}</p>
            <p><i>Sender odds</i> ${bet.oddsSender}</p>
            <p><i>Sender amount</i> ${bet.amountSender}</p>
            <p><i>Date and time created</i> ${bet.dateAndTimeCreated}</p>
            <p><i>Date and time for bet to be resolved</i> ${bet.dateAndTimeResolve}</p>
        </div>
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
        // this.style.display = "none";

        //athuga gæti verið slæmt að nota pendingbet id, þ.e. unsafe

        console.log(pendingBetId);
        var form = document.getElementById("counter-form" + pendingBetId);
        console.log(form);
        form.style.display = "block";

        var btn = document.getElementById("counter-btn" + pendingBetId);
        btn.style.display = "none";


    }

    var bet = document.getElementsByClassName("bet");
    for(var i=0;i < bet.length; i++){
        bet[i].addEventListener("click", function() {
            var moreInfo = this.lastElementChild;
            console.log(this.lastElementChild);
            console.log(moreInfo);
            if (moreInfo.style.maxHeight){
                moreInfo.style.maxHeight = null;
            } else {
                moreInfo.style.maxHeight = moreInfo.scrollHeight + "px";
            }
        });
    }

</script>