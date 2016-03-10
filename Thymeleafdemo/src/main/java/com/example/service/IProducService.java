package com.example.service;
import java.util.List;

import com.example.domain.*;


public interface IProducService {
	List<Product> listProducts();
	Product getProductById(Integer id);
	 
    Product saveProduct(Product product);
 
    void deleteProduct(Integer id);
}
