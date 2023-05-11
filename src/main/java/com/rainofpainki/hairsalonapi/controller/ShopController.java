package com.rainofpainki.hairsalonapi.controller;

import com.rainofpainki.hairsalonapi.dto.ShopForList;
import com.rainofpainki.hairsalonapi.dto.ShopInfo;
import com.rainofpainki.hairsalonapi.response.DataResponse;
import com.rainofpainki.hairsalonapi.response.PageableResponse;
import com.rainofpainki.hairsalonapi.service.ShopService;
import com.rainofpainki.hairsalonapi.util.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("")
    public ResponseEntity<PageableResponse> getShopList(PageRequest pageRequest) {
        Pageable pageable = pageRequest.of();
        Page<ShopForList> shopList = shopService.getShopList(pageable);

        PageableResponse response = PageableResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("success")
                .data(shopList)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse> getShopInfo(@PathVariable("id") Long id) {
        DataResponse body = null;
        try {
            ShopInfo shopInfo = shopService.getShopInfo(id);
            body = DataResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("success")
                    .data(shopInfo)
                    .build();
        } catch(NoSuchElementException e) {}
        return (body == null ? ResponseEntity.status(HttpStatus.NOT_FOUND) : ResponseEntity.ok()).body(body);
    }

}
