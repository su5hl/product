package com.kh.product.domain.svc;

import com.kh.product.domain.dao.ProductDAO;
import com.kh.product.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductSVCImpl implements ProductSVC{
  private final ProductDAO productDAO;

  @Override
  public List<Product> productList() {
    return productDAO.productList();
  }
//등록
  @Override
  public Long save(Product product) {
    return productDAO.save(product);
  }
}
