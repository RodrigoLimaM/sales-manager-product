package br.com.salesmanager.product.controller;

import br.com.salesmanager.product.model.Product;
import br.com.salesmanager.product.model.dto.ProductDTO;
import br.com.salesmanager.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductDTO productDTO) {
        return ResponseEntity.ok(productService.insert(productDTO));
    }
}
