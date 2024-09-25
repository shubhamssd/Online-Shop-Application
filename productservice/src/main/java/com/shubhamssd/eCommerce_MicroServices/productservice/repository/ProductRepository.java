package com.shubhamssd.eCommerce_MicroServices.productservice.repository;

import com.shubhamssd.eCommerce_MicroServices.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
