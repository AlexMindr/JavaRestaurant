package servlets;

import models.Angajati;
import models.Comenzi;
import repositories.AngajatiRepository;
import repositories.ComenziRepository;
import repositories.ListaRepository;
import utils.DbCon;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@WebServlet("/chelner")
public class ChelnerServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String button = request.getParameter("submit");
        String username = request.getParameter("username");

        String getid= request.getParameter("id");


        try {
            Statement statement = DbCon.getConnection().createStatement();
            String sql = "SELECT * FROM angajati WHERE username like '" + username + "'";

            ResultSet resultSet = statement.executeQuery(sql);


            if (resultSet.next()) {
                Angajati angajat = new Angajati(
                        resultSet.getInt("id_angajat"),
                        resultSet.getString("nume"),
                        resultSet.getString("username"),
                        resultSet.getString("parola"),
                        resultSet.getString("ocupatie")
                );

                request.setAttribute("username",username);
                request.setAttribute("nume", angajat.getNume());

                if ("Finalizare".equals(button)) {
                    sql = "SELECT * FROM comenzi WHERE id_comanda like '" + getid + "' AND id_angajat like '" + angajat.getId_angajat() + "'";

                    resultSet = statement.executeQuery(sql);
                    if (resultSet.next()) {
                        Comenzi comanda = new Comenzi(
                                resultSet.getInt("id_comanda"),
                                resultSet.getInt("id_angajat"),
                                resultSet.getInt("id_lista"),
                                resultSet.getInt("masa"),
                                resultSet.getTimestamp("data_ora"),
                                resultSet.getInt("finalizata")
                        );

                        if (comanda != null) {
                            if (comanda.getFinalizata() == 0) {

                                ComenziRepository.finalizare(comanda);
                                request.setAttribute("FinalizareStatus", "Comanda Finalizata cu succes!");

                            } else if (comanda.getFinalizata() == 1) {
                                request.setAttribute("FinalizareStatus", "Comanda este deja finalizata!");

                            }

                            }

                    }
                        else {
                            request.setAttribute("FinalizareStatus", "Nu poti finaliza comenzile altor Chelneri!");

                        }


                }
             else if ("Comenzi in Procesare".equals(button)){
                 request.setAttribute("ComenziP","Comenzi in curs de procesare:");

                }
                else if ("Raport Comenzi".equals(button)){
                    request.setAttribute("RapCom","Raport comenzi:");

                }
                else if("Comanda noua".equals(button)){
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();


                    String stringids = request.getParameter("ids");

                    if(stringids!=null && stringids.contains(",")==true){

                    List<Integer> lista=ListaRepository.transformFromString(stringids);
                        Comenzi comandanoua= new Comenzi(0,angajat.getId_angajat(), ListaRepository.save(lista),parseInt(request.getParameter("masa")),Timestamp.valueOf((dtf.format(now))),0);
                        ComenziRepository.save(comandanoua);
                    request.setAttribute("SuccesAdd","Comanda adaugata cu succes!");
                    }
                    else request.setAttribute("ListaError","Nu puteti adauga o comanda goala sau de acel format!");
                }


            }
            request.setAttribute("username",username);

            request.getRequestDispatcher("LoginChelner.jsp").forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }





    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("Login.jsp").forward(req,resp);

    }

}
