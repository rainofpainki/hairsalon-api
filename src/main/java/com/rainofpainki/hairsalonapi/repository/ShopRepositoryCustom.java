package com.rainofpainki.hairsalonapi.repository;

import com.rainofpainki.hairsalonapi.dto.ShopListDto;

import java.util.List;

public interface ShopRepositoryCustom {
    List<ShopListDto> queryDslFindAll();
}
