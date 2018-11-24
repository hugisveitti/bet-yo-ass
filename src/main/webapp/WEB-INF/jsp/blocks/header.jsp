<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<link rel="icon" type="image/png" href="/images/icon.png">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>"/>
<header class="header">

    <h1 class="mainHeading">
        <a class="indexLink" href="/">BET YO ASS</a>
    </h1>
    <h3 class="secondaryHeading" href="/">
        <a class="indexLink" href="/">You surely can!</a>
    </h3>
    <nav class="navigation">



        <sec:authorize access="isAuthenticated()">
            <div class="user-info">
                <div class="user-name">
                    <!-- <img class="user-img" src="images/default-user.png" /> -->

                    <p><i class="fa fa-user"></i> ${user.getUsername()}</p>
                </div>
                <div class="user-credit">
                    <!-- <img src="images/logo.png" /> -->

                    <p> <i class="fa fa-money"></i> ${user.getCredit()}</p>
                </div>

            </div>

            <div class="header-tabs">
                <a href="/userpage">User Page</a>
                <a href="/sendbet">Make a bet</a>
                <sf:form method="POST" action="/logout" id="logout-form">
                    <a href="javascript:;" onclick="parentNode.submit();">Logout</a>
                    <input type="hidden" VALUE="logout"/>
                </sf:form>
            </div>

        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
            <div class="header-tabs">
                <a href="/signup">Signup</a>
                <a href="/login">Login</a>
            </div>
        </sec:authorize>
    </nav>
</header>
