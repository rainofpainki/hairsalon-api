package com.rainofpainki.hairsalonapi.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ShopForShopInfo {
    private Long shopId;
    private String shopName;
    private String shopThumbUrl;
    private String shopAddress;
    private Map<String, String> shopBusinessHours;
    private String shopTelNumber;
    private String shopMessage;
}
