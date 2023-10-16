package com.kh.product.web;

import com.kh.product.domain.entity.Product;
import com.kh.product.domain.svc.ProductSVC;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
  private final ProductSVC productSVC;

  @GetMapping
  public String productList(Model model) {

    List<Product> list = productSVC.productList();
    List<ListForm> all = new ArrayList<>();
    for (Product product : list) {
      ListForm allForm = new ListForm();
      allForm.setPid(product.getPid());
      allForm.setPname(product.getPname());
      all.add(allForm);
    }
    model.addAttribute("all", all);

    return "product/list";
  }

  //조회
  @GetMapping("/{id}/detail")  //GET http://localhost:9080/products/1/detail
  public String findById(
      @PathVariable("id") Long id,
      Model model){
    //상품조회
    Optional<Product> findedProduct = productSVC.productList(id);
    Product product = findedProduct.orElseThrow(); // optional에 product가 있으면 값을 가져오고 product없으면 예외발생

    DetailForm detailForm = new DetailForm();
    detailForm.setPid(product.getPid());
    detailForm.setPname(product.getPname());
    detailForm.setQuantity(product.getQuantity());
    detailForm.setPrice(product.getPrice());

    model.addAttribute("detailForm",detailForm);
    return "product/detailForm";
  }
  //등록
  @GetMapping("/add")
  public String addForm(Model model){

    return "product/add";
  }
//
//  //등록처리
//  @PostMapping("/add")
//  public String add(
//      @Valid @ModelAttribute SaveForm saveForm,
//      RedirectAttributes redirectAttributes
//  ){
//
// //상품등록
//    Product product = new Product();

//    product.setPname(saveForm.getPname());
//    product.setQuantity(saveForm.getQuantity());
//    product.setPrice(saveForm.getPrice());
//    Long pid = productSVC.save(product);
//
//
//    return ;
//  }


}