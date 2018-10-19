

<link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>"/>

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
