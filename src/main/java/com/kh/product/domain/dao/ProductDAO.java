package com.kh.product.domain.dao;

import com.kh.product.domain.entity.Product;

import java.util.List;

public interface ProductDAO {

  List<Product> productList();
  //등록
  Long save(Product product);


}
