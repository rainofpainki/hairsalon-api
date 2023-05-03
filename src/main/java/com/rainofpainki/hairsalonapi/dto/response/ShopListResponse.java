package com.rainofpainki.hairsalonapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ShopListResponse {
    private Long shopId;
    private String shopName;
    private String shopThumbUrl;
    private String shopAddress;
    private Map<String, String> shopBusinessHours;
    private String shopTelNumber;
}
