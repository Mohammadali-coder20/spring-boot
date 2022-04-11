package com.mohammadali.springboot.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Supplier;

@Data
@ToString
@Component
@NoArgsConstructor
public class Pagination<T> {

    private Collection<T> pageElements;

    private int  totalPages;

    public static final int PAGE_ELEMENT_SIZE = 6;

    public <E extends Collection<T>> Pagination(Supplier<E> collectionProducer){
        this.pageElements = collectionProducer.get();
    }

//    public  Collection<T> createPageElements(Supplier<? extends Collection<T>> collectionProducer){
//        return collectionProducer.get();
//    }
//
//    public Pagination(Supplier<? extends >){
//        this.pageElements = createPageElements()
//    }

    public static Pageable createPage(int pageNumber , int pageSize ){
        return PageRequest.of(pageNumber , pageSize);
    }
}
