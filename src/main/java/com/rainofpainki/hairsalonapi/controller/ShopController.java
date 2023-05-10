package com.rainofpainki.hairsalonapi.controller;

import com.rainofpainki.hairsalonapi.dto.response.ShopListResponse;
import com.rainofpainki.hairsalonapi.entity.Shop;
import com.rainofpainki.hairsalonapi.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("")
    public List<Shop> getShopList() {
        return shopService.getShopList();
    }

}
