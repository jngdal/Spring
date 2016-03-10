package com.example.repositories;
import org.springframework.data.repository.CrudRepository;

import com.example.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
}
