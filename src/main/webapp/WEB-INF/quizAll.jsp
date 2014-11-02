<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <%@ include file="jspf/cssInclude.jspf"%>
</head>

<body>
<%@ include file="jspf/userId.jspf"%>

<h2>All Quizes</h2>
    <ul>
        <c:forEach var="quiz" items="${quizList}">
            <li><a href="./quiz.do?id=${quiz.id}">${quiz.caption}</a></li>
        </c:forEach>
    </ul>
</body>
</html>
