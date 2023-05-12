package com.rainofpainki.hairsalonapi.dto;

import lombok.Data;

@Data
public class ProcedureForShopInfo {
    private Long procedureId;
    private String procedureName;
    private Integer procedurePrice;
    private Integer procedureHours;
}
