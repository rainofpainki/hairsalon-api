package com.rainofpainki.hairsalonapi.service;

import com.rainofpainki.hairsalonapi.dto.request.ReservationRequest;
import com.rainofpainki.hairsalonapi.entity.Reservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    private ReservationService service;

    @Test
    @Transactional
    public void should_save_reservation() throws ParseException {
        ReservationRequest request = new ReservationRequest();
        request.setShopId(1L);
        request.setProcedureId(1L);
        request.setStylistId(1L);
        request.setReservationDate("2023-05-12");
        request.setReservationTime("10:00");

        Long userId = 1L;
        Reservation reservation = service.mapToEntity(request, userId);
        Reservation saved = service.save(reservation);

        Assertions.assertEquals(saved.getReservationDatetime(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-05-12 10:00:00"));
    }
}
