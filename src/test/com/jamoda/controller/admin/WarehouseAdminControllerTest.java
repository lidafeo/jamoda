package com.jamoda.controller.admin;

import com.jamoda.model.Clothes;
import com.jamoda.model.Warehouse;
import com.jamoda.service.ClothesService;
import com.jamoda.service.WarehouseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

class WarehouseAdminControllerTest {

    WarehouseService warehouseService = Mockito.mock(WarehouseService.class);
    ClothesService clothesService = Mockito.mock(ClothesService.class);
    Model model = Mockito.mock(Model.class);

    @Test
    void pageAddToWarehouse() {
        WarehouseAdminController warehouseAdminController = new WarehouseAdminController() ;

        Assertions.assertNotNull(warehouseAdminController.pageAddToWarehouse(model));
        Assertions.assertEquals( "admin/addToWarehouse",
                warehouseAdminController.pageAddToWarehouse(model));
    }

    @Test
    void addGroup() {
        WarehouseAdminController warehouseAdminController = new WarehouseAdminController();
        warehouseAdminController.setWarehouseService(warehouseService);
        warehouseAdminController.setClothesService(clothesService);
        Warehouse warehouse = new Warehouse();
        warehouse.setCount(10);
        Clothes clothes = new Clothes();
        clothes.setName("qwer");

        Mockito.when(clothesService.findByArticle("qwerty")).thenReturn(clothes);

        Assertions.assertNotNull(warehouseAdminController.addGroup(
                warehouse, null, model));
        Assertions.assertEquals( "admin/addToWarehouse",
                warehouseAdminController.addGroup(
                        warehouse, null, model));
    }

    @Test
    void addGroup1() {
        WarehouseAdminController warehouseAdminController = new WarehouseAdminController();
        warehouseAdminController.setWarehouseService(warehouseService);
        warehouseAdminController.setClothesService(clothesService);
        Warehouse warehouse = new Warehouse();
        warehouse.setCount(10);
        Clothes clothes = new Clothes();
        clothes.setName("qwer");

        Mockito.when(clothesService.findByArticle("qwerty")).thenReturn(clothes);
        Mockito.when(warehouseService.findByClothesAndSize(
                clothes, warehouse.getSize())).thenReturn(warehouse);

        Assertions.assertNotNull(warehouseAdminController.addGroup(
                warehouse, "qwerty", model));
        Assertions.assertEquals( "admin/addToWarehouse",
                warehouseAdminController.addGroup(
                        warehouse, "qwerty", model));
    }

    @Test
    void addGroup2() {
        WarehouseAdminController warehouseAdminController = new WarehouseAdminController();
        warehouseAdminController.setWarehouseService(warehouseService);
        warehouseAdminController.setClothesService(clothesService);
        Warehouse warehouse = new Warehouse();
        warehouse.setCount(10);
        Clothes clothes = new Clothes();
        clothes.setName("qwer");

        Mockito.when(clothesService.findByArticle("qwerty")).thenReturn(clothes);
        Mockito.when(warehouseService.findByClothesAndSize(
                clothes, warehouse.getSize())).thenReturn(null);

        Assertions.assertNotNull(warehouseAdminController.addGroup(
                warehouse, "qwerty", model));
        Assertions.assertEquals( "admin/addToWarehouse",
                warehouseAdminController.addGroup(
                        warehouse, "qwerty", model));
    }
}