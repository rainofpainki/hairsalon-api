package com.rainofpainki.hairsalonapi.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rainofpainki.hairsalonapi.dto.projection.QShopListProjection;
import com.rainofpainki.hairsalonapi.dto.projection.ShopListProjection;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.rainofpainki.hairsalonapi.entity.QShop.shop;

@Repository
public class ShopRepositoryImpl implements ShopRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public ShopRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ShopListProjection> queryDslFindAll() {
        return queryFactory.select(new QShopListProjection(
                shop.shopId,
                shop.shopName,
                shop.shopThumbUrl,
                shop.shopAddress,
                shop.shopStartHourOfWeekday,
                shop.shopEndHourOfWeekday,
                shop.shopStartHourOfWeekend,
                shop.shopEndHourOfWeekend,
                shop.shopTelNumber
        )).from(shop)
        .fetch();
    }
}
