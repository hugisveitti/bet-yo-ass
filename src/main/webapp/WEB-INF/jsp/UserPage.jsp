<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<html>
<head>
    <%@ page isELIgnored="false" %>

    <link rel="stylesheet" type="text/css" href="<c:url value="/css/button.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/login.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/userpage.css"/>"/>
    <link href="https://fonts.googleapis.com/css?family=Krub" rel="stylesheet">

</head>

<%@ include file="blocks/header.jsp" %>

<body>
<div class="betInfo">
    <h1>USERPAGE</h1>
    <div class="pendingBetsWrapper wrapper">
        <c:choose>
            <c:when test="${notWaitingPendingBets != null}">
                <h3>Bets you have to accept, decline or send a counter offer.</h3>
            </c:when>
            <c:otherwise>
                <h5>You have no bets to accept, decline or send a counter offer.</h5>
            </c:otherwise>
        </c:choose>

        <c:forEach items="${notWaitingPendingBets}" var="pendingBet">
            <div class="bet" id="pending-bet${pendingBet.id}">
                <button class="expand-btn">+</button>
                <div class="bet-main-info" id="bet-more-info${pendingBet.id}">
                    <h4>${pendingBet.title}</h4>
                    <p>vs. ${pendingBet.getOpponent(user.getUsername())}</p>
                    <p>${pendingBet.getYourAmount(user.getUsername())} @ ${pendingBet.getYourOdds(user.getUsername())}</p>
                </div>
                <div class="bet-more-info" id="bet-more-info${pendingBet.id}">
                    <p><i>Description:</i> ${pendingBet.description}</p>
                    <p><i>Opponent amount</i> ${pendingBet.getOpponentAmount(user.getUsername())}</p>
                    <p><i>Opponent odds</i> ${pendingBet.getOppenentOdds(user.getUsername())}</p>
                    <p><i>Your amount</i> <span>${pendingBet.getYourAmount(user.getUsername())}</span></p>
                    <p><i>Your odds</i> ${pendingBet.getYourOdds(user.getUsername())}</p>

                    <p><i>Date and time created</i> ${pendingBet.dateAndTimeCreated}</p>
                    <p><i>Date and time for bet to be resolved</i> ${pendingBet.dateAndTimeResolve}</p>
                    <p>You have to respond to the bet</p>

                    <sf:form method="POST" action="/accept-pending-bet" class="userpage-form">
                        <input type="hidden" name="pendingBetId" value="${pendingBet.id}">
                        <span hidden class="your-pending-amount">${pendingBet.getYourAmount(user.getUsername())}</span>
                        <span class="credits-error"></span>
                        <button class="button submit" type="submit" value="Accept bet">
                            <span>
                                Accept bet
                            </span>
                        </button>
                    </sf:form>

                    <sf:form method="POST" action="/decline-pending-bet" class="userpage-form">
                        <input type="hidden" name="pendingBetId" value="${pendingBet.id}">
                        <button class="button submit" type="submit" value="Decline">
                            <span>
                                Decline
                            </span>
                        </button>
                    </sf:form>

                    <button class="counter-btn button submit" id="counter-btn${pendingBet.id}">
                        <span>
                            Send counter offer
                        </span>
                    </button>

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

                        <br>

                        <span class="credits-error"></span>
                        <br>

                        <input type="hidden" name="pendingBetId" value="${pendingBet.id}">
                        <button class="button submit" type="submit" value="Make counter offer">
                            <span>
                                Make counter offer
                            </span>
                        </button>
                    </sf:form>
                </div>
            </div>
        </c:forEach>
        <br>
    </div>
    <div class="pendingBetsWrapper wrapper">
    <c:choose>
        <c:when test="${waitingPendingBets != null}">
            <h3>You are waiting for the other user to respond to these bets.</h3>
        </c:when>
        <c:otherwise>
            <h5>You are not waiting for any bet.</h5>
        </c:otherwise>
    </c:choose>

    <c:forEach items="${waitingPendingBets}" var="pendingBet">
        <div class="bet" id="pending-bet${pendingBet.id}">
            <button class="expand-btn">+</button>
            <div class="bet-main-info" id="bet-more-info${pendingBet.id}">
                <h4>${pendingBet.title}</h4>
                <p>vs. ${pendingBet.getOpponent(user.getUsername())}</p>
                <p>${pendingBet.getYourAmount(user.getUsername())} @ ${pendingBet.getYourOdds(user.getUsername())}</p>
            </div>
            <div class="bet-more-info" id="bet-more-info${pendingBet.id}">
                <p><i>Description:</i> ${pendingBet.description}</p>

                <p><i>Opponent amount</i> ${pendingBet.getOpponentAmount(user.getUsername())}</p>
                <p><i>Opponent odds</i> ${pendingBet.getOppenentOdds(user.getUsername())}</p>
                <p><i>Your amount</i> ${pendingBet.getYourAmount(user.getUsername())}</p>
                <p><i>Your odds</i> ${pendingBet.getYourOdds(user.getUsername())}</p>

                <p><i>Date and time created</i> ${pendingBet.dateAndTimeCreated}</p>
                <p><i>Date and time for bet to be resolved</i> ${pendingBet.dateAndTimeResolve}</p>
            </div>
        </div>
    </c:forEach>
    </div>
    <div class="activeBetsWrapper wrapper">
        <h3>Active bets</h3>
        <c:forEach items="${activeBets}" var="bet">
            <div class="bet active-bet" id="pending-bet${bet.id}">
                <button class="expand-btn">+</button>
                <div class="bet-main-info" id="bet-more-info${bet.id}">
                    <h4>${bet.title}</h4>
                    <p>vs. ${bet.getOpponent(user.getUsername())}</p>
                    <p>${bet.getYourAmount(user.getUsername())} @ ${bet.getYourOdds(user.getUsername())}</p>
                </div>
                <div class="bet-more-info" id="bet-more-info${bet.id}">
                    <p><i>Description:</i> ${bet.description}</p>

                    <p><i>Opponent amount</i> ${bet.getOpponentAmount(user.getUsername())}</p>
                    <p><i>Opponent odds</i> ${bet.getOppenentOdds(user.getUsername())}</p>
                    <p><i>Your amount</i> ${bet.getYourAmount(user.getUsername())}</p>
                    <p><i>Your odds</i> ${bet.getYourOdds(user.getUsername())}</p>

                    <p><i>Date and time created</i> ${bet.dateAndTimeCreated}</p>
                    <p><i>Date and time for bet to be resolved</i> ${bet.dateAndTimeResolve}</p>
                    <p>You can vote on who won the be, even though the resolved date has not been reached.</p>
                    <c:choose>
                        <c:when test="${(bet.sender == user.getUsername() and bet.senderResolved) or (bet.receiver == user.getUsername() and bet.receiverResolved) }">
                            You have voted
                            <c:choose>
                                <c:when test="${(bet.sender == user.getUsername() and bet.voteSender == 'sender') or (bet.receiver == user.getUsername() and bet.voteReceiver == 'receiver')}">
                                    for yourself to win.
                                </c:when>
                                <c:otherwise>
                                    for your opponent to win.
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <button class="vote-expand-btn button submit">
                                <span>
                                    Vote on this bet!
                                </span>
                            </button>
                            <sf:form action="/vote-bet" method="post" class="vote-form">
                                <legend>Who won?</legend>
                                <input type="hidden" name="betId" value="${bet.id}">
                                <input type="radio" name="whoWon" value="me"> <span class="vote-option">I Won</span> <br>
                                <input type="radio" name="whoWon" value="opponent"><span class="vote-option">My opponent won</span> <br>
                                <button class="button submit" type="submit">
                                    <span>
                                        Vote
                                    </span>
                                </button>
                            </sf:form>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </c:forEach>

        <br>
    </div>

    <div class="resolvedBetsWrapper wrapper">
        <h3>Resolved bets</h3>
        <c:forEach items="${resolvedBets}" var="bet">
            <div class="bet resolved-bet" id="pending-bet${bet.id}">
                <button class="expand-btn">+</button>
                <div class="bet-main-info" id="bet-more-info${bet.id}">
                    <h4>${bet.title}</h4>
                    <p>vs. ${bet.getOpponent(user.getUsername())}</p>
                    <p>${bet.getYourAmount(user.getUsername())} @ ${bet.getYourOdds(user.getUsername())}</p>
                </div>
                <div class="bet-more-info" id="bet-more-info${bet.id}">
                    <p><i>Description:</i> ${bet.description}</p>

                    <p><i>Opponent amount</i> ${bet.getOpponentAmount(user.getUsername())}</p>
                    <p><i>Opponent odds</i> ${bet.getOppenentOdds(user.getUsername())}</p>
                    <p><i>Your amount</i> ${bet.getYourAmount(user.getUsername())}</p>
                    <p><i>Your odds</i> ${bet.getYourOdds(user.getUsername())}</p>

                    <p><i>Date and time created</i> ${bet.dateAndTimeCreated}</p>
                    <p><i>Date and time for bet to be resolved</i> ${bet.dateAndTimeResolve}</p>
                </div>
            </div>
        </c:forEach>
    </div>



