package com.rainofpainki.hairsalonapi.service;

import com.rainofpainki.hairsalonapi.dto.request.ReservationRequest;
import com.rainofpainki.hairsalonapi.entity.Procedure;
import com.rainofpainki.hairsalonapi.entity.Reservation;
import com.rainofpainki.hairsalonapi.entity.Shop;
import com.rainofpainki.hairsalonapi.entity.Stylist;
import com.rainofpainki.hairsalonapi.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository repository;

    public Reservation mapToEntity(ReservationRequest request, Long userId) throws ParseException {
        Shop shop = repository.getShop(request.getShopId()).orElseThrow(() -> new NoSuchElementException());
        Stylist stylist = repository.getStylist(request.getStylistId()).orElseThrow(() -> new NoSuchElementException());
        Procedure procedure = repository.getProcedure(request.getProcedureId()).orElseThrow(() -> new NoSuchElementException());

        LocalDateTime reservationStartTime = request.getReservationStartTime();
        LocalDateTime reservationEndTime = reservationStartTime.plusMinutes(procedure.getProcedureMinutes());


        return Reservation.builder()
                .reservationStartTime(reservationStartTime)
                .reservationEndTime(reservationEndTime)
                .reservationMinutes(procedure.getProcedureMinutes())
                .reservationShopName(shop.getShopName())
                .reservationStylistName(stylist.getStylistName())
                .reservationProcedureName(procedure.getProcedureName())
                .userId(userId)
                .shop(shop)
                .stylist(stylist)
                .procedure(procedure)
                .price(procedure.getProcedurePrice())
                .build();
    }

    public Reservation save(Reservation reservation) {
        return repository.saveAndFlush(reservation);
    }

    public Optional<Reservation> checkReservationTime(ReservationRequest request) {
        Procedure procedure = repository.getProcedure(request.getProcedureId()).orElseThrow(() -> new NoSuchElementException());
        LocalDateTime reservationStartTime = request.getReservationStartTime();
        LocalDateTime reservationEndTime = reservationStartTime.plusMinutes(procedure.getProcedureMinutes());
        Optional<Reservation> optionalStartTime = repository.stylistHasReservationForTime(request.getStylistId(), reservationStartTime);
        return ( optionalStartTime.isPresent() ? optionalStartTime : repository.stylistHasReservationForTime(request.getStylistId(), reservationEndTime) );
    }
}
