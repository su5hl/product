package com.kh.product.domain.svc;

import com.kh.product.domain.dao.ProductDAO;
import com.kh.product.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
  public Long padd(Product product) {
    return productDAO.padd(product);
  }


  @Override
  public Optional<Product> pDatail(Long pid) {
    return productDAO.pDatail(pid);
  }
  //삭제
  @Override
  public int pDelete(Long productId) {
    return productDAO.pDelete(productId);
  }
  @Override
  public int pUpdate(Long productId, Product product) {
    return productDAO.pUpdate(productId,product);
  }
}
