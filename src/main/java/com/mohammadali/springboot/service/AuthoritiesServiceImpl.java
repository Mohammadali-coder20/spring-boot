package com.mohammadali.springboot.service;

import org.MohammadAli.data.AuthoritiesDAO;
import org.MohammadAli.data.entities.Authorities;
import org.MohammadAli.models.AuthoritiesDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthoritiesServiceImpl implements AuthoritiesService{

    @Autowired
    ModelMapper mapper;

    @Autowired
    AuthoritiesDAO authoritiesDAO;

    @Override
    @Transactional
    public void save(AuthoritiesDTO.CREATE authoritiesDTO) throws IOException {
        Authorities authorities = mapper.map(authoritiesDTO , Authorities.class);
        authoritiesDAO.save(authorities);
    }

    @Override
    @Transactional
    public List<AuthoritiesDTO.RETRIEVE> findAll() {
        List<Authorities> all = authoritiesDAO.findAll();
        return all.stream().map(authorities -> mapper.map(authorities , AuthoritiesDTO.RETRIEVE.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(AuthoritiesDTO.DELETE deleteDTO) {
        authoritiesDAO.deleteById(deleteDTO.getAuthoritiesID());
    }
}
