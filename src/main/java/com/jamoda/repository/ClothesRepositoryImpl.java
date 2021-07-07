package com.jamoda.repository;

import com.jamoda.model.Clothes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClothesRepositoryImpl implements ClothesCustom{

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Page<Clothes> findByStringsContains(String[] arr, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Clothes> query = cb.createQuery(Clothes.class);
        Root<Clothes> clothesRoot = query.from(Clothes.class);

        Path<String> namePath = clothesRoot.get("name");
        Path<String> categoryPath = clothesRoot.get("category");
        Path<String> categoryNamePath = categoryPath.get("nameRus");

        List<Predicate> predicates = new ArrayList<>();
        for (String str : arr) {
            predicates.add(cb.or(cb.like(namePath, "%" + str.trim() + "%"), cb.like(categoryNamePath, "%" + str.trim() + "%")));
        }

        query.select(clothesRoot)
                .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
        //return entityManager.createQuery(query).setFirstResult(pageable.getPageNumber() * pageable.getPageSize()).setMaxResults(pageable.getPageSize()).getResultList();

        List<Clothes> result = entityManager.createQuery(query).getResultList();
        int start = (int) (pageable.getOffset() > result.size() ? result.size() :  pageable.getOffset());
        int end = (start + pageable.getPageSize()) > result.size() ? result.size() : (start + pageable.getPageSize());
        Page<Clothes> pages = new PageImpl<Clothes>(result.subList(start, end), pageable, result.size());
        return pages;
    }
}
