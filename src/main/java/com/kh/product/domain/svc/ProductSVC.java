package com.kh.product.domain.svc;

import com.kh.product.domain.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductSVC {

  List<Product> productList();
//등록
  Long padd(Product product);

  Optional<Product> pDatail(Long productId);
  //삭제
  int pDelete(Long productId);
  //수정
  int pUpdate(Long productId, Product product);
}
