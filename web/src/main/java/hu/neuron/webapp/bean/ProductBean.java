package hu.neuron.webapp.bean;


import hu.neuron.webapp.dao.ProductHibernateDAO;
import hu.neuron.webapp.model.ProductDTO;
import lombok.*;
import org.primefaces.PrimeFaces;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
@ManagedBean
@SessionScoped
public class ProductBean {
    private String name;
    private String category;
    private Long unit;
    private Long measure;
    private Long purchasePrice;
    private Long salePrice;
    private String description;


    public void saveProduct(){
        ProductDTO productDTO = productDTOBuilder();
        ProductHibernateDAO productHibernateDAO = new ProductHibernateDAO();
        productHibernateDAO.addProduct(productDTO);
        PrimeFaces.current().executeScript("self.close();");

    }

    public ProductDTO productDTOBuilder(){
        return ProductDTO.builder()
                .name(name)
                .category(category)
                .description(description)
                .unit(unit)
                .measure(measure)
                .purchasePrice(purchasePrice)
                .salePrice(salePrice)
                .build();

    }

}
