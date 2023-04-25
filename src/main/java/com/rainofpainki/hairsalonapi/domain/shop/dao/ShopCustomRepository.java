package com.rainofpainki.hairsalonapi.domain.shop.dao;

import com.rainofpainki.hairsalonapi.domain.shop.domain.Shop;

import java.util.List;

public interface ShopCustomRepository {

    List<Shop> queryDslFindAll();
}
