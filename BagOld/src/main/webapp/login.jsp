<%@ page import="net.flosev.quiz.filter.security.UrlCodec" %>
<%@ page import="static net.flosev.quiz.filter.security.UrlCodec.decode" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
</head>
<body>
<form action="login.do?redirectTo=<%= request.getParameter("redirectTo")%>"
      method="post"
      enctype="application/x-www-form-urlencoded">
    <label for="login">Login:</label>
    <br><input name="login" id="login" type="text"/>
    <br><label for="password">Password</label>
    <br><input name = "password" id="password" type="password"/>
    <br><input type="submit"/>
</form>
// you can be Mike/123 or Sara/123

<br/>
Or you can <a href="register.do">Register new user</a>

</body>
</html>
