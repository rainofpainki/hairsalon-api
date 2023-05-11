package com.rainofpainki.hairsalonapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProcedureForShopInfo {
    private Long procedureId;
    private String procedureName;
    private Integer procedurePrice;
    private Integer procedureHours;
}
