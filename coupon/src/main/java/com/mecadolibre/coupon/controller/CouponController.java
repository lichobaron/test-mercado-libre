package com.mecadolibre.coupon.controller;

import com.mecadolibre.coupon.dto.OptimizationRequest;
import com.mecadolibre.coupon.dto.OptimizationResponse;
import com.mecadolibre.coupon.service.CouponOptimizerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("coupon")
public class CouponController {

    private final CouponOptimizerService couponOptimizerService;

    @PostMapping
    public OptimizationResponse optimizeCoupon(@RequestBody OptimizationRequest optimizationRequest) {
        return couponOptimizerService.optimizeCoupon(optimizationRequest);
    }
}
