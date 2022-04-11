package com.mohammadali.springboot.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.MohammadAli.models.Pagination;
import org.MohammadAli.models.ProductDTO;
import org.MohammadAli.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @GetMapping("/product-list/{category}/{pageNumber}")
    public String showAllProductByCategory(
              @PathVariable("category") String category
            , @PathVariable("pageNumber") int pageNumber
            , @Autowired Pagination<ProductDTO.RETRIEVE> pagination
            , Model model) {

        List<ProductDTO.RETRIEVE> productList;

        if (category.equals("all"))
            productList = productService.findAll(pageNumber - 1 , pagination);
        else
            productList = productService.findProductByCategory(category, pageNumber - 1 , pagination);
        model.addAttribute("beginIndex", Math.max(1, pageNumber - 5));
        model.addAttribute("endIndex", Math.min(pageNumber + 10, pagination.getTotalPages()));
        model.addAttribute("totalPages", pagination.getTotalPages());
        model.addAttribute("currentPageNumber", pageNumber);
        model.addAttribute("productCategory", (category.equals("all") ? "all" : category));
        model.addAttribute("products", productList);
        return "product-list";
        //        PageRequest pageRequest = (category.equals("all")) ? PageRequest.of(page, size) : PageRequest.of(page, size, Sort.by("productName")) ;
    }

    @GetMapping("/view-product-detail/{pageNumber}/{productID}")
    public String showProductDetail(@PathVariable("productID") long productID,@PathVariable("pageNumber") String pageNumber , Model model) {
        String previousPage = pageNumber.split("\\s")[1];
        ProductDTO.RETRIEVE dto = productService.findProductByID(productID);
        model.addAttribute("product", dto);
        model.addAttribute("previousPage", previousPage);
        return "product-detail";
    }
}
