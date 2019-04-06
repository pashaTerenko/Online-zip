<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>ToZIP</title>
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
<div align="center">
    <c:url value="/newuser" var="regUrl" />

    <form action="${regUrl}" method="POST">
        Login:<br/><input type="text" name="login"><br/>
        Password:<br/><input type="password" name="password"><br/>
        <input type="submit" />

        <c:if test="${exists ne null}">
            <p>User already exists!</p>
        </c:if>
    </form>
</div>
</body>
</html>
