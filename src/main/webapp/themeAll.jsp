<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <%@ include file="WEB-INF/jspf/cssInclude.jspf"%>
</head>

<body>
<%@ include file="WEB-INF/jspf/userId.jspf"%>

<h2>All Themes</h2>
    <ul>
        <c:forEach var="theme" items="${themeList}">
            <li><a href="./theme.do?id=${theme.id}">${theme.caption}</a></li>
        </c:forEach>
    </ul>
</body>
</html>
