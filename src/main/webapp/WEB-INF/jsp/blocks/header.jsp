<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>"/>
<header class="header">

    <h1 class="mainHeading">
        <a class="indexLink" href="/">BET YO ASS</a>
    </h1>
    <h3 class="secondaryHeading" href="/">
        <a class="indexLink" href="/">You surely can!</a>
    </h3>
        <nav>
            <sec:authorize access="isAuthenticated()">
                <a href="/userpage">User Page</a>
                <a href="/sendbet">Make a bet</a>
                <sf:form method="POST" action="/logout" id="logout-form">
                    <a href="javascript:;" onclick="parentNode.submit();">Logout</a>
                    <input type="hidden" VALUE="logout"/>
                </sf:form>

            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
                <a href="/signup">Signup</a>
                <a href="/login">Login</a>
            </sec:authorize>
        </nav>
</header>
