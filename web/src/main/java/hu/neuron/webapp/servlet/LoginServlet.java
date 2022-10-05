package hu.neuron.webapp.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/secured")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getSession().getAttribute("username").toString();
        String password = req.getSession().getAttribute("password").toString();
        boolean authenticated =(boolean)req.getSession().getAttribute("authenticated");
        PrintWriter out = resp.getWriter();
        out.println("Felhasználónév: "+username +" jelszó: " + password+ " bejelentkezett: " + authenticated);

    }
}
