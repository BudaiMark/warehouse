package hu.neuron.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class Product {
    private String name;
    private String category;
    private Long unit;
    private Long measure;
    private Long purchasePrice;
    private Long salePrice;
    private String description;



}
