package servlets;

import models.Angajati;
import models.Comenzi;
import repositories.ComenziRepository;
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
import java.util.List;

@WebServlet("/manager")
public class ManagerServlet extends HttpServlet {
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

                if ("Raport".equals(button)) {
                    request.setAttribute("RaportL","Lista in ordine descrescatoare este:");
                    }




                else if ("Afiseaza".equals(button)){
                    System.out.println(request.getParameter("data1"));

                    request.setAttribute("Comenzi in interval","Comenzi in intervalul cerut:");
                    List<Comenzi> comenzi= ComenziRepository.findByDateTimeInterval(request.getParameter("data1"),request.getParameter("data2"),request.getParameter("timp1"),request.getParameter("timp2"));
                    System.out.println(comenzi);
                    request.setAttribute("comenzi",comenzi);

                }
                else if ("Comenzi in Lucru".equals(button)){
                    request.setAttribute("ComLucru","Raport comenzi:");


                }

            }
            request.setAttribute("username",username);

            request.getRequestDispatcher("LoginManager.jsp").forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }





    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("Login.jsp").forward(req,resp);

    }

}


