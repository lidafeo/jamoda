package com.jamoda.repository;

import com.jamoda.model.Attribute;
import com.jamoda.model.AttributeValue;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class AttributeValueImpl implements AttributeValueCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<AttributeValue> findArticleClothesWithFilter(Map<Attribute, List<String>> filters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AttributeValue> query = cb.createQuery(AttributeValue.class);
        Root<AttributeValue> attributeValue = query.from(AttributeValue.class);

        Path<String> attributePath = attributeValue.get("attribute");
        Path<String> clothesPath = attributeValue.get("clothes");
        Path<String> valuesPath = attributeValue.get("value");

        List<Predicate> predicates = new ArrayList<>();
        CriteriaBuilder.In<String> inClause = cb.in(valuesPath);
        for (Attribute attribute : filters.keySet()) {
            for(String hhh : filters.get(attribute)) {
                inClause.value(hhh);
            }
            predicates.add(cb.and(cb.equal(attributePath, attribute), inClause));
        }
        query.select(attributeValue)
                .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(query).getResultList();
    }
}
