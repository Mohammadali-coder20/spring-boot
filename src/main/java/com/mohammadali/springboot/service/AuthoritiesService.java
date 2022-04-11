package com.mohammadali.springboot.service;

import org.MohammadAli.models.AuthoritiesDTO;

import java.io.IOException;
import java.util.List;

public interface AuthoritiesService {


    void save(AuthoritiesDTO.CREATE authoritiesDTO) throws IOException;

    List<AuthoritiesDTO.RETRIEVE> findAll();

    void delete(AuthoritiesDTO.DELETE deleteDTO);
}
