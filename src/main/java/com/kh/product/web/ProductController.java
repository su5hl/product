package com.kh.product.web;

import com.kh.product.domain.entity.Product;
import com.kh.product.domain.svc.ProductSVC;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
  private final ProductSVC productSVC;
//목록
  @GetMapping
  public String pList(Model model) {

    List<Product> list = productSVC.productList();
    List<ListForm> all = new ArrayList<>();
    for (Product product : list) {
      ListForm allList = new ListForm();
      allList.setPid(product.getPid());
      allList.setPname(product.getPname());
      allList.setQuantity(product.getQuantity());
      allList.setPrice(product.getPrice());
      all.add(allList);
    }
    model.addAttribute("all", all);

    return "product/list";
  }

  //조회
  @GetMapping("/{id}/detail")
  public String pDatail(
      @PathVariable("id") Long  id,
      Model model){
    //상품조회
    Optional<Product> findedProduct = productSVC.pDatail(id);
    Product product = findedProduct.orElseThrow();

    DetailForm detailForm = new DetailForm();
    detailForm.setPid(product.getPid());
    detailForm.setPname(product.getPname());
    detailForm.setQuantity(product.getQuantity());
    detailForm.setPrice(product.getPrice());

    model.addAttribute("detailForm",detailForm);
    return "product/detail";
  }


  //등록
  @GetMapping("/add")
  public String padd(Model model){
    model.addAttribute("saveForm", new SaveForm());
    return "product/add";
  }

  //등록처리
  @PostMapping("/add")
  public String add(
      @Valid @ModelAttribute SaveForm saveForm,
      RedirectAttributes redirectAttributes
  ){
 //상품등록
    Product product = new Product();

    product.setPname(saveForm.getPname());
    product.setQuantity(saveForm.getQuantity());
    product.setPrice(saveForm.getPrice());
    Long pid = productSVC.padd(product);
    redirectAttributes.addAttribute("id", pid);

    return "redirect:/products/{id}/detail";
  }

  //삭제
  @DeleteMapping("/{id}")
  public String pDelete(@PathVariable("id") Long id){

    int deletedRowCnt = productSVC.pDelete(id);

    return "redirect:/products";
  }

  //수정
  @GetMapping("/{id}")
  public String pUpdate(){

    return "product/update";
  }
}