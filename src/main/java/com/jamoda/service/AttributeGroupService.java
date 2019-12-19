package com.jamoda.service;

import com.jamoda.model.AttributeGroup;
import com.jamoda.repository.AttributeGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeGroupService {

    private AttributeGroupRepository attributeGroupRepository;

    public AttributeGroup saveAttributeGroup(AttributeGroup attributeGroup) {
        return attributeGroupRepository.saveAndFlush(attributeGroup);
    }

    public List<AttributeGroup> findAll() {
        return attributeGroupRepository.findAll();
    }

    public AttributeGroup findById(long id) {
        return attributeGroupRepository.findById(id);
    }

    public AttributeGroup findByName(String name) {
        return attributeGroupRepository.findByName(name);
    }

    @Autowired
    public void setAttributeGroupRepository(AttributeGroupRepository attributeGroupRepository) {
        this.attributeGroupRepository = attributeGroupRepository;
    }
}
