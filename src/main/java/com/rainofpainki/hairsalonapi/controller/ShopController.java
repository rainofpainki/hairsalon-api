package com.rainofpainki.hairsalonapi.controller;

import com.rainofpainki.hairsalonapi.dto.ShopForList;
import com.rainofpainki.hairsalonapi.dto.ShopInfo;
import com.rainofpainki.hairsalonapi.dto.request.PageRequest;
import com.rainofpainki.hairsalonapi.dto.response.DataResponse;
import com.rainofpainki.hairsalonapi.dto.response.PageableResponse;
import com.rainofpainki.hairsalonapi.service.ShopService;
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
    private ShopService service;

    @GetMapping("")
    public ResponseEntity<PageableResponse> getShopList(PageRequest pageRequest) {
        Pageable pageable = pageRequest.of();
        Page<ShopForList> shopList = service.getShopList(pageable);

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
        DataResponse response = null;
        try {
            ShopInfo shopInfo = service.getShopInfo(id);
            response = DataResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("success")
                    .data(shopInfo)
                    .build();
        } catch(NoSuchElementException e) {}
        return (response == null ? ResponseEntity.status(HttpStatus.NOT_FOUND) : ResponseEntity.ok()).body(response);
    }

}
