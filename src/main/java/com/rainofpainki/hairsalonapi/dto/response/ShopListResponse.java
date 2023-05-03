package com.rainofpainki.hairsalonapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopListResponse {
    private Long shopId;
    private String shopName;
    private String shopThumbUrl;
    private String shopAddress;
    private String shopBusinessHours;
    private String shopTelNumber;
}
