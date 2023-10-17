package com.kh.product.domain.dao;


import com.kh.product.domain.entity.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MyRowMapper implements RowMapper<Product> {
  @Override
  public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
    Product product = new Product();
    product.setPid(rs.getLong("pid"));
    product.setPname(rs.getString("pname"));
    product.setPrice(rs.getLong("price"));
    product.setQuantity(rs.getLong("quantity"));

    return product;
  }
}