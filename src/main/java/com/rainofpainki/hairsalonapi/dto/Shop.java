package com.rainofpainki.hairsalonapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Shop {
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
