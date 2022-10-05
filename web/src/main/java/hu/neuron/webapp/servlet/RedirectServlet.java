package hu.neuron.webapp.servlet;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();

        if(username.equals("admin") && password.equals("password")){

            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            session.setAttribute("authenticated", true);
            resp.sendRedirect("secured");

        }else{
            RequestDispatcher rd = req.getRequestDispatcher("login.html");
            rd.forward(req,resp);
        }
    }

}
