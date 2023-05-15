package com.rainofpainki.hairsalonapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationForList {
    private Long reservationId;
    private LocalDateTime reservationStartTime;
    private LocalDateTime reservationEndTime;
    private Integer reservationMinutes;
    private Integer shopId;
    private Integer stylistId;
    private Integer procedureId;
    private String reservationShopName;
    private String reservationStylistName;
    private String reservationProcedureName;
    private Integer price;
}
