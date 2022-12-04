package hu.neuron.webapp.bean;


import hu.neuron.webapp.dao.ProductHibernateDAO;
import hu.neuron.webapp.model.ProductDTO;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import java.util.List;

@ManagedBean
@RequestScoped
@Getter
@Setter
public class ProductResponseBean {

    public static long page = 0;
    public static long max_page;
    private List<ProductDTO> productResponse;

    public String getAllProducts() {
        ProductHibernateDAO productHibernateDAO = new ProductHibernateDAO();
        productResponse = productHibernateDAO.getAllProducts(page).getProductDTOS();
        max_page = productHibernateDAO.getAllProducts(page).getNumberOfProducts();

        return "products";
    }

    public String next() {
        if (page < max_page) {
            page++;
            getAllProducts();
        }
        return "products";
    }

    public String previous() {
        if (page >=0) {
            page--;
            getAllProducts();
        }
        return "products";
    }


}
