package com.rainofpainki.hairsalonapi.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rainofpainki.hairsalonapi.entity.QShop;
import com.rainofpainki.hairsalonapi.entity.Shop;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ShopRepositoryImpl implements ShopRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public ShopRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Shop> findAll() {
        return queryFactory.selectFrom(QShop.shop).fetch();
    }
}
