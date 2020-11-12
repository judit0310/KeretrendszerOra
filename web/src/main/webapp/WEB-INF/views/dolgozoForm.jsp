<%--
  Created by IntelliJ IDEA.
  User: judit
  Date: 2020. 11. 12.
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Dolgozó Hozzáadása</title>
</head>
<body>
<form:form method="post" action="addDolgozo" modelAttribute="dolgozo">
    <form:label path="nev">Név</form:label>
    <form:input path="nev"/>
    <form:label path="szuletesiDatum">Születési Dátum</form:label>
    <form:input type="date" path="szuletesiDatum"/>
    <form:label path="fizetes">Fizetés</form:label>
    <form:input path="fizetes"/>
    <form:label path="reszleg">Részleg</form:label>
    <form:select path="reszleg">
        <form:options items="${reszlegek}"/>
    </form:select>
    <input type="submit" value="Küldés"/>
</form:form>
</body>
</html>
