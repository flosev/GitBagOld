<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <%@ include file="WEB-INF/jspf/cssInclude.jspf"%>
</head>

<body>
<%@ include file="WEB-INF/jspf/userId.jspf"%>

<h2><span style="text-decoration: underline;">Quiz:</span> ${quiz.caption}</h2>
<ul>
    <li>Intro: ${quiz.intro}</li>
    <li>Questions:
        <ul>
            <c:forEach var="question" items="${quiz.questions}">
                <li><a href="./question.do?id=${question.id}">${question.caption}</a></li>
            </c:forEach>
        </ul>
    </li>
    <li>Themes:
            <c:forEach var="theme" items="${quiz.themes}">
                <a href="./theme.do?id=${theme.id}">${theme.caption}</a>,
            </c:forEach>
    </li>
</ul>

<br/>
<a href="quizAll.do">All quizes</a>

<br/><%-- Add quiz to bucket--%>
<a href="./quizAddToBucket.do?id=${quiz.id}">Add this quiz to bucket</a>

<br/><br/><br/><%-- Show quiz backet --%>
<h2>Quizes in bucket</h2>
<ul>
    <c:forEach var="quizInBucket" items="${quizesInBucket}">
        <li><a href="./quiz.do?id=${quizInBucket.id}">${quizInBucket.caption}</a>(<a href="./quizRemoveFromBucket.do?id=${quizInBucket.id}&redirectToId=${quiz.id}">X</a>)</li>
    </c:forEach>
</ul>

</body></html>






