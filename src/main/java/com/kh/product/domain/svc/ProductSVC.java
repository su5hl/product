package com.kh.product.domain.svc;

import com.kh.product.domain.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductSVC {

  List<Product> productList();
//등록
  Long save(Product product);
}
