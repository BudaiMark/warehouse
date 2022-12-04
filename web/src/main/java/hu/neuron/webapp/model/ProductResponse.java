package hu.neuron.webapp.model;

import lombok.*;


import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class ProductResponse {
    List<ProductDTO> productDTOS;
    Long numberOfProducts;

}
