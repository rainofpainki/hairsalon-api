package com.rainofpainki.hairsalonapi.controller;

import com.rainofpainki.hairsalonapi.dto.ShopForList;
import com.rainofpainki.hairsalonapi.response.DataResponse;
import com.rainofpainki.hairsalonapi.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("")
    public ResponseEntity<DataResponse> getShopList() {
        List<ShopForList> shopList = shopService.getShopList();
        DataResponse response = DataResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("success")
                .count(shopList.size())
                .data(new ArrayList<>(shopList))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
