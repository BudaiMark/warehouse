package hu.neuron.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private String name;
    private String category;
    private Long unit;
    private Long measure;
    private Long purchasePrice;
    private Long salePrice;
    private String description;



}
