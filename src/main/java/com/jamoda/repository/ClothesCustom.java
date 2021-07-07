package com.jamoda.repository;

import com.jamoda.model.Clothes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClothesCustom {
    Page<Clothes> findByStringsContains(String[] arr, Pageable pageable);
}
