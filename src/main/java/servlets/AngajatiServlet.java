package servlets;

import models.Angajati;
import models.Comenzi;
import repositories.AngajatiRepository;
import repositories.ComenziRepository;
import utils.DbCon;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebServlet("/login")
public class AngajatiServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String line = request.getReader().readLine();
        line = URLDecoder.decode(line);



        try {
             Angajati angajat = AngajatiRepository.findByUsername(line.split("&")[0].split("=")[1]);
            if (line.split("&")[1].split("=")[1].isEmpty()|| line.split("&")[0].split("=")[1].isEmpty())
                request.setAttribute("loginBlank","Campurile de username si parola nu pot fi goale!");
            if (angajat==null ) {

                request.setAttribute("loginError", "Username/parola incorecte!");

            }
            else {
                request.setAttribute("Id",angajat.getId_angajat());
                request.setAttribute("nume",angajat.getNume());
                request.setAttribute("username",angajat.getUsername());
                request.setAttribute("parola",line.split("&")[1].split("=")[1]);
                request.setAttribute("ocupatie",angajat.getOcupatie());

                Statement statement = DbCon.getConnection().createStatement();
                String sql = "SELECT parola FROM angajati WHERE username like '" + request.getAttribute("username") + "'";

                ResultSet resultSet = statement.executeQuery(sql);
                String string= new String();
                if (resultSet.next())
                    string = resultSet.getString("parola");
                    request.setAttribute("pass",string);


                }
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            }


         catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }






    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("Login.jsp").forward(req,resp);

    }

}
