package com.rainofpainki.hairsalonapi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RESERVATION")
public class Reservation {
    @Id
    @GeneratedValue(generator = "reservation_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "reservation_seq", allocationSize = 1, sequenceName = "RESERVATION_SEQ")
    private Long reservationId;
    private LocalDateTime reservationStartTime;
    private LocalDateTime reservationEndTime;
    private Integer reservationMinutes;
    private String reservationShopName;
    private String reservationStylistName;
    private String reservationProcedureName;

    private Long userId;

    @ManyToOne
    @JoinColumn(name = "SHOP_ID")
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "STYLIST_ID")
    private Stylist stylist;

    @ManyToOne
    @JoinColumn(name = "PROCEDURE_ID")
    private Procedure procedure;

    private Integer price;
}
