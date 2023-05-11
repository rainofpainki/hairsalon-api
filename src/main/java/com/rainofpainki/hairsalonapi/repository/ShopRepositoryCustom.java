package com.rainofpainki.hairsalonapi.repository;

import com.rainofpainki.hairsalonapi.entity.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShopRepositoryCustom {
    Page<Shop> findAll(Pageable pageable);
}