</div>



<div class="userInfo">
    <br>
    Logged in as ${user.getUsername()}
    <br>
    You have ${user.getCredit()} credits
    <br>
    Eru einhver not fyrir þetta?
</div>


<br>
<br>
<span id="user-credits" hidden>${user.getCredit()}</span>

</body>
</html>

<script src="<c:url value="/javascript/calcOdds.js" />"></script>

<script>

    //to open counter offer form
    var counterBtn = document.getElementsByClassName('counter-btn');
    for(var j=0;j<counterBtn.length; j++){
        counterBtn[j].addEventListener("click", function(){
            var counterForm = this.nextElementSibling;
            counterForm.style.display = "block";
            this.style.display = "none";
            var moreInfo = counterForm.parentElement;
            moreInfo.style.maxHeight = moreInfo.scrollHeight + "px";
        });
    }


    //to expand the form when clicked on +
    var expandBtn = document.getElementsByClassName("expand-btn");
    for(var i=0;i < expandBtn.length; i++){
        expandBtn[i].addEventListener("click", function() {
            var mainInfo = this.nextElementSibling;
            var moreInfo = mainInfo.nextElementSibling;
            if (moreInfo.style.maxHeight){
                moreInfo.style.maxHeight = null;
            } else {
                moreInfo.style.maxHeight = moreInfo.scrollHeight + "px";
            }
            if(this.innerHTML === "+"){
                this.innerHTML = "-";
            } else {
                this.innerHTML = "+";
            }
        });
    }

    //in active bets to open form to resolve bet
    var voteExpandBtn = document.getElementsByClassName("vote-expand-btn");
    for(i=0; i<voteExpandBtn.length; i++){
        voteExpandBtn[i].addEventListener("click", function(){
            this.style.display = "none";
           var voteForm = this.nextElementSibling;
           voteForm.style.display = "block";
           var moreInfo = voteForm.parentElement;
            moreInfo.style.maxHeight = moreInfo.scrollHeight + "px";

        });
    }

</script>