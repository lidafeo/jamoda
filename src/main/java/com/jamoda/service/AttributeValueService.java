package com.jamoda.service;

import com.jamoda.model.AttributeValue;
import com.jamoda.repository.AttributeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeValueService {
    @Autowired
    private AttributeValueRepository attributeValueRepository;

    public AttributeValue saveAttributeValue(AttributeValue attributeValue) {
        return attributeValueRepository.save(attributeValue);
    }

    public AttributeValue findById(long id) {
        return attributeValueRepository.findById(id);
    }
}
