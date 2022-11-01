package hu.neuron.webapp.servlet;


import hu.neuron.webapp.api.User;
import hu.neuron.model.Product;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;





public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();

        if("admin".equals(username) && "password".equals(password)){

            HttpSession session = req.getSession();
            User user = new User(username, password);
            session.setAttribute("user", user);
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            session.setAttribute("authenticated", true);
            List<Product> products = new ArrayList<>();
            resp.sendRedirect("secured");


        }else{

            req.setAttribute("error", true);
            RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
            rd.forward(req,resp);
        }
    }

}
