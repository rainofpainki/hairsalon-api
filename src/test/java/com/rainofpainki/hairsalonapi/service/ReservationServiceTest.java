package com.rainofpainki.hairsalonapi.service;

import com.rainofpainki.hairsalonapi.dto.ReservationForList;
import com.rainofpainki.hairsalonapi.dto.request.PageRequest;
import com.rainofpainki.hairsalonapi.dto.request.ReservationRequest;
import com.rainofpainki.hairsalonapi.entity.Reservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

        Assertions.assertEquals(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(saved.getReservationStartTime()), "2023-05-12 10:00");
        Assertions.assertEquals(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(saved.getReservationEndTime()), "2023-05-12 10:30");
    }

    @Test
    @Transactional
    public void should_check_reservation() throws ParseException {
        ReservationRequest request = new ReservationRequest();
        request.setShopId(1L);
        request.setProcedureId(1L);
        request.setStylistId(1L);
        request.setReservationDate("2023-05-13");
        request.setReservationTime("14:00");
        service.save(service.mapToEntity(request, 1L));

        ReservationRequest request2 = new ReservationRequest();
        request2.setShopId(1L);
        request2.setProcedureId(1L);
        request2.setStylistId(1L);
        request2.setReservationDate("2023-05-13");
        request2.setReservationTime("14:25");
        Optional<Reservation> optional = service.checkReservationTime(request2);

        Assertions.assertTrue(optional.isPresent());

        ReservationRequest request3 = new ReservationRequest();
        request3.setShopId(1L);
        request3.setProcedureId(1L);
        request3.setStylistId(1L);
        request3.setReservationDate("2023-05-13");
        request3.setReservationTime("13:50");
        Optional<Reservation> optional2 = service.checkReservationTime(request3);
        Assertions.assertTrue(optional2.isPresent());
    }

    public void should_get_my_reservation_list() throws ParseException {
        Long userId = 1L;
        List<String> times = new ArrayList<>(Arrays.asList("09:00", "10:10", "11:00", "11:40", "14:00"));
        for(String time : times) {
            ReservationRequest request = new ReservationRequest();
            request.setShopId(1L);
            request.setProcedureId(1L);
            request.setStylistId(1L);
            request.setReservationDate("2023-05-15");
            request.setReservationTime(time);
            service.save(service.mapToEntity(request, userId));
        }

        PageRequest pageRequest = new PageRequest();
        pageRequest.setPage(1);
        pageRequest.setSize(2);
        Pageable pageable = pageRequest.of();
        Page<ReservationForList> reservationList = service.getReservationListByUser(pageable, userId);

        Assertions.assertEquals(reservationList.getSize(), 2);
        Assertions.assertEquals(reservationList.getTotalElements(), 5);
    }
}
