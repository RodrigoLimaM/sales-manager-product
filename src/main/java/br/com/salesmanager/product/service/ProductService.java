package br.com.salesmanager.product.service;

import br.com.salesmanager.product.model.Product;
import br.com.salesmanager.product.model.dto.ProductDTO;
import br.com.salesmanager.product.model.mapper.ProductMapper;
import br.com.salesmanager.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    public Product insert(ProductDTO productDTO) {
        return productRepository.insert(productMapper.mapProductDTOToProduct(productDTO));
    }
}
