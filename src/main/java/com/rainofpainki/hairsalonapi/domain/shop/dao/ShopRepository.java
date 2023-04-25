package com.rainofpainki.hairsalonapi.domain.shop.dao;

import com.rainofpainki.hairsalonapi.domain.shop.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Integer>, ShopCustomRepository {
}
