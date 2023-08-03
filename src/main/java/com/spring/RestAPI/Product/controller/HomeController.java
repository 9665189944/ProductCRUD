package com.spring.RestAPI.Product.controller;

import com.spring.RestAPI.Product.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class HomeController {
    List<Product> productList=new ArrayList<>();

    {
        productList .add(new Product(1,"Mobile",2000.0));
        productList.add(new Product(2,"laptop",3000.0));
    }

    @GetMapping("/")
    public String getProduct(Model model) {
        model.addAttribute("products", productList);
        return "home";
    }
    @GetMapping("/addproduct")
    public String showProductFrom(Model model) {
        model.addAttribute("product", new Product());
        return "addproduct.html";
    }

    @PostMapping("/saveproduct")
    public String addBook(Product product) {
    productList.add(product);
        return "redirect:/";
    }

    @GetMapping("/deleteproduct/{id}")
    public String deletedProduct(@PathVariable int id) {
        Iterator<Product> itr = productList.iterator();
        while (itr.hasNext()) {
            if (itr.next().getProductID() == id) {
                itr.remove();
            }
        }
        return "redirect:/";

    }

}
