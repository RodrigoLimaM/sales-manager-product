package br.com.salesmanager.product.service;

import br.com.salesmanager.product.model.Product;
import br.com.salesmanager.product.model.dto.ProductDTO;
import br.com.salesmanager.product.model.mapper.ProductMapper;
import br.com.salesmanager.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    public Product insert(ProductDTO productDTO) {
        return productRepository.insert(productMapper.mapProductDTOToProduct(productDTO));
    }

    public Optional<Product> findProductById(String productId){
        return productRepository.findById(productId);
    }

    public Boolean checkForAvailability(Integer requestedQuantity, String productId) {
        return this.findProductById(productId)
                .map(product -> this.hasAvailableStock(requestedQuantity, product.getQuantity()))
                .orElse(false);
    }

    private Boolean hasAvailableStock(Integer requestedQuantity, Integer productQuantity) {
        return requestedQuantity <= productQuantity;
    }
}
