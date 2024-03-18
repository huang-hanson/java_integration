package com.hanson.cache.controller;

import com.hanson.cache.entity.Brand;
import com.hanson.cache.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hanson
 * @date 2024/3/18 15:05
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/list")
    public List<Brand> getAllBrands() {
        return brandService.list();
    }

    @GetMapping("/{id}")
    public Brand getBrandById(@PathVariable Integer id) {
        return brandService.getById(id);
    }

    @PostMapping("/add")
    public void addBrand(@RequestBody Brand brand) {
        brandService.save(brand);
    }

    @PutMapping("/update")
    public void updateBrand(@RequestBody Brand brand) {
        brandService.updateById(brand);
    }

    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable Integer id) {
        brandService.removeById(id);
    }
}