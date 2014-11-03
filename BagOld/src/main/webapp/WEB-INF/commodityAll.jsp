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
            <td width=300 px><div ><%@ include file="jspf/userId.jspf" %></div></td>
        </tr>


        </tr>
        <tr align="center">
            <td><h1>Dakine</h1></td>
            </tr>
    </table>
</div>
<br>



    <div align="left" class="block4">
        <c:forEach var="fashion" items="${fashion}">
            <nobr><a href="./commodityType.do?id=${fashion.id}" class="large magenta awesome" >${fashion.caption} </a>
            <%--<form action="./commodity.do?id=${commodity.id}"
                  method="post" enctype="application/x-www-form-urlencoded">
                <br><input type="submit" class="blue awesome" value=${commodity.article}>
                <button type="submit" value="submit" name="submit" class="button ">${commodity.article} </button>
            </form>--%>
        </c:forEach>
    </div>

<br> <br><br><br> <br>  <br>
  <div>
    <c:forEach var="commodity" items="${quizList}">
        <nobr><div class="block2">
            <h1><a href="./commodity.do?id=${commodity.id}">${commodity.article}</a></h1>
            <a href="./commodity.do?id=${commodity.id}">${commodity.fashion.caption}</a>
            <br><a href="./commodity.do?id=${commodity.id}">${commodity.length}</a>
        </div>
        </nobr>
    </c:forEach>
   </div>

    <c:forEach var="commodity" items="${quizList}">
<nobr><div class="block1">
            <h1><a href="./commodity.do?id=${commodity.id}">${commodity.price}</a></h1>
            <a href="./commodity.do?id=${commodity.id}">${commodity.length}</a> </div>
</nobr>
    </c:forEach>
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

<%--<li>Sizes:
   <ul>
       <c:forEach var="commodity" items="${quizList}">
           <li><a href="./commodity.do?id=${commodity.id}">${commodity.sizes}</a></li>

       </c:forEach>
   </ul>--%>

<%--это работало пока был List<Size> sizes
<li>Size:
    <ul>
        <c:forEach var="size" items="${commodity.sizes}">
            &lt;%&ndash;<li>Length: ${size.getLength()}</li>&ndash;%&gt;
            <li>Item: ${size.article}</li>
            <li>Length: ${size.length}</li>
            <li>Width: ${size.width}</li>
            <li>Heigth: ${size.heigth}</li>
        </c:forEach>
    </ul>
</li>--%>