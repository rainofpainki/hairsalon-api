package com.rainofpainki.hairsalonapi.repository;

import com.rainofpainki.hairsalonapi.dto.ShopListDto;
import com.rainofpainki.hairsalonapi.entity.Shop;
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
        List<ShopListDto> shopList = shopRepository.queryDslFindAll();
        shopList.iterator().forEachRemaining(shop -> {
            System.out.println("shop => " + shop);
        });
    }
}
