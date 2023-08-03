package com.spring.RestAPI.Product.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    private int productID;
    private String productName;
    private  double productPrice;
}
