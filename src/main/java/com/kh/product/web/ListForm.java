package com.kh.product.web;

import lombok.Data;

@Data
public class ListForm {
  private Long pid;
  private String pname;
  private Long quantity;
  private Long price;
}
