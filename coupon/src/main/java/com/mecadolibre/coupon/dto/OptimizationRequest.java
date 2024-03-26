package com.mecadolibre.coupon.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OptimizationRequest {

    private List<String> itemIds;
    private double amount;
}
