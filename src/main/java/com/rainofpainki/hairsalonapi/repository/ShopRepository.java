package com.rainofpainki.hairsalonapi.repository;

import com.rainofpainki.hairsalonapi.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long>, ShopRepositoryCustom {
}
