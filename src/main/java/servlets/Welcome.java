package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/")
public class Welcome extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String button = request.getParameter("submit");



        if("Login".equals(button) )
        {
            RequestDispatcher req = request.getRequestDispatcher("Login.jsp");
            req.forward(request, response);
        }
        else if("Register".equals(button))
        {
            RequestDispatcher req = request.getRequestDispatcher("Register.jsp");
            req.forward(request, response);
        }
    }
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("Welcome.jsp").forward(req,resp);
    }
}
