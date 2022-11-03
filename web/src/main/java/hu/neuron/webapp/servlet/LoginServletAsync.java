package hu.neuron.webapp.servlet;

import com.google.gson.Gson;
import hu.neuron.webapp.api.User;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginServletAsync extends HttpServlet {
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("name").trim();
        String password = req.getParameter("password").trim();


        if ("admin".equals(username) && "password".equals(password)) {
            /**
             * Json formátumba adjuk vissza a usert aszinkron kérésnél használva.
             */
            HttpSession session = req.getSession();
            User user = new User(username, password);
            session.setAttribute("user", user);
            String employeeJsonString = this.gson.toJson(user);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(employeeJsonString);


        } else {
            RequestDispatcher rd = req.getRequestDispatcher("login-async.html");
            rd.forward(req, resp);
        }
    }

}
