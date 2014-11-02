<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <%@ include file="WEB-INF/jspf/cssInclude.jspf"%>
</head>

<body>
<%@ include file="WEB-INF/jspf/userId.jspf"%>

<h2><span style="text-decoration: underline;">Edit theme:</span></h2>

<form action="./themeEdit.do?id=${quiz.id}" method="POST" enctype="application/x-www-form-urlencoded">
    Id: ${theme.id}
    <br/>Caption: <input type="text">${theme.caption}</input>
    <br/><input type="submit">Save</input>
</form>

</body></html>