<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

    <head>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>"/>
        <title>BET YO ASS</title>
    </head>
    <header>
        <h1>BET YO ASS</h1>
        <nav>
            <c:choose>
                <c:when test="${username != null}">
                    <a href="/userpage">User Page</a>
                    <a href="/sendbet">Make a bet</a>
                    <a href="/logout">Logout</a>
                </c:when>
                <c:otherwise>
                    <a href="/signup">Signup</a>
                    <a href="/login">Login</a>
                </c:otherwise>
            </c:choose>
        </nav>
    </header>
    <body>


    </body>
    <footer>Class HBV501G, University of Iceland</footer>


</html>
