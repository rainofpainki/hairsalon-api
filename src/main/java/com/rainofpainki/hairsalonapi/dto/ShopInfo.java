package com.rainofpainki.hairsalonapi.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ShopInfo {
    private ShopForShopInfo shop;
    private List<ProcedureForShopInfo> procedures;
    private List<StylistForShopInfo> stylists;
}
