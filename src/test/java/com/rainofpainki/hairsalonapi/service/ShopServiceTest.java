package com.rainofpainki.hairsalonapi.service;

import com.rainofpainki.hairsalonapi.entity.Shop;
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
        List<Shop> shopList = shopService.getShopList();
        Assertions.assertTrue(shopList.size() == 3);
        Assertions.assertTrue(true);
    }
}
