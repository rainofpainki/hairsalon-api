package com.rainofpainki.hairsalonapi.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ShopForList {
    private Long shopId;
    private String shopName;
    private String shopThumbUrl;
    private String shopAddress;
    private Map<String, String> shopBusinessHours;
    private String shopTelNumber;
}
