package com.mohammadali.springboot.service;

import org.MohammadAli.data.ProductDAO;
import org.MohammadAli.data.entities.Product;
import org.MohammadAli.models.Pagination;
import org.MohammadAli.models.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {


    @Autowired
    ProductDAO productDAO;

    @Autowired
    ModelMapper modelMapper;


    @Override
    @Transactional
    public void save(ProductDTO.CREATE productDTO) throws IOException {
        Product product = modelMapper.map(productDTO, Product.class);
        product.setImg(productDTO.getProductImg().getBytes());
        productDAO.save(product);
    }

    @Override
    @Transactional
    public List<ProductDTO.RETRIEVE> findAll(Integer pageNumber, Pagination<ProductDTO.RETRIEVE> pagination) {
        Pageable pageable = Pagination.createPage(pageNumber, Pagination.PAGE_ELEMENT_SIZE);
        Page<Product> productList = productDAO.findAll(pageable);
        pagination.setTotalPages(productList.getTotalPages());
        List<ProductDTO.RETRIEVE> productDTOList = productList
                .stream()
                .map(Product -> modelMapper.map(Product, ProductDTO.RETRIEVE.class))
                .collect(Collectors.toList());
//        new PageImpl<ProductDTO.RETRIEVE>(productDTOList);
        return productDTOList;
    }

    @Override
    @Transactional
    public byte[] retrieveProductImgByID(Long Id) {
        return productDAO.retrieveProductImgByID(Id);
    }

    @Override
    @Transactional
    public ProductDTO.RETRIEVE findProductByID(long productID) {
//        Product product = productDAO.findById(productID).get();
//        Product product = productDAO.findById(productID).orElse(new Product());
        Product product = productDAO.findById(productID).orElseThrow(() -> new RuntimeException("there is no such product"));
        ProductDTO.RETRIEVE map = modelMapper.map(product, ProductDTO.RETRIEVE.class);
        return map;
    }

    @Override
    @Transactional
    public List<ProductDTO.RETRIEVE> findProductByCategory(String category, int pageNumber, Pagination<ProductDTO.RETRIEVE> pagination) {
        Pageable pageable = Pagination.createPage(pageNumber, Pagination.PAGE_ELEMENT_SIZE);
        Page<Product> productList = productDAO.findAllByProductCategory(category, pageable);
        pagination.setTotalPages(productList.getTotalPages());
        return productList.stream().map(product -> modelMapper.map(product, ProductDTO.RETRIEVE.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void remove(Long productID) {
        productDAO.deleteById(productID);
    }

    @Override
    @Transactional
    public List<ProductDTO.RETRIEVE> findProductByBrandOrModelOrNameAndCategory(String searchTerm, String category, int pageNumber, Pagination<ProductDTO.RETRIEVE> pagination) {
        Pageable pageable = Pagination.createPage(pageNumber, Pagination.PAGE_ELEMENT_SIZE);
        Page<Product> productList = productDAO.findProductByModelOrBrandOrNameAndCategory(searchTerm, category, pageable);
        pagination.setTotalPages(productList.getTotalPages());
        return productList.stream().map(product -> modelMapper.map(product, ProductDTO.RETRIEVE.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void update(ProductDTO.CREATE createDTO, Long productID) throws IOException {
        Product product = modelMapper.map(createDTO, Product.class);
        product.setProductID(productID);
        product.setImg(createDTO.getProductImg().getBytes());
        if(product.getImg().length == 0)
            product.setImg(productDAO.retrieveProductImgByID(productID));
        productDAO.save(product);
    }


    @Override
    public int findPageElementsCount(int pageNumber) {
        Pageable pageable = Pagination.createPage(pageNumber , Pagination.PAGE_ELEMENT_SIZE );
        Page<Product> products = productDAO.findAll(pageable);
        return products.getNumberOfElements();
    }











}
