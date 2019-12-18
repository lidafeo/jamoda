package com.jamoda.repository;

import com.jamoda.model.Attribute;
import com.jamoda.model.AttributeValue;
import com.jamoda.model.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class AttributeValueRepositoryImpl implements AttributeValueCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<AttributeValue> findArticleClothesWithFilter(Map<Attribute, List<String>> filters,
                                                             List<Category> categories) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AttributeValue> query = cb.createQuery(AttributeValue.class);
        Root<AttributeValue> attributeValue = query.from(AttributeValue.class);

        Path<String> attributePath = attributeValue.get("attribute");
        Path<String> valuesPath = attributeValue.get("value");

        List<Predicate> predicates = new ArrayList<>();
        CriteriaBuilder.In<String> inClause = cb.in(valuesPath);
        for (Attribute attribute : filters.keySet()) {
            for(String hhh : filters.get(attribute)) {
                inClause.value(hhh);
            }
            predicates.add(cb.and(cb.equal(attributePath, attribute), inClause));
        }


        Path<String> clothesPath = attributeValue.get("clothes");
        Path<String> categoryPath = clothesPath.get("category").get("nameEn");

        CriteriaBuilder.In<String> inCategory = cb.in(categoryPath);
        Predicate pred;
        if(categories != null && categories.size() != 0) {
            for (Category category : categories) {
                inCategory.value(category.getNameEn());
            }
            pred = cb.and(cb.or(predicates.toArray(new Predicate[predicates.size()])), inCategory);
        }
        else {
            pred = cb.or(predicates.toArray(new Predicate[predicates.size()]));
        }

        query.select(attributeValue)
                .where(pred);
        return entityManager.createQuery(query).getResultList();
    }
}
