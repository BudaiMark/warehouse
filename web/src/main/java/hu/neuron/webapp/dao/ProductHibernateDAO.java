package hu.neuron.webapp.dao;

import hu.neuron.webapp.model.ProductResponse;
import hu.neuron.webapp.model.ProductDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProductHibernateDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("warehouse2");

    /*
    Builder segítségével felépítjük az objektumot, majd hozzáadjuk az adatbázishoz
     */

    public void addProduct(ProductDTO product) {

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    /*
    Lekérdezzük hány darab termék van az adatbázisban.
     */
    public Long countAllProducts() {

        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT count (p) FROM ProductDTO p", Long.class).getSingleResult();
        } finally {
            em.close();
        }
    }

    /*
    Lekérdezzük az adatbázisban szereplő termékeket.
     */
    public ProductResponse getAllProducts(long page) {

        EntityManager em = emf.createEntityManager();
        /*
        Felhasználjuk a termékek számát, az oldalváltás matt van rá szükség.
         */
        Long count = countAllProducts();
        TypedQuery<ProductDTO> productDTOTypedQuery;
        List<ProductDTO> productDTOS;
        if(page<0){
            page = 0;
        }
        try {
            /*
            Amennyiben a page száma kissebb mint a megszámolt termék, akkor visszatérünk az eredménnyel, ami illik az oldalhoz
             */
            //page megszüntetése

                productDTOTypedQuery = em.createQuery("SELECT p FROM ProductDTO p", ProductDTO.class);
                productDTOS = productDTOTypedQuery.setFirstResult((int) page).setMaxResults(1).getResultList();
                return new ProductResponse(productDTOS, count);

        } finally {
            em.close();
        }
    }
/*
Kategória és mértékegység alapján kérdezzük le az elemek számát.
 */

    public Long countProductsByCategoryAndMeasure(String categoryName, Long measure) {

        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> countProductTypedQuery = em.createQuery("SELECT count (p) FROM ProductDTO p WHERE p.category = ?1 AND p.measure = ?2", Long.class);
            countProductTypedQuery.setParameter(1, categoryName);
            countProductTypedQuery.setParameter(2, measure);
            return countProductTypedQuery.getSingleResult();
        } finally {
            em.close();
        }
    }

    /*
    Lekérdezzük a termékeket kategória és mértékegység alapján.
     */
    public ProductResponse getProductsByCategoryAndMeasure(String categoryName, Long measure, int page) {

        EntityManager em = emf.createEntityManager();
        Long count = countProductsByCategoryAndMeasure(categoryName, measure);
        List<ProductDTO> productDTOS;
        if(page<0){
            page = 0;
        }
        try {
            TypedQuery<ProductDTO> productDTOTypedQuery = em.createQuery("SELECT p FROM ProductDTO p WHERE p.category = ?1 AND p.measure = ?2", ProductDTO.class);
            productDTOTypedQuery.setParameter(1, categoryName);
            productDTOTypedQuery.setParameter(2, measure);

            productDTOS = productDTOTypedQuery.setFirstResult(page).setMaxResults(1).getResultList();
            return new ProductResponse(productDTOS, count);



        } finally {
            em.close();
        }
    }

    /*
    Kategória alapján visszaadjuk a termékek számát.
     */
    public Long countProductsByCategory(String categoryName) {

        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> countProductTypedQuery = em.createQuery("SELECT count (p) FROM ProductDTO p WHERE p.category = ?1", Long.class);
            countProductTypedQuery.setParameter(1, categoryName);
            return countProductTypedQuery.getSingleResult();
        } finally {
            em.close();
        }
    }

    /*
    Kategória alapján visszaadjuk a termékeket.
     */
    //
    public ProductResponse getProductsByCategory(String categoryName, Integer page) {

        EntityManager em = emf.createEntityManager();
        Long count = countProductsByCategory(categoryName);
        List<ProductDTO> productDTOS;
        if(page<0){
            page = 0;
        }
        try {
            TypedQuery<ProductDTO> productDTOTypedQuery = em.createQuery("SELECT p FROM ProductDTO p WHERE (?1 IS NULL OR p.category = ?1 )", ProductDTO.class);

                productDTOS = productDTOTypedQuery.setParameter(1, categoryName).setFirstResult(page).setMaxResults(1).getResultList();
                return new ProductResponse(productDTOS, count);

        } finally {
            em.close();
        }
    }
    /*
    Mértékegység alapján visszaadjuk a termékek számát.
    */

    public Long countProductsByMeasure(Long measure) {

        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> countProductTypedQuery = em.createQuery("SELECT count (p) FROM ProductDTO p WHERE p.measure = ?1", Long.class);
            countProductTypedQuery.setParameter(1, measure);
            return countProductTypedQuery.getSingleResult();
        } finally {
            em.close();
        }
    }
      /*
       Mértékegység alapján visszaadjuk a termékeket.
        */

    public ProductResponse getProductsByMeasure(Long measure, Integer page) {

        EntityManager em = emf.createEntityManager();
        Long count = countProductsByMeasure(measure);
        List<ProductDTO> productDTOS;
        if(page<0){
            page = 0;
        }
        try {
            TypedQuery<ProductDTO> productDTOTypedQuery = em.createQuery("SELECT p FROM ProductDTO p WHERE p.measure = ?1", ProductDTO.class);

            productDTOS = productDTOTypedQuery.setParameter(1, measure).setFirstResult(page).setMaxResults(1).getResultList();
            return new ProductResponse(productDTOS,count);

        } finally {
            em.close();
        }
    }

    /*
       Lekérdezzük a kategóriákat.
        */
    public List<String> getCategories() {

        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p.category FROM ProductDTO p group by p.category", String.class).getResultList();
        } finally {
            em.close();
        }
    }

    /*
    Lekérdezzük az összes mértékegységet.
     */
    public List<Long> getMeasures() {

        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p.measure FROM ProductDTO p group by p.measure", Long.class).getResultList();
        } finally {
            em.close();
        }
    }

}
