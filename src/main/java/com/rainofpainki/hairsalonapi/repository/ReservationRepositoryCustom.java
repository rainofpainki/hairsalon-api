package com.rainofpainki.hairsalonapi.repository;

import com.rainofpainki.hairsalonapi.entity.Procedure;
import com.rainofpainki.hairsalonapi.entity.Reservation;
import com.rainofpainki.hairsalonapi.entity.Shop;
import com.rainofpainki.hairsalonapi.entity.Stylist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ReservationRepositoryCustom {

    Optional<Shop> getShop(Long shopId);

    Optional<Stylist> getStylist(Long stylistId);

    Optional<Procedure> getProcedure(Long procedureId);

    Optional<Reservation> stylistHasReservationForTime(Long stylistId, LocalDateTime time);

    Page<Reservation> findAllByUserId(Pageable pageable, Long userId);
}
