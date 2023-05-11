package com.rainofpainki.hairsalonapi.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rainofpainki.hairsalonapi.entity.QShop;
import com.rainofpainki.hairsalonapi.entity.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ShopRepository {

    private JPAQueryFactory queryFactory;

    public ShopRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Page<Shop> findAll(Pageable pageable) {
        List<Shop> shopList = queryFactory
                .selectFrom(QShop.shop)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory.select(QShop.shop.count())
                .from(QShop.shop)
                .fetchOne();

        return new PageImpl<>(shopList, pageable, count);
    }
}
