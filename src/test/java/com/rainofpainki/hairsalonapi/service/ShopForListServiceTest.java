package com.rainofpainki.hairsalonapi.service;

import com.rainofpainki.hairsalonapi.dto.ShopForList;
import com.rainofpainki.hairsalonapi.dto.ShopInfo;
import com.rainofpainki.hairsalonapi.util.PageRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@SpringBootTest
public class ShopForListServiceTest {

    @Autowired
    private ShopService shopService;

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
        ShopInfo shopInfo = shopService.getShopInfo(1L);
        Assertions.assertEquals(shopInfo.getShop().getShopName(), "비롬헤어 정자본점");
        Assertions.assertEquals(shopInfo.getStylists().get(0).getStylistName(), "영우 디자이너");
        Assertions.assertEquals(shopInfo.getProcedures().get(0).getProcedureName(), "여성커트");
    }
}
