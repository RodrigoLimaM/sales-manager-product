package br.com.salesmanager.product.controller;

import br.com.salesmanager.product.model.Product;
import br.com.salesmanager.product.model.dto.ProductDTO;
import br.com.salesmanager.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

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

    @PutMapping("/subtractQuantity/{productId}")
    public ResponseEntity<Product> subtractQuantity(@PathVariable String productId,
                                                       @RequestParam Integer quantity) {
        return productService.updateQuantity(productId, quantity)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/unitaryValue/{productId}")
    public ResponseEntity<BigDecimal> getUnitaryValue(@PathVariable String productId) {
        return productService.getUnitaryValue(productId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/availability/{productId}")
    public ResponseEntity<Boolean> hasAvailableStock(@RequestParam Integer requestedQuantity,
                                                     @PathVariable String productId) {
        return ResponseEntity.ok(productService.checkForAvailability(requestedQuantity, productId));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return productService.findAll()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}
