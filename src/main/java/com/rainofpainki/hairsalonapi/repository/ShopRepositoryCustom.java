package com.rainofpainki.hairsalonapi.repository;

import com.rainofpainki.hairsalonapi.dto.projection.ShopListProjection;

import java.util.List;

public interface ShopRepositoryCustom {
    List<ShopListProjection> queryDslFindAll();
}
