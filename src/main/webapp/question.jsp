<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <%@ include file="WEB-INF/jspf/cssInclude.jspf"%>
</head>

<body>
<%@ include file="WEB-INF/jspf/userId.jspf"%>

<br/>
<br/>

<h2><span style="text-decoration: underline;">Question:</span> ${question.caption}</h2>
<ul>
    <li>Question: ${question.question}</li>
    <li>Themes:
        <c:forEach var="theme" items="${question.themes}">
            <a href="./theme.do?id=${theme.id}">${theme.caption}</a>,
        </c:forEach>
    </li>
    <li>Answer:
        <ul>
            <c:forEach var="answer" items="${question.answers}">
                <li>${answer.answer}</li>
            </c:forEach>
        </ul>
    </li>
</ul>

</body></html>