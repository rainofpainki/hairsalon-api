package com.rainofpainki.hairsalonapi.service;

import com.rainofpainki.hairsalonapi.dto.ReservationForList;
import com.rainofpainki.hairsalonapi.dto.request.ReservationRequest;
import com.rainofpainki.hairsalonapi.entity.Procedure;
import com.rainofpainki.hairsalonapi.entity.Reservation;
import com.rainofpainki.hairsalonapi.entity.Shop;
import com.rainofpainki.hairsalonapi.entity.Stylist;
import com.rainofpainki.hairsalonapi.repository.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    @Transactional(readOnly = true)
    public Page<ReservationForList> getReservationListByUser(Pageable pageable, Long userId) {
        Page<Reservation> entities = repository.findAllByUserId(pageable, userId);
        List<ReservationForList> reservationList = new ArrayList<>();
        for(Reservation reservation : entities) {
            reservationList.add(modelMapper.map(reservation, ReservationForList.class));
        }
        return new PageImpl<>(reservationList, pageable, entities.getTotalElements());
    }
}
