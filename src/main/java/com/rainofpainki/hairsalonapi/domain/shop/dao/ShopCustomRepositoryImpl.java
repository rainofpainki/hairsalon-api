package com.rainofpainki.hairsalonapi.domain.shop.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rainofpainki.hairsalonapi.domain.shop.domain.QShop;
import com.rainofpainki.hairsalonapi.domain.shop.domain.Shop;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ShopCustomRepositoryImpl implements ShopCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public ShopCustomRepositoryImpl(EntityManager em) {
        jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Shop> queryDslFindAll() {
        return jpaQueryFactory.selectFrom(QShop.shop).fetch();
    }
}
