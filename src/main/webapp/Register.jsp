<%--
  Created by IntelliJ IDEA.
  User: cralm
  Date: 5/18/2020
  Time: 11:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Angajati" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<br>
<br>
<html>
<head>
    <title>Inregistrare</title>
</head>
<body>

<form align="center" method="post" action="/register">
    <output>Nume complet:</output><br>
    <input type="text" name="nume"><br>
    <output>Username:</output><br>
    <input type="text" name="username"><br>
    <output>Parola:</output><br>
    <input type="password" name="parola"><br>
    <output>Ocupatie:</output><br>
    <input type="text" name="ocupatie"><br>

    <input type="submit" value="Register">
    <p align="center">
        <%if(request.getAttribute("registerError")!=null)
            out.print(request.getAttribute("registerError"));
        else if(request.getAttribute("ocupatieError")!=null)
            out.print(request.getAttribute("ocupatieError"));
          else if (request.getAttribute("registerSuccess")!=null)
              out.print(request.getAttribute("registerSuccess"));

        %>
    </p>

</form>




</body>
</html>
