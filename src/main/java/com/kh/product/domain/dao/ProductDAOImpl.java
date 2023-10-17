package com.kh.product.domain.dao;

import com.kh.product.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor

public class ProductDAOImpl implements ProductDAO{

  private final NamedParameterJdbcTemplate template;

  //목록
  @Override
  public List<Product> productList() {
    StringBuffer sql = new StringBuffer();
    sql.append("  select pid, pname, quantity, price ");
    sql.append("    from product ");
    sql.append("order by pid desc");

    List<Product> list = template.query(sql.toString(), BeanPropertyRowMapper.newInstance(Product.class));
    return list;
  }
//조회
  @Override
  public Optional<Product> pDatail(Long pid) {
    StringBuffer sql = new StringBuffer();
    sql.append("select pid,pname,quantity,price ");
    sql.append("  from product ");
    sql.append(" where pid = :id ");

    MyRowMapper myRowMapper = new MyRowMapper();
    try {
      // SQL 파라미터 수동매핑
      Map<String, Long> param = Map.of("id", pid);

      //RowMapper 수동 매핑
      Product product = template.queryForObject(sql.toString(), param, myRowMapper);
      return Optional.of(product);
    }catch(EmptyResultDataAccessException e){
      //조회결과가 없는경우
      return Optional.empty();
    }
  }

//등록
  @Override
  public Long padd(Product product) {
    StringBuffer sql = new StringBuffer();
    sql.append("insert into product(pid,pname,quantity,price) ");
    sql.append("values(product_seq.nextval, :pname , :quantity, :price) ");
    // SQL 파라미터 자동매핑
    SqlParameterSource param = new BeanPropertySqlParameterSource(product);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    template.update(sql.toString(),param,keyHolder,new String[]{"pid"});
    long productId = keyHolder.getKey().longValue();
    return productId;
  }
  @Override
  public int pDelete(Long productId) {
    String sql = "delete from product where pid = :pid";
    int deletedRowCnt = template.update(sql, Map.of("pid", productId));

    return deletedRowCnt;
  }


  @Override
  public int pUpdate(Long productId, Product product) {
    return 0;
  }
}
