package com.kh.product.domain.dao;

import com.kh.product.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor

public class ProductDAOImpl implements ProductDAO{

  private final NamedParameterJdbcTemplate template;

  @Override
  public List<Product> productList() {
    StringBuffer sql = new StringBuffer();
    sql.append("  select pid, pname, quantity, price ");
    sql.append("    from product ");
    sql.append("order by pid desc");


    List<Product> list = template.query(sql.toString(), BeanPropertyRowMapper.newInstance(Product.class));
    return list;
  }

  @Override
  public Long save(Product product) {
    StringBuffer sql = new StringBuffer();
    sql.append("insert into product(pid,pname,quantity,price) ");
    sql.append("values(product_seq.nextval, :pname , :quantity, :price) ");

    // SQL 파라미터 자동매핑
    SqlParameterSource param = new BeanPropertySqlParameterSource(product); //
    KeyHolder keyHolder = new GeneratedKeyHolder();
    template.update(sql.toString(),param,keyHolder,new String[]{"pid"});

    long productId = keyHolder.getKey().longValue();    //상품아이디
    return productId;
  }

}
