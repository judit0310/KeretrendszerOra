<%--
  Created by IntelliJ IDEA.
  User: judit
  Date: 2020. 11. 12.
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>${dolgozo.id}</title>
</head>
<body>
<table>
    <tr><td>Név:</td><td>${dolgozo.nev}</td></tr>
    <tr><td>Születési Dátum:</td><td>${dolgozo.szuletesiDatum}</td></tr>
    <tr><td>Részleg:</td><td>${dolgozo.reszleg}</td></tr>
    <tr><td>Fizetés</td><td>${dolgozo.fizetes}</td></tr>
    <tr><td>Ismert nyelvek</td><td>${dolgozo.nyelvIsmeret}</td></tr>
</table>
<form action="${pageContext.servletContext.contextPath}/">
    <input type="submit" value="Home">
</form>

</body>
</html>
