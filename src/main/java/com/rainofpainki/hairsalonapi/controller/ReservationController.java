package com.rainofpainki.hairsalonapi.controller;

import com.rainofpainki.hairsalonapi.dto.request.ReservationRequest;
import com.rainofpainki.hairsalonapi.dto.response.ResultResponse;
import com.rainofpainki.hairsalonapi.entity.Reservation;
import com.rainofpainki.hairsalonapi.repository.ShopRepository;
import com.rainofpainki.hairsalonapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/reservation")
public class ReservationController {

    static final String HEADER_USER_ID = "X-API-USER-ID";

    @Autowired
    private ReservationService service;

    @Autowired ShopRepository shopRepository;

    @PostMapping("")
    @Transactional
    public ResponseEntity<ResultResponse> save(
            @RequestBody ReservationRequest request,
            @RequestHeader(name = HEADER_USER_ID) Long userId
    ) {
        ResultResponse response = null;
        try {
            Reservation reservation = service.mapToEntity(request, userId);
            Reservation saved = service.save(reservation);

            response = ResultResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("success")
                    .result(saved instanceof Reservation)
                    .build();
        }catch (ParseException | NoSuchElementException e) {
            System.out.println(e);
        }

        return (response == null ? ResponseEntity.status(HttpStatus.BAD_REQUEST) : ResponseEntity.ok()).body(response);
    }
}
