package servlets;

import models.Angajati;
import models.Comenzi;
import models.Meniu;
import repositories.ComenziRepository;
import repositories.MeniuRepository;
import utils.DbCon;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String button = request.getParameter("submit");
        String username = request.getParameter("username");




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




                if ("Salveaza".equals(button)) {
                    Meniu menius = MeniuRepository.findByNume(request.getParameter("nume_felmnc"));
                    if (menius == null) {
                        request.setAttribute("Save", "Salvat cu success!");
                        Meniu noufel = new Meniu(
                                0,
                                request.getParameter("nume_felmnc"),
                                parseDouble(request.getParameter("pret"))
                        );
                        MeniuRepository.save(noufel);
                    }
                    else request.setAttribute("SaveFail","Acest fel de mancare deja exista!");
                }




                else if ("Editeaza".equals(button)){
                    Integer id=parseInt(request.getParameter("id"));

                    sql = "select * from meniu  where id_felmnc ='"+ id +"'";
                    resultSet = statement.executeQuery(sql);


                    if (resultSet.next()) {
                        Meniu fel = new Meniu(
                                resultSet.getInt("id_felmnc"),
                                resultSet.getString("nume_felmnc"),
                                resultSet.getDouble("pret")

                        );
                        if(fel!=null) {

                            MeniuRepository.edit(id, request.getParameter("nume_felmnc"), parseDouble(request.getParameter("pret")));
                            request.setAttribute("Edit", "Editarea s-a realziat cu succes!");
                        }
                        else  request.setAttribute("EditFail", "Nu exista acel produs!");


                }

            }
            request.setAttribute("username",username);

            request.getRequestDispatcher("LoginAdmin.jsp").forward(request, response);
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }





    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("Login.jsp").forward(req,resp);

    }

}




