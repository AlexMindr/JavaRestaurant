package servlets;

import models.Angajati;
import repositories.AngajatiRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;

@WebServlet("/register")
public class Register extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {




        try {
           Angajati angajat = AngajatiRepository.findByUsername(request.getParameter("username"));


        if (angajat != null) {
            request.setAttribute("registerError", "Username deja luat!");


        }
        else {
             angajat = new Angajati(0,
                    request.getParameter("nume"),
                    request.getParameter("username"),
                    request.getParameter("parola"),
                    request.getParameter("ocupatie")
            );

            if(angajat.getOcupatie().equals("chelner")) {
                request.setAttribute("registerSuccess", "Inregistrarea s-a realizat cu succes!");
                AngajatiRepository.save(angajat);
            }
            else if (angajat.getOcupatie().equals("manager")) {
                     Angajati ang = AngajatiRepository.findByNewManager(angajat.getNume());
                    if(ang !=null) {
                        if (ang.getNume().equals(angajat.getNume())) {
                            AngajatiRepository.save(angajat);
                            AngajatiRepository.deleteManDummy(ang.getId_angajat());
                            request.setAttribute("registerSuccess", "Inregistrarea s-a realizat cu succes!");}
                         else request.setAttribute("ocupatieError", "Nu va puteti inregistra ca manager!");
                    }
                     else request.setAttribute("ocupatieError", "Nu va puteti inregistra ca manager!");
                 }
                else request.setAttribute("ocupatieError","Nu exista aceasta pozitie!");



            }
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        } catch (SQLException throwables) {
                throwables.printStackTrace();
            }



        }






    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("Register.jsp").forward(req,resp);
    }

}
