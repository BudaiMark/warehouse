package hu.neuron.webapp.servlet;

import com.google.gson.Gson;
import hu.neuron.dto.Product;
import hu.neuron.webapp.dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Requestet továbbküldjük.
        req.getRequestDispatcher("/WEB-INF/pages/products.jsp").forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        *  A requestbodyból kiolvassuk a Json objektumot, majd létrehozunk belőle egy Productot, amit lementünk
        *  az adatbázisba.
        */
        String requestData = req.getReader().lines().collect(Collectors.joining());
        Gson gson = new Gson();
        Product obj = gson.fromJson(requestData, Product.class);
        new ProductDAO().insertProductTable(obj);
    }
}
