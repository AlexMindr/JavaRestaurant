<%@ page import="repositories.ComenziRepository" %>
<%@ page import="models.Meniu" %>
<%@ page import="java.util.List" %>
<%@ page import="repositories.MeniuRepository" %><%--
  Created by IntelliJ IDEA.
  User: cralm
  Date: 5/19/2020
  Time: 5:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Chelner</title>
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

        <br><br><br><output>Autentificat ca si Chelner!</output><br><br>


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
                <%
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
            <h2 align="center">Ce doriti sa faceti?</h2>

    <p align="center" >
            <%

                String form ="<form align='center' method='post' action='/chelner'>\n"+
                        "<br><output>Finalizare Comanda</output><br>\n"+
                        "<input type='hidden' name='username' value='"+request.getAttribute("username")+"'\n"+
                        "<output>Id: </output><input type='text' name='id'><br>"+
                        "<input type='submit' name='submit' value='Finalizare'>\n"+
                        "</form>";

                out.print(form);
            %>
    </p>

        <p align="center" >
        <%if(request.getAttribute("FinalizareStatus")!=null)
                out.print(request.getAttribute("FinalizareStatus"));


        %>
        </p>
<br>
    <p align="center" >
        <%

            String form2 ="<form align='center' method='post' action='/chelner'>\n"+
                    "<br><output>Comenzi in curs de procesare</output><br>\n"+
                    "<input type='hidden' name='username' value='"+request.getAttribute("username")+"'\n"+
                    "<input type='text' name='comenzip'><br>"+
                    "<input type='submit' name='submit' value='Comenzi in Procesare'>\n"+
                    "</form>";

            out.print(form2);
        %>
    </p>

    <p align="center" >
        <%if(request.getAttribute("ComenziP")!=null) {
            out.print(request.getAttribute("ComenziP"));
            out.print("<br>");
            out.print(ComenziRepository.ComenziInLucruFaraNume());
        }

        %>
    </p>


    <br>
    <p align="center" >
        <%

            String form3 ="<form align='center' method='post' action='/chelner'>\n"+
                    "<br><output>Raport Comenzi</output><br>\n"+
                    "<input type='hidden' name='username' value='"+request.getAttribute("username")+"'\n"+
                    "<input type='text' name='rapcom'><br>"+
                    "<input type='submit' name='submit' value='Raport Comenzi'>\n"+
                    "</form>";

            out.print(form3);
        %>
    </p>

    <p align="center" >
        <%if(request.getAttribute("RapCom")!=null) {
            out.print(request.getAttribute("RapCom"));
            out.print("<br>");
            out.print(ComenziRepository.findByAngajat(request.getAttribute("username").toString()));
        }

        %>
    </p>

    <br>
    <p align="center" >
        <%

            String form4 ="<form align='center' method='post' action='/chelner'>\n"+
                    "<br><output>Creeaza comanda noua</output><br>\n"+
                    "<output>Masa: </output><input type='text' name='masa'><br>\n"+
                    "<input type='hidden' name='username' value='"+request.getAttribute("username")+"'\n"+
                    "<output> Id Produse: </output><input type='text' name='ids'><br>\n"+
                    "<input type='submit' name='submit' value='Comanda noua'>\n"+
                    "</form>";

            out.print(form4);
        %>
    </p>

    <p align="center" >
        <%if(request.getAttribute("ListaError")!=null) {
            out.print(request.getAttribute("ListaError"));

        }
        else if(request.getAttribute("SuccesAdd")!=null) {
            out.print(request.getAttribute("SuccesAdd"));
        }
        %>
    </p>
        </div>
    </div>





</body>
</html>