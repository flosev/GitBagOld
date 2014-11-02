<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <%@ include file="WEB-INF/jspf/cssInclude.jspf"%>
</head>

<body>
<%@ include file="WEB-INF/jspf/userId.jspf"%>

<h2><span style="text-decoration: underline;">Theme:</span> ${theme.caption}</h2>
<ul>
    <c:choose>
        <c:when test="${empty theme.parent}">
            <li>Parent: no parent (top level theme)</li>
        </c:when>
        <c:otherwise>
            <li>Parent: <a href="./theme.do?id=${theme.parent.id}">${theme.parent.caption}</a></li>
        </c:otherwise>
    </c:choose>
    <li>Children themes:
        <c:choose>
            <c:when test="${empty theme.children}">No sub themes (lowest level)</c:when>
            <c:otherwise>
                <ul>
                    <c:forEach var="elem" items="${theme.children}">
                        <li><a href="./theme.do?id=${elem.id}">${elem.caption}</a></li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
</ul>

<c:if test="${not empty quizList}">
    Quizes with such theme:
    <c:forEach var="quiz" items="${quizList}">
        <a href="./quiz.do?id=${quiz.id}">${quiz.caption}</a>,
    </c:forEach>
</c:if>

<br/><a href="./themeEdit.do?id=${theme.id}">Edit theme</a>
<br/><a href="./themeAll.do">All themes</a>

</body></html>