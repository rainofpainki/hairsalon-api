package com.rainofpainki.hairsalonapi.dto.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
}
