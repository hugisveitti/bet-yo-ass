<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

    <head>
        <meta charset="utf-8"/>
        <title>BET YO ASS</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/button.css"/>"/>
        <link href="https://fonts.googleapis.com/css?family=Krub" rel="stylesheet">
    </head>
    <%@ include file="blocks/header.jsp" %>
    <body>

    <div class="notifications">
        <sec:authorize access="!isAuthenticated()">
            <div class="card signupCard">
                <div class="cardContent">
                    <div class="text-area">
                        <p>Sign up and join the party. Is's super easy!</p>
                    </div>
                    <div class="buttonArea">
                        <a href="/signup">
                            <button class="button" style="vertical-align:middle">
                                <span>Sign up </span>
                            </button>
                        </a>
                    </div>
                </div>
            </div>
            <div class="card notificationCard">
                <div class="cardContent">
                    <div class="text-area">
                        <p>You have an account and want to continue the fun.</p>
                    </div>
                    <div class="buttonArea">
                        <a href="/login">
                            <button class="button" style="vertical-align:middle">
                                <span>Login </span>
                            </button>
                        </a>
                    </div>
                </div>
            </div>
            <div class="card adviceCard">
                <div class="cardContent">
                    <div class="text-area">
                        <p>Need some help with gambling addiction? Get the help you need.</p>
                    </div>
                    <div class="buttonArea">
                        <a href="https://www.helpguide.org/articles/addictions/gambling-addiction-and-problem-gambling.htm">
                            <button class="button adviceButton" style="vertical-align:middle">
                                <span>Get help </span>
                            </button>
                        </a>
                    </div>

                </div>
            </div>
        </sec:authorize>
    </div>

    </body>

    <footer>Class HBV501G, University of Iceland</footer>


</html>
