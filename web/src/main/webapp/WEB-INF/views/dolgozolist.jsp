<%--
  Created by IntelliJ IDEA.
  User: judit
  Date: 2020. 11. 12.
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false"%>
<html>
<head>
    <title>Dolgozóink</title>
</head>
<body>
<c:if test="${!empty dolgozok}">
    <table frame="border" rules="all">
        <tr><th>Azonosító</th><th>Név</th><th>Születési Dátum</th><th>Részleg</th></tr>
<c:forEach items="${dolgozok}" var="dolgozo">
    <tr><td><a href="${pageContext.servletContext.contextPath}/dolgozo/${dolgozo.id}">${dolgozo.id}</a></td><td>${dolgozo.nev}</td><td>${dolgozo.szuletesiDatum}</td><td>${dolgozo.reszleg}</td></tr>
</c:forEach>
    </table>
</c:if>
<c:if test="${empty dolgozok}">
<c:out value="Nincs dolgozonk"/></c:if>

<form action="${pageContext.servletContext.contextPath}/addDolgozo">
    <input type="submit" value="Dolgozó hozzáadása">
</form>
</body>
</html>
