<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
</head>

<body>
<%@ include file="WEB-INF/jspf/userId.jspf"%>

REGISTERED NEW USER:

<ul>
    <li>Login: ${user.login}</li>
    <li>Password: ${user.password}</li>
    <li>Email: ${user.email}</li>
</ul>

</body>
</html>
