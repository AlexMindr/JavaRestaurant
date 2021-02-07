<%@ page import="repositories.ComenziRepository" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="utils.DbCon" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="models.Meniu" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Comenzi" %><%--
  Created by IntelliJ IDEA.
  User: cralm
  Date: 5/20/2020
  Time: 3:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Manager</title>
</head>
<body>
<p align="center" style="font-size:25px;color:darkblue;">

    <br><br><br><output>Autentificat ca si Manager!</output><br><br>


</p>

<p align="center" style="font-size:20px;color:sandybrown;">
    <%
        out.print("Bine ai venit "+request.getAttribute("nume")+" !");


    %>
    <br>
</p>




<p align="center" >
    <%

        String form ="<form align='center' method='post' action='/manager'>\n"+
                "<br><output>Rapoarte lista feluri de mancare descrescator</output><br>\n"+
                "<input type='hidden' name='username' value='"+request.getAttribute("username")+"'\n"+
                "<input type='text' name='rapdesc'><br>"+
                "<input type='submit' name='submit' value='Raport'>\n"+
                "</form>";

        out.print(form);
    %>
</p>

<p align="center" >
    <%if(request.getAttribute("RaportL")!=null) {
        out.print(request.getAttribute("RaportL"));
        out.print("<br>");
        Statement statement = DbCon.getConnection().createStatement();
        String sql = "SELECT lista.id_felmnc,nume_felmnc,pret,count(lista.id_felmnc) cantitate FROM lista JOIN meniu using (id_felmnc) GROUP BY lista.id_felmnc ORDER BY count(lista.id_felmnc) desc";

        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            Meniu fel = new Meniu(
                    resultSet.getInt("id_felmnc"),
                    resultSet.getString("nume_felmnc"),
                    resultSet.getDouble("pret")
            );

            Integer cantitate= resultSet.getInt("cantitate");
            out.print(fel+" si cantitate vanduta: "+ cantitate);
            out.print("<br>");
        }

    }
    %>
</p>
<br>
<p align="center" >
    <%

        String form2 ="<form align='center' method='post' action='/manager'>\n"+
                "<br><output>Comenzi intr-un interval</output><br>\n"+
                "<input type='hidden' name='username' value='"+request.getAttribute("username")+"'\n"+
                "<output>Data1</output><input type='text' name='data1'>"+
                "<output> Timp1</output><input type='text' name='timp1'><br><br>"+
                "<output>Data2</output><input type='text' name='data2'>"+
                "<output> Timp2</output><input type='text' name='timp2'><br><br>"+
                "<input type='submit' name='submit' value='Afiseaza'>\n"+
                "</form>";

        out.print(form2);
    %>
</p>

<p align="center" >
    <%if(request.getAttribute("Comenzi in interval")!=null) {
        out.print(request.getAttribute("Comenzi in interval"));
        out.print("<br>");
        List <Comenzi> comenzi= (List<Comenzi>) request.getAttribute("comenzi");
        for(Comenzi comanda:comenzi) {
            out.print(comanda);
            out.print("<br>");
        }

    }

    %>
</p>


<br>
<p align="center" >
    <%

        String form3 ="<form align='center' method='post' action='/manager'>\n"+
                "<br><output>Raport Comenzi</output><br>\n"+
                "<input type='hidden' name='username' value='"+request.getAttribute("username")+"'\n"+
                "<input type='text' name='comenziproc'><br>"+
                "<input type='submit' name='submit' value='Comenzi in Lucru'>\n"+
                "</form>";

        out.print(form3);
    %>
</p>

<p align="center" >
    <%if(request.getAttribute("ComLucru")!=null) {
        out.print(request.getAttribute("ComLucru"));
        out.print("<br>");
        Statement statement = DbCon.getConnection().createStatement();
        String sql = "select id_comanda,id_angajat,id_lista,masa,data_ora,finalizata,nume from comenzi join angajati using (id_angajat) where finalizata = 0";

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Comenzi comanda = new Comenzi(
                    resultSet.getInt("id_comanda"),
                    resultSet.getInt("id_angajat"),
                    resultSet.getInt("id_lista"),
                    resultSet.getInt("masa"),
                    resultSet.getTimestamp("data_ora"),
                    resultSet.getInt("finalizata"));

            String nume = resultSet.getString("nume");

            out.print(comanda + " creata de " + nume);
            out.print("<br>");
        }
    }

    %>
</p>






</body>
</html>
