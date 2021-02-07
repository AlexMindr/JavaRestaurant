<%@ page import="java.sql.Statement" %>
<%@ page import="utils.DbCon" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="models.Meniu" %>
<%@ page import="java.util.List" %>
<%@ page import="repositories.MeniuRepository" %><%--
  Created by IntelliJ IDEA.
  User: cralm
  Date: 5/20/2020
  Time: 4:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        * {
            box-sizing: border-box;
        }

        /* Create two equal columns that floats next to each other */
        .column {
            float: left;
            width: 50%;
            padding: 10px;

        }

        /* Clear floats after the columns */
        .row:after {
            content: "";
            display: table;
            clear: both;
        }
    </style>
</head>
<body>
<p  align="center" style="font-size:25px;color:darkblue;">
<h2><br><br><output>Autentificat ca si Administrator!</output><br></h2>
</p>
<p align="center" style="font-size:20px;color:sandybrown;">
    <%
        out.print("Bine ai venit "+request.getAttribute("nume")+" !");


    %>
    <br>
</p>


<div class="row">
    <div class="column" style="background-color:#bbb;">
        <h2 align="center">Meniul actual:</h2>
        <br>
        <p align="center" >
            <%  out.print("<br>");
                out.print("<br>");

                out.print("<br>");
                List<Meniu> meniu= (List<Meniu>) MeniuRepository.ListaProduse();
                for(Meniu fel:meniu) {
                    out.print(fel);
                    out.print("<br>");
                }




            %>
        </p>
    </div>
    <div class="column" >
        <h2 align="center">Comenzi posibile</h2>
        <p>
        <p align="center" >
            <%

                String form ="<form align='center' method='post' action='/admin'>\n"+
                        "<br><output>Adauga un fel de mancare</output><br>\n"+
                        "<input type='hidden' name='username' value='"+request.getAttribute("username")+"'\n"+
                        "<output>Nume: </output><input type='text' name='nume_felmnc'><br>\n"+
                        "<output>  Pret: </output><input type='text' name='pret'><br>\n"+
                        "<input type='submit' name='submit' value='Salveaza'>\n"+
                        "</form>";

                out.print(form);
            %>
        </p>

        <p align="center" >
            <%if(request.getAttribute("SaveFail")!=null) {
                out.print(request.getAttribute("SaveFail"));
                out.print("<br>");

            }
            else if(request.getAttribute("Save")!=null){
                out.print(request.getAttribute("Save"));
                out.print("<br>");
            }
            %>
        </p>
        <br>
        <p align="center" >
            <%

                String form2 ="<form align='center' method='post' action='/admin'>\n"+
                        "<br><output>Editeaza fel de mancare</output><br>\n"+
                        "<input type='hidden' name='username' value='"+request.getAttribute("username")+"'\n"+
                        "<output>Introduceti id-ul: </output><input type='text' name='id'><br>\n"+
                        "<output>Nume nou: </output><input type='text' name='nume_felmnc'><br>\n"+
                        "<output>Pret nou: </output><input type='text' name='pret'><br>\n"+
                        "<input type='submit' name='submit' value='Editeaza'>\n"+
                        "</form>";

                out.print(form2);
            %>
        </p>

        <p align="center" >
            <%if(request.getAttribute("EditFail")!=null) {
                out.print(request.getAttribute("EditFail"));
                out.print("<br>");

            }
            else if(request.getAttribute("Edit")!=null){
                out.print(request.getAttribute("Edit"));
                out.print("<br>");
            }
            %>
        </p>
        </p>
    </div>
</div>






</body>
</html>
