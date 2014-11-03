<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="css/blueprint/screen1.css" type="text/css" href="/css/firefox.css?1" media="screen, projection" />
    <link rel="stylesheet" href="css/blueprint/ie.css" type="text/css" media="screen, projection" />

</head>
<body >


<br><div  class="blockMain">
    <table cellspacing=10 px >
        <tr align="right">
            <td width=600 px><h2>Товары от украинского производителя</h2></td>
            <td width=300 px><div ><%@ include file="WEB-INF/jspf/userId.jspf" %></div></td>
        </tr>


        </tr>
        <tr align="center">
            <td><h1>Dakine</h1></td>
        </tr>
    </table>
</div>
<br><br><br><br><br><br><br><br>
<ul>
    <li><a href="./commodityAll.do">Click me pls</a></li>
</ul>

<ul>
    <li><a href="${pageContext.request.contextPath}/simplemodel1"
           title="simplemodel1">simplemodel12</a></li>
    <li><a href="${pageContext.request.contextPath}/requestmethod1"
           title="requestmethod (get)">requestmethod (get)</a></li>
    <li>
        <form action="${pageContext.request.contextPath}/requestmethod"
              method="post">
            <input type="submit" value="requestmethod (post)">
        </form>
    </li>
</ul>
<img src="images/wall1.jpg" width="250" height="230"/>
</body>
</html>
