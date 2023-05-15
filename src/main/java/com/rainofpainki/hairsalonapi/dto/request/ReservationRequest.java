package com.rainofpainki.hairsalonapi.dto.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class ReservationRequest {
    @NotNull
    private Long shopId;
    @NotNull
    private Long procedureId;
    @NotNull
    private Long stylistId;
    @NotNull
    private String reservationDate;
    @NotNull
    private String reservationTime;

    public LocalDateTime getReservationStartTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(this.getReservationDate() + " " + this.getReservationTime(), formatter);
    }
}
