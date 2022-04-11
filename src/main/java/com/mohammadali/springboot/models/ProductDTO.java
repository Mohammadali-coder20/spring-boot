package com.mohammadali.springboot.models;


import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;


@Data
@ToString
@Component
@AllArgsConstructor
@NoArgsConstructor
//@Accessors(chain = true)
//@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {

    @NotEmpty
    @Size(min = 3 , max = 20)
    private String productName;

    @NotEmpty
    @Size(min = 3 , max = 20)
    private String productBrand;

    @NotEmpty
    @Size(min = 3 , max = 20)
    private String productModel;

    @NotEmpty
    private String productDescription;

    @NotNull
    @Range(min = 0)
    private Double productPrice;

    @NotNull
    @Range(min = 0)
    private Double unitInStock;

    @NotNull
    @Range(min = 0)
    private Double discount;

    @NotEmpty
    @Size(min = 3 , max = 20)
    private String productStatus;

    @NotEmpty
    private String productCategory;

    public enum ProductCategory{

        LAPTOP("Laptop","Laptop"),
        MOBILE("2","Mobile"),
        TV("3","Tv"),
        CAMERA("4","Camera"),
        REFRIGERATOR("5", "Refrigerator"),
        TABLET("6", "Tablet"),
        MICROOVEN("7","MicroOven"),
        ROUTER("8","Router"),
        GAMING("9","GAMING"),
        DVDPLAYER("10","DvdPlayer"),
        FAN("11","Fan"),
        PRINTER("12","Printer"),
        DESKTOP("13","Desktop"),
        HOME("HOME","HOME");


        private String key;
        private String value;

        ProductCategory(String key , String value){
            this.key = key;
            this.value = value;
        }
    }



    @Setter
    @Getter
    public static class CREATE extends ProductDTO{


        private Map<String,String> categoryList;

        private MultipartFile productImg;

        public CREATE(){
            super();
            categoryList = new HashMap<>();
            categoryList.put("Laptop","Laptop");
            categoryList.put("Mobile","Mobile");
            categoryList.put("TV","TV");
            categoryList.put("Gaming","Gaming");
            categoryList.put("Mobile Accessory","Mobile Accessory");
            categoryList.put("House","House");
        }
    }

    @Data
    @AllArgsConstructor
    public static class DELETE{

        private Long productID;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    public static class RETRIEVE extends ProductDTO{

        private byte[] img;

        private Map<String,String> categoryList;

        private Long productID;

        private MultipartFile productImg;

        public void init(){
            categoryList = new HashMap<>();
            categoryList.put("Laptop","Laptop");
            categoryList.put("Mobile","Mobile");
            categoryList.put("TV","TV");
            categoryList.put("Gaming","Gaming");
            categoryList.put("Mobile Accessory","Mobile Accessory");
            categoryList.put("House","House");
        }

    }


}
