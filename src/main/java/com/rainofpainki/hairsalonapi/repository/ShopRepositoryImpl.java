package com.rainofpainki.hairsalonapi.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rainofpainki.hairsalonapi.dto.QShopListDto;
import com.rainofpainki.hairsalonapi.dto.ShopListDto;
import org.springframework.stereotype.Repository;
import static com.rainofpainki.hairsalonapi.entity.QShop.shop;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ShopRepositoryImpl implements ShopRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public ShopRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ShopListDto> queryDslFindAll() {
        return queryFactory.select(new QShopListDto(
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
