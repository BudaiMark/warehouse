package hu.neuron.webapp.servlet;


import com.google.gson.Gson;
import hu.neuron.webapp.dao.ProductDAO;
import hu.neuron.dto.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductAsyncServlet extends HttpServlet {
    private Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        * Lekérdezzük az összes terméket, beleírjuk egy Json objektumba.
        */
        //HttpSession session = req.getSession()
        List<Product> products = new ProductDAO().getAllProducts();
        String productJsonString = this.gson.toJson(products);


        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(productJsonString);

    }

}
