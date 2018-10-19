<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>

<%@ include file="blocks/header.jsp" %>

<h1>Send bet</h1>

yo thu er ${username}
<sf:form method="POST" modelAttribute="bet" action="/sendbet">

    <input type="text" placeholder="Username receiver" name="usernameReceiver"><br>
    <input type="text" placeholder="Bet name" name="name"/><br>
    <input type="text" placeholder="Description" name="description"/><br>

    <input type="submit" VALUE="Send bet"/>

</sf:form>

</html>