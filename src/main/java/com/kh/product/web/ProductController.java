package com.kh.product.web;

import com.kh.product.domain.entity.Product;
import com.kh.product.domain.svc.ProductSVC;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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


  //등록
  @GetMapping("/add")
  public String addForm(Model model){

    model.addAttribute("saveForm", new SaveForm());
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
//    log.info("상품아이디={}",pid);
//    redirectAttributes.addAttribute("id", pid);
//
//    return "redirect:/products/{id}/detail";
//  }


}