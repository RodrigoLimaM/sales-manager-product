package br.com.salesmanager.product.repository;

import br.com.salesmanager.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
