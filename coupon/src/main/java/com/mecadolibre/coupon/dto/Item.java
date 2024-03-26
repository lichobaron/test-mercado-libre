package com.mecadolibre.coupon.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Item {

    private String id;
    private double price;
}
