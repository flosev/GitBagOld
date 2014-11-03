<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <%@ include file="WEB-INF/jspf/cssInclude.jspf"%>

</head>

<body>
<%@ include file="WEB-INF/jspf/userId.jspf"%>


<ul>
    <li>ID: <a href="./commodity.do?id=${commodity.id}">${commodity.id}</a></li>
    <li>Article: <a href="./commodity.do?id=${commodity.id}">${commodity.article}</a></li>
    <li>Size: <a href="./commodity.do?id=${commodity.id}">${commodity.length} mm*${commodity.width} mm*${commodity.heigth}mm</a></li>
    <li>Price: <a href="./commodity.do?id=${commodity.id}">${commodity.price}</a></li>
    <li>Type: <a href="./commodity.do?id=${commodity.id}">${commodity.fashion.caption}</a></li>
    <img src="images/${commodity.article}.jpg" width="250" height="230"/>
</ul>

<br/>
<a href="commodityAll.do">All quizes</a>

<br/><%-- Add quiz to bucket--%>
<a href="./quizAddToBucket.do?id=${quiz.id}">Add this quiz to bucket</a>

<br/><br/><br/><%-- Show quiz backet --%>
<h2>Quizes in bucket</h2>
<ul>
    <c:forEach var="quizInBucket" items="${quizesInBucket}">
        <li><a href="./quiz.do?id=${quizInBucket.id}">${quizInBucket.caption}</a>(<a href="./quizRemoveFromBucket.do?id=${quizInBucket.id}&redirectToId=${quiz.id}">X</a>)</li>
    </c:forEach>
</ul>
<iframe src="quiz.jsp" width="468" height="60" align="left">
    Ваш браузер не поддерживает плавающие фреймы!
</iframe>

</body></html>
