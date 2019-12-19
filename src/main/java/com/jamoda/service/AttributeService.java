package com.jamoda.service;

import com.jamoda.model.Attribute;
import com.jamoda.repository.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeService {
    private AttributeRepository attributeRepository;

    public Attribute saveAttribute(Attribute attribute) {
        return attributeRepository.saveAndFlush(attribute);
    }

    public List<Attribute> findAll() {
        return attributeRepository.findAll();
    }

    public Attribute findById(long id) {
        return attributeRepository.findById(id);
    }

    public Attribute findByName(String name) {
        return attributeRepository.findByName(name);
    }

    @Autowired
    public void setAttributeRepository(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }
}
