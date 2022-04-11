package com.mohammadali.springboot.service;

import org.MohammadAli.models.UserDTO;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void save(UserDTO.CREATE userDTO) throws IOException;

    List<UserDTO.RETRIEVE> findAll();

    void delete(UserDTO.DELETE deleteDTO);
}
