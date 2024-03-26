package com.mecadolibre.coupon.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OptimizationResponse {

    private List<String> itemIds;
    private double total;
}
