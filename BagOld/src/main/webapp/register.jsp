<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
</head>

<body>

REGISTER NEW USER:

<form action="register.do" method="post" enctype="application/x-www-form-urlencoded">

    <label for="login">Login:</label>
    <br><input name="login" id="login" type="text" value="${login}"/>
    ${errorMap.login}

    <br><label for="password">Password:</label>
    <br><input name = "password" id="password" type="password" value="${password}"/>
    ${errorMap.password}

    <br><label for="email">Email:</label>
    <br><input name = "email" id="email" type="text" value="${email}"/>
    ${errorMap.email}

    <br><input type="submit"/>
</form>

</body>
</html>
