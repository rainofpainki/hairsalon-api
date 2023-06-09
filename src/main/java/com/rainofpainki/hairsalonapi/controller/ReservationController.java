package com.rainofpainki.hairsalonapi.controller;

import com.rainofpainki.hairsalonapi.dto.ReservationForList;
import com.rainofpainki.hairsalonapi.dto.request.PageRequest;
import com.rainofpainki.hairsalonapi.dto.request.ReservationRequest;
import com.rainofpainki.hairsalonapi.dto.response.PageableResponse;
import com.rainofpainki.hairsalonapi.dto.response.ResultResponse;
import com.rainofpainki.hairsalonapi.entity.Reservation;
import com.rainofpainki.hairsalonapi.repository.ShopRepository;
import com.rainofpainki.hairsalonapi.service.ReservationService;
import com.rainofpainki.hairsalonapi.validator.ReservationSaveValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/reservation")
public class ReservationController {

    static final String HEADER_USER_ID = "X-API-USER-ID";

    @Autowired
    private ReservationService service;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ReservationSaveValidator reservationSaveValidator;

    @InitBinder("reservationRequest")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(reservationSaveValidator);
    }

    @PostMapping("")
    @Transactional
    public ResponseEntity<ResultResponse> save(
            @RequestHeader(name = HEADER_USER_ID) Long userId,
            @Validated @RequestBody ReservationRequest reservationRequest,
            BindingResult bindingResult
    ) {
        ResultResponse response = null;

        if (bindingResult.hasErrors()) {
            ObjectError error =  bindingResult.getAllErrors().get(0);
            response = ResultResponse.builder()
                    .code(HttpStatus.CONFLICT.value())
                    .httpStatus(HttpStatus.CONFLICT)
                    .message(error.getDefaultMessage())
                    .result(false)
                    .build();
            return ResponseEntity.status(409).body(response);
        }

        try {
            Reservation reservation = service.mapToEntity(reservationRequest, userId);
            Reservation saved = service.save(reservation);

            response = ResultResponse.builder()
                    .code(HttpStatus.OK.value())
                    .httpStatus(HttpStatus.OK)
                    .message("예약이 완료되었습니다.")
                    .result(saved instanceof Reservation)
                    .build();
        }catch (ParseException | NoSuchElementException e) {
            System.out.println(e);
        }

        return (response == null ? ResponseEntity.status(HttpStatus.BAD_REQUEST) : ResponseEntity.ok()).body(response);
    }

    @GetMapping("")
    public ResponseEntity<PageableResponse> getMyReservationList(PageRequest pageRequest, @RequestHeader(name = HEADER_USER_ID) Long userId) {
        Pageable pageable = pageRequest.of();
        Page<ReservationForList> reservationList = service.getReservationListByUser(pageable, userId);

        PageableResponse response = PageableResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("success")
                .data(reservationList)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
