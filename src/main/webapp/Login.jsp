<%@ page import="java.sql.Statement" %>
<%@ page import="utils.DbCon" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: cralm
  Date: 5/18/2020
  Time: 10:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login</title>
</head>
<body>
<form align="center" method="post" action="/login">
    <table style="with: 50%">

            <br><br><br><output>Autentificare</output><br><br>
            <output>Username:</output><br>
            <input type="text" name="username"><br>
            <output>Parola:</output><br>
            <input type="password" name="parola"><br>

    </table>
    <input type="submit" value="Login">
    <p align="center">
        <%
            if (request.getAttribute("loginBlank")!=null)
                out.print(request.getAttribute("loginBlank"));
            else if (request.getAttribute("loginError")!=null)
                out.print(request.getAttribute("loginError"));
            else if(request.getAttribute("username")!=null && request.getAttribute("parola")!=null ) {
                if (request.getAttribute("pass").toString().equals(request.getAttribute("parola").toString())) {
                if(request.getAttribute("ocupatie").equals("chelner"))
                request.getRequestDispatcher("LoginChelner.jsp").forward(request, response);
                else if(request.getAttribute("ocupatie").equals("manager"))
                    request.getRequestDispatcher("LoginManager.jsp").forward(request, response);
                    else if(request.getAttribute("ocupatie").equals("administrator"))
                    request.getRequestDispatcher("LoginAdmin.jsp").forward(request, response);
                    }
                else out.print("User sau parola incorecte!");

            }



        %>
    </p>
    </form>




</body>
</html>


