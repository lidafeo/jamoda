package com.jamoda.service;

import com.jamoda.model.Clothes;
import com.jamoda.model.Warehouse;
import com.jamoda.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class WarehouseService {
    private WarehouseRepository warehouseRepository;

    public Warehouse saveWarehouse(Warehouse warehouse) {
        return warehouseRepository.saveAndFlush(warehouse);
    }

    public Warehouse findByClothesAndSize(Clothes clothes, int size) {
        return warehouseRepository.findByClothesAndSize(clothes, size);
    }

    @Autowired
    public void setWarehouseRepository(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }
}
