package com.rainofpainki.hairsalonapi.domain.shop.dao;

import com.rainofpainki.hairsalonapi.domain.shop.domain.Shop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ShopRepositoryTest {

    @Autowired
    private ShopRepository shopRepository;

    @Test
    public void queryDslFindAllTest() {
        List<Shop> shops = shopRepository.queryDslFindAll();
        shops.iterator().forEachRemaining(shop -> {
            System.out.println("shop = " + shop);
        });
    }
}
