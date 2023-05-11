package com.rainofpainki.hairsalonapi.service;

import com.rainofpainki.hairsalonapi.dto.ShopForList;
import com.rainofpainki.hairsalonapi.entity.Shop;
import com.rainofpainki.hairsalonapi.repository.ShopRepository;
import com.rainofpainki.hairsalonapi.util.PageRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@SpringBootTest
public class ShopForListServiceTest {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopRepository shopRepository;

    @Test
    public void should_get_shop_list() {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPage(1);
        pageRequest.setSize(2);
        Pageable pageable = pageRequest.of();
        Page<ShopForList> shopList = shopService.getShopList(pageable);
        Assertions.assertEquals(shopList.getSize(), 2);
        Assertions.assertEquals(shopList.getTotalElements(), 3);
    }

    @Test
    public void should_get_shop_info() {
        Optional<Shop> shopOptional = shopRepository.findById(1L);
        boolean isPresent = shopOptional.isPresent();
        Assertions.assertTrue(isPresent);
        if(isPresent) {
            Shop shop = shopOptional.get();
            Assertions.assertEquals(shop.getShopId(), 1L);
        }
    }
}
