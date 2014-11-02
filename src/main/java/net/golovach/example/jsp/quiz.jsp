<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
    <ul>
        <li>ID:${quiz.id}</li>
        <li>CAPTION:${quiz.caption}</li>
        <li>THEME:${quiz.theme}</li>
        <li>QUESTIONS:
            <ul>
                <c:forEach var="question" items="${quiz.questions}">
                    <li><a href="./question.do?id=${question.id}">${question.caption}</a></li>
                </c:forEach>
            </ul>
        </li>
    </ul>
</body>
</html>






