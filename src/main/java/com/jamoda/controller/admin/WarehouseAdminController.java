package com.jamoda.controller.admin;

import com.jamoda.model.Clothes;
import com.jamoda.model.Warehouse;
import com.jamoda.service.ClothesService;
import com.jamoda.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WarehouseAdminController {

    private WarehouseService warehouseService;
    private ClothesService clothesService;

    @GetMapping("/admin/add_to_warehouse")
    public String pageAddToWarehouse(Model model) {
        return "admin/addToWarehouse";
    }

    @PostMapping("/admin/add_to_warehouse")
    public String addGroup(Warehouse warehouse,
                           @RequestParam(name="article") String article,
                           Model model) {
        Clothes clothesFromDb = clothesService.findByArticle(article);
        if(clothesFromDb == null) {
            model.addAttribute("error", "Такого товара не существует!");
            return "admin/addToWarehouse";
        }
        Warehouse warehouseFromDb = warehouseService.findByClothesAndSize(clothesFromDb, warehouse.getSize());
        if(warehouseFromDb == null) {
            warehouseFromDb = new Warehouse(clothesFromDb, warehouse.getSize(), warehouse.getCount());
        }
        else {
            warehouseFromDb.setCount(warehouseFromDb.getCount() + warehouse.getCount());
        }
        if(warehouse.getCount() > 0 && !clothesFromDb.isPresence()) {
            clothesFromDb.setPresence(true);
            clothesService.saveClothes(clothesFromDb);
        }
        warehouseService.saveWarehouse(warehouseFromDb);
        model.addAttribute("message", "Товары успешно добавлены на склад");
        return "admin/addToWarehouse";
    }

    @Autowired
    public void setWarehouseService(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }
    @Autowired
    public void setClothesService(ClothesService clothesService) {
        this.clothesService = clothesService;
    }
}
