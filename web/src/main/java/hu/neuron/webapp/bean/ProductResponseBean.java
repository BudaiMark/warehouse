package hu.neuron.webapp.bean;


import hu.neuron.webapp.dao.ProductHibernateDAO;
import hu.neuron.webapp.model.ProductDTO;
import hu.neuron.webapp.model.ProductResponse;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.util.List;

@ManagedBean
@SessionScoped
@Getter
@Setter
public class ProductResponseBean {

    public static long page = 0;
    public static long max_page;
    private List<ProductDTO> productResponse;
    private List<String> categories;
    private List<Long> measures;
    private String selectedCategory;
    private String selectedMeasure;
    private static final String ALL_VALUE = "all";

    /**
     *    Az products.xhtml oldal betöltésekor ez a függvény fut le, ami lekérdezi az adatbázisból az összes terméket,
     *         a lapozhatóság kedvéért haszáljuk a max_page változót, illetve a page változót aktuális termék megjelenítéséhez.
     * @return visszatér a xhtml oldalra.
     */
    // Nem inicializálom, hanem ha null az értéke dc selectnél all
    public String getAllProducts() {
        ProductHibernateDAO productHibernateDAO = new ProductHibernateDAO();
        selectedCategory = ALL_VALUE;
        selectedMeasure = ALL_VALUE;
        ProductResponse response = productHibernateDAO.getAllProducts(page);
        productResponse = response.getProductDTOS();
        max_page = response.getNumberOfProducts();
        categories = productHibernateDAO.getCategories();
        measures = productHibernateDAO.getMeasures();

        return "products";
    }

    public void getSortedProducts() {
        ProductHibernateDAO productHibernateDAO = new ProductHibernateDAO();
        /*
        Amennyiben a category, illetve a measure legörtülő lista szűrést tartalmaz, akkor a lekérdezzük az adatbázisból azokat a termékeket,
        amik a megszorításoknak eleget tesznek.(Egyezik a category, illetve measure típusa.)
         */
        if (!"all".equals(selectedCategory) && !"all".equals(selectedMeasure)) {
            ProductResponse response = productHibernateDAO.getProductsByCategoryAndMeasure(selectedCategory,Long.valueOf(selectedMeasure), (int) page);
            productResponse = response.getProductDTOS();
            max_page = response.getNumberOfProducts();
        }
        /*
        Abban az esetben, ha csak a kategória tartalmaz szűrést, azokat az adatokat kérdezzük le az adatbázisból, ami az adott category-val rendelkezik.
         */
        else if (!"all".equals(selectedCategory)) {
            ProductResponse response = productHibernateDAO.getProductsByCategory(selectedCategory, (int) page);
            productResponse = response.getProductDTOS();
            max_page = response.getNumberOfProducts();

        }
        /*
        Abban az esetben, ha csak a measure tartalmaz szűrést, azokat az adatokat kérdezzük le az adatbázisból, ami az adott measure-el rendelkezik.
         */
        else if (!"all".equals(selectedMeasure)) {
            ProductResponse response = productHibernateDAO.getProductsByMeasure(Long.valueOf(selectedMeasure), (int) page);
            productResponse = response.getProductDTOS();
            max_page = response.getNumberOfProducts();

        }
        /*
            Ha nem rendelkezik szűréssel az adott listázás, akkor az összes termék lesz kilistázva.
         */
        else {
            ProductResponse response = productHibernateDAO.getAllProducts(page);
            productResponse = response.getProductDTOS();
            max_page = response.getNumberOfProducts();

        }
    }

    /*
    Következő termékre léptet.
     */
    public void next() {
        if (page < max_page - 1) {
            page++;
            getSortedProducts();
        }
    }
    /*
       Előző termékre léptet.
        */
    public void previous() {
        if (page > 0) {
            page--;
            getSortedProducts();
        }
    }
    /*
    Frissítí a termékek listáját a szűrésnek megfelelően.
     */
    public void refresh() {
        page = 0;
        getSortedProducts();
    }

}
