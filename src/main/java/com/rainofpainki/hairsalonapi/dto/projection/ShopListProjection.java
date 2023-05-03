package com.rainofpainki.hairsalonapi.dto.projection;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShopListProjection {
    private Long shopId;
    private String shopName;
    private String shopThumbUrl;
    private String shopAddress;
    private String shopStartHourOfWeekday;
    private String shopEndHourOfWeekday;
    private String shopStartHourOfWeekend;
    private String shopEndHourOfWeekend;
    private Integer shopHolidayOfWeek;
    private String shopTelNumber;

    @QueryProjection
    public ShopListProjection(final Long shopId, final String shopName, final String shopThumbUrl, final String shopAddress, final String shopStartHourOfWeekday, final String shopEndHourOfWeekday, final String shopStartHourOfWeekend, final String shopEndHourOfWeekend, final Integer shopHolidayOfWeek, final String shopTelNumber) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopThumbUrl = shopThumbUrl;
        this.shopAddress = shopAddress;
        this.shopStartHourOfWeekday = shopStartHourOfWeekday;
        this.shopEndHourOfWeekday = shopEndHourOfWeekday;
        this.shopStartHourOfWeekend = shopStartHourOfWeekend;
        this.shopEndHourOfWeekend = shopEndHourOfWeekend;
        this.shopHolidayOfWeek = shopHolidayOfWeek;
        this.shopTelNumber = shopTelNumber;
    }
}
