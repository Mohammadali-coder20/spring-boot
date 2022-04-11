package com.mohammadali.springboot.controllers;

import lombok.AllArgsConstructor;
import org.MohammadAli.models.Pagination;
import org.MohammadAli.models.ProductDTO;
import org.MohammadAli.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/product")
@AllArgsConstructor
public class AdminProductController {


    ProductService productService;

    @GetMapping("/add-product/{lastPage}")
    public String addProduct(@ModelAttribute("product") ProductDTO.CREATE createDTO
            , @PathVariable("lastPage") int lastPage
            , @RequestParam(value = "update", required = false) String update
            , Model model) {

        createDTO.setProductStatus("Brand New");
        int pageElements = productService.findPageElementsCount(lastPage - 1);
        int previousPage = (pageElements + 1 > Pagination.PAGE_ELEMENT_SIZE) ? lastPage + 1 : lastPage;
        model.addAttribute("previousPage", previousPage);
        return "add-product";
    }


    @PostMapping("/save-product")
    public String saveProduct(
            @Valid @ModelAttribute("product") ProductDTO.CREATE createDTO
            ,/*@RequestBody(required = false)*/  String pageNumber
            , BindingResult result
            , Long productID) throws IOException {

        if (result.hasErrors())
            return "add-product";
        if (productID != null)
            productService.update(createDTO, productID);
        else
            productService.save(createDTO);
        return "redirect:/admin/product-management/" + pageNumber;
    }

    @GetMapping("/search-product/{pageNumber}")
    public String searchProduct(
            @RequestParam("searchTerm") String searchTerm
            , @PathVariable("pageNumber") int pageNumber
            , @RequestParam("category") String category
            , Pagination<ProductDTO.RETRIEVE> pagination
            , Model model) {

        List<ProductDTO.RETRIEVE> productList;

        if (searchTerm.equals("") /*|| category.equalsIgnoreCase("all")*/)
            productList = productService.findAll(pageNumber - 1, pagination);
        else
            productList = productService.findProductByBrandOrModelOrNameAndCategory(searchTerm, category, pageNumber - 1, pagination);

        model.addAttribute("beginIndex", Math.max(1, pageNumber - 5));
        model.addAttribute("endIndex", Math.min(pageNumber + 10, pagination.getTotalPages()));
        model.addAttribute("totalPages", pagination.getTotalPages());
        model.addAttribute("currentPageNumber", pageNumber);
        model.addAttribute("productCategory", (category.equals("all") ? "all" : category));
        model.addAttribute("products", productList);
        model.addAttribute("search", "search");
        return "product-inventory";
    }

    @RequestMapping(value = "/delete-product/{pageNumber}/{productID}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable("productID") Long productID, @PathVariable("pageNumber") String pageNumber) {
        String previousPage = pageNumber.substring(12);
        productService.remove(productID);
        return "redirect:/admin/product-management/" + previousPage;
    }

    @RequestMapping(value = "/update-product/{pageNumber}/{productID}", method = RequestMethod.GET)
    public String findProductForUpdateProduct(@PathVariable("productID") Long productID, @PathVariable("pageNumber") String pageNumber, Model model) {
        String previousPage = pageNumber.substring(12);
        ProductDTO.RETRIEVE productByID = productService.findProductByID(productID);
        productByID.init();
        model.addAttribute("product", productByID);
        model.addAttribute("update", "update");
        model.addAttribute("previousPage", previousPage);
        return "add-product";
    }
}
