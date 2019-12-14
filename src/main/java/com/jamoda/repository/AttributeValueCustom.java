package com.jamoda.repository;

import com.jamoda.model.Attribute;
import com.jamoda.model.AttributeValue;

import java.util.List;
import java.util.Map;

public interface AttributeValueCustom{
    List<AttributeValue> findArticleClothesWithFilter(Map<Attribute, List<String>> filters);
}
