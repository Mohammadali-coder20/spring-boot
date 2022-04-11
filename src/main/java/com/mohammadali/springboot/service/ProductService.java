package com.mohammadali.springboot.service;

import org.MohammadAli.models.Pagination;
import org.MohammadAli.models.ProductDTO;

import java.io.IOException;
import java.util.List;


public interface ProductService {
    void save(ProductDTO.CREATE productDTO) throws IOException;

    List<ProductDTO.RETRIEVE> findAll(Integer  pageNumber , Pagination<ProductDTO.RETRIEVE> pagination);

    byte[] retrieveProductImgByID(Long Id);

    ProductDTO.RETRIEVE findProductByID(long productID);

    List<ProductDTO.RETRIEVE> findProductByCategory(String category , int  pageNumber , Pagination<ProductDTO.RETRIEVE> pagination);

    void remove(Long productID);

    List<ProductDTO.RETRIEVE> findProductByBrandOrModelOrNameAndCategory(String searchTerm, String category , int pageNumber , Pagination<ProductDTO.RETRIEVE> pagination);

    void update(ProductDTO.CREATE createDTO , Long productID) throws IOException;

    int findPageElementsCount(int pageNumber);
}
