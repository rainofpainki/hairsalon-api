package com.rainofpainki.hairsalonapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StylistForShopInfo {
    private Long stylistId;
    private String stylistName;
    private String stylistMessage;
    private String stylistThumbUrl;
}
