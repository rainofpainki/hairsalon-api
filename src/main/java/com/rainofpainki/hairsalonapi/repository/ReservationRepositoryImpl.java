package com.rainofpainki.hairsalonapi.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rainofpainki.hairsalonapi.entity.Procedure;
import com.rainofpainki.hairsalonapi.entity.Reservation;
import com.rainofpainki.hairsalonapi.entity.Shop;
import com.rainofpainki.hairsalonapi.entity.Stylist;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.rainofpainki.hairsalonapi.entity.QProcedure.procedure;
import static com.rainofpainki.hairsalonapi.entity.QReservation.reservation;
import static com.rainofpainki.hairsalonapi.entity.QShop.shop;
import static com.rainofpainki.hairsalonapi.entity.QStylist.stylist;

@Repository
public class ReservationRepositoryImpl implements ReservationRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public ReservationRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Optional<Shop> getShop(Long shopId) {
        return Optional.ofNullable(queryFactory.selectFrom(shop).where(shop.shopId.eq(shopId)).fetchOne());
    }

    public Optional<Stylist> getStylist(Long stylistId) {
        return Optional.ofNullable(queryFactory.selectFrom(stylist).where(stylist.stylistId.eq(stylistId)).fetchOne());
    }

    public Optional<Procedure> getProcedure(Long procedureId) {
        return Optional.ofNullable(queryFactory.selectFrom(procedure).where(procedure.procedureId.eq(procedureId)).fetchOne());
    }

    public Optional<Reservation> stylistHasReservationForTime(Long stylistId, LocalDateTime time) {
        return Optional.ofNullable(queryFactory.selectFrom(reservation).where(
                    reservation.stylist.stylistId.eq(stylistId),
                    reservation.reservationStartTime.loe(time),
                    reservation.reservationEndTime.goe(time)
                ).fetchOne()
        );
    }
}
