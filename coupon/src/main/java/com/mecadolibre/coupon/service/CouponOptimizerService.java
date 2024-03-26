package com.mecadolibre.coupon.service;

import com.mecadolibre.coupon.client.MLItemClient;
import com.mecadolibre.coupon.dto.Item;
import com.mecadolibre.coupon.dto.MLItemResponse;
import com.mecadolibre.coupon.dto.OptimizationRequest;
import com.mecadolibre.coupon.dto.OptimizationResponse;
import com.mecadolibre.coupon.util.CouponOptimizerUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CouponOptimizerService {

    private final MLItemClient mlItemClient;

    public OptimizationResponse optimizeCoupon(OptimizationRequest optimizationRequest) {

        List<Item> selectedItems = CouponOptimizerUtil.optimizeCoupon((int) optimizationRequest.getAmount(), getItems(optimizationRequest.getItemIds()));

        return OptimizationResponse.builder()
                .itemIds(selectedItems.stream()
                        .map(Item::getId)
                        .toList())
                .total(selectedItems.stream()
                        .map(Item::getPrice)
                        .reduce((double) 0, Double::sum))
                .build();
    }

    private List<Item> getItems(List<String> itemIds) {

        return itemIds.stream()
                .map(id -> {
                    MLItemResponse itemResponse = mlItemClient.getItem(id);
                    return Item.builder()
                            .id(itemResponse.getId())
                            .price(itemResponse.getPrice())
                            .build();
                    }
                )
                .toList();
    }
}
