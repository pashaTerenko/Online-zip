
<%--
  Created by IntelliJ IDEA.
  User: teren
  Date: 24.03.2019
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<html>
<head>
    <title>ToZip</title>
    <style>
        body{
            background-color: rgba(240, 255, 248, 0); /* Цвет фона веб-страницы */
        }
        h1 {
            background-color: RGB(249, 201, 16); /* Цвет фона под заголовком */
        }

    </style>
</head>
<body>
<h1>To zip</h1>






<div aria-orientation="horizontal">
    <table class="table">
        <thead>

        <tr>
            <th scope="col">Files</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${files}" var="o">
            <tr>
                <td><c:out value="${o.value}"/><form action="/zips/get" method="get">
                    <button type="submit" class="btn btn-primary" name="id" value="${o.key}">download zip</button>
                </form></td>

            </tr>

        </c:forEach>
        </tbody>
    </table>

</div>

<nav class="navbar navbar-default">
    </form>
      <form action="/zips/resetzip" method="post">
          <button type="submit" class="btn btn-primary">reset</button>
      </form>
    <form action="/" method="get">
        <button type="submit" class="btn btn-primary">go back</button>
    </form>
</div>

</nav>
</body>
</html>
