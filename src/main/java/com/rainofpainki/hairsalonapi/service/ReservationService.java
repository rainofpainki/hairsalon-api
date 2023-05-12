package com.rainofpainki.hairsalonapi.service;

import com.rainofpainki.hairsalonapi.dto.request.ReservationRequest;
import com.rainofpainki.hairsalonapi.entity.Procedure;
import com.rainofpainki.hairsalonapi.entity.Reservation;
import com.rainofpainki.hairsalonapi.entity.Shop;
import com.rainofpainki.hairsalonapi.entity.Stylist;
import com.rainofpainki.hairsalonapi.repository.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public Reservation mapToEntity(ReservationRequest request, Long userId) throws ParseException {
        Shop shop = repository.getShop(request.getShopId()).orElseThrow(() -> new NoSuchElementException());
        Stylist stylist = repository.getStylist(request.getStylistId()).orElseThrow(() -> new NoSuchElementException());
        Procedure procedure = repository.getProcedure(request.getProcedureId()).orElseThrow(() -> new NoSuchElementException());

        return Reservation.builder()
                .reservationDatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(request.getReservationDate() + " " + request.getReservationTime()))
                .reservationHours(procedure.getProcedureHours())
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
}
