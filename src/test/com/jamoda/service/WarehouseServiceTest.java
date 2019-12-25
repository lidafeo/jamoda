package com.jamoda.service;

import com.jamoda.model.Attribute;
import com.jamoda.model.Clothes;
import com.jamoda.model.Filter;
import com.jamoda.model.Warehouse;
import com.jamoda.repository.CategoryRepository;
import com.jamoda.repository.FilterRepository;
import com.jamoda.repository.WarehouseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class WarehouseServiceTest {

    @Test
    void saveWarehouse() {
        WarehouseService WhServMock = new WarehouseService();
        WarehouseRepository whRepMock = Mockito.mock(WarehouseRepository.class);
        WhServMock.setWarehouseRepository(whRepMock);

        Clothes clothes = new Clothes();
        clothes.setArticle("123");
//        clothes.setPrice(1000);
//        clothes.setVisit(10);
//        clothes.setName("Qwerty");
//        clothes.setPresence(true);
        Warehouse wh = new Warehouse(clothes, 48, 1);

        when(whRepMock.saveAndFlush(wh))
                .thenAnswer(i -> i.getArguments()[0]);
        Assertions.assertEquals(WhServMock.saveWarehouse(wh),
                whRepMock.saveAndFlush(wh));
    }

    @Test
    void findByClothesAndSize() {
        WarehouseService WhServMock = new WarehouseService();
        WarehouseRepository whRepMock = Mockito.mock(WarehouseRepository.class);
        WhServMock.setWarehouseRepository(whRepMock);

        Clothes clothes = new Clothes();
        clothes.setArticle("123");
        Warehouse wh = new Warehouse(clothes, 48, 1);

        Mockito.when(whRepMock.findByClothesAndSize(clothes,
                48)).thenReturn(wh);

        Assertions.assertEquals(
                WhServMock.findByClothesAndSize(clothes, 48),
                whRepMock.findByClothesAndSize(clothes, 48));
    }
}