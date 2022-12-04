package hu.neuron.webapp.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Table(name = "product")
public class ProductDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @EqualsAndHashCode.Include private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;
    @Column(name = "unit")
    private Long unit;
    @Column(name = "measure")
    private Long measure;
    @Column(name = "purchaseprice")
    private Long purchasePrice;
    @Column(name = "saleprice")
    private Long salePrice;
    @Column(name = "description")
    private String description;



}