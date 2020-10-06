package br.com.salesmanager.product.model.mapper;

import br.com.salesmanager.product.model.Product;
import br.com.salesmanager.product.model.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product mapProductDTOToProduct(ProductDTO productDTO) {
        return Product.builder()
                .name(productDTO.getName())
                .quantity(productDTO.getQuantity())
                .unitaryValue(productDTO.getUnitaryValue())
                .build();
    }
}
