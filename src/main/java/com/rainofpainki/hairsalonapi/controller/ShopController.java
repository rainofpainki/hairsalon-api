package com.rainofpainki.hairsalonapi.controller;

import com.rainofpainki.hairsalonapi.dto.response.ShopListResponse;
import com.rainofpainki.hairsalonapi.service.ShopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/shop")
public class ShopController {

    @Autowired
    ShopServiceImpl shopServiceImpl;

    @GetMapping("")
    public List<ShopListResponse> getShopList() {
        return shopServiceImpl.getShopList();
    }

}
