package com.rainofpainki.hairsalonapi.validator;

import com.rainofpainki.hairsalonapi.dto.request.ReservationRequest;
import com.rainofpainki.hairsalonapi.entity.Reservation;
import com.rainofpainki.hairsalonapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ReservationSaveValidator implements Validator {

    @Autowired
    private ReservationService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return ReservationRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ReservationRequest request = (ReservationRequest) target;
        Optional<Reservation> optional = service.checkReservationTime(request);
        if (optional.isPresent()) {
            errors.rejectValue("reservationTime", "reservationTime.already_exists", "해당 시간대에는 이미 예약이 존재합니다.");
        }
    }
}
