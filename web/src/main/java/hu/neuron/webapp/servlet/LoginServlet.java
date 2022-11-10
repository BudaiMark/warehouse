package hu.neuron.webapp.servlet;


import hu.neuron.dto.User;
import hu.neuron.dto.Product;

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
        /*
         * Lekezeljük a username, password értékében szereplő esetleges whitespaceket.
         */
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();

        /*
         *  Amennyiben megegyezik a felhasználói név "admin"-nal és a jelszó "password"-al, akkor létrehozunk egy listát
         *  , majd hozzáadunk két terméket, illetve beállítjuk session attribútumnak, amikre szükség lesz.
         */
        if ("admin".equals(username) && "password".equals(password)) {
            List<Product> products = new ArrayList<>();
            //mock terméket
            products.add(new Product("Edzőpóló", "Póló", 5L, 400L, 3000L, 5000L, "Jó minőségű edzőpóló"));
            products.add(new Product("Utcaipóló", "Póló", 4L, 400L, 2000L, 4000L, "Jó minőségű póló"));

            //Létrehozzuk a session és beállítjuk az attribútumait.
            HttpSession session = req.getSession();
            User user = new User(username, password);
            session.setAttribute("user", user);
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            session.setAttribute("authenticated", true);
            session.setAttribute("productList", products);
            resp.sendRedirect("dashboard");


        }
        /*
         * Error flaggel jelezzük ha nem jó a belépési azonosító, visszairányítjuk a bejelentkező képernyőre.
         */
        else {
            req.setAttribute("error", true);
            RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
            rd.forward(req, resp);
        }
    }

}
