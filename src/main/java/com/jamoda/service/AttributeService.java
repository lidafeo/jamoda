package com.jamoda.service;

import com.jamoda.model.Attribute;
import com.jamoda.repository.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeService {
    @Autowired
    private AttributeRepository attributeRepository;

    public List<Attribute> findAll() {
        return attributeRepository.findAll();
    }
    public Attribute findById(long id) {
        return attributeRepository.findById(id);
    }
}
