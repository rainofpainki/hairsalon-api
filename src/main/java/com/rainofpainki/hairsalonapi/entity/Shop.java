package com.rainofpainki.hairsalonapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SHOP")
public class Shop {
    @Id
    @GeneratedValue(generator = "shop_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "shop_seq", allocationSize = 1, sequenceName = "SHOP_SEQ")
    private Long shopId;
    private String shopName;
    private String shopThumbUrl;
    private String shopAddress;
    private String shopStartHourOfWeekday;
    private String shopEndHourOfWeekday;
    private String shopStartHourOfWeekend;
    private String shopEndHourOfWeekend;
    private String shopTelNumber;
    private String shopMessage;
    private Integer shopHolidayOfWeek;
}
