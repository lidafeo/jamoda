package com.jamoda.service;

import com.jamoda.repository.ClothesRepository;
import com.jamoda.repository.WarehouseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

class SearchServiceTest {

    ClothesRepository clRepMock = Mockito.mock(ClothesRepository.class);

    @Test
    void findClothesByQ() {
        SearchService searchService = new SearchService();
        searchService.setClothesRepository(clRepMock);

        Pageable pageable = PageRequest.of(0, 8);
        String[] str= {"q"};

        Assertions.assertEquals(searchService.findClothesByQ("q", pageable),
                clRepMock.findByStringsContains(str, pageable));
    }
}