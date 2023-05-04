package com.rainofpainki.hairsalonapi.service;

import com.rainofpainki.hairsalonapi.dto.response.ShopListResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ShopServiceTest {

    @Autowired
    private ShopService shopService;

    @Test
    public void should_get_shop_list() {
        List<ShopListResponse> shopListResponseList = shopService.getShopList();
        Assertions.assertTrue(shopListResponseList.size() > 0);
    }
}
