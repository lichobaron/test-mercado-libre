package com.mecadolibre.coupon.util;

import com.mecadolibre.coupon.dto.Item;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CouponOptimizerUtilTest {

    @Test
    void optimizeCoupon() {
        List<Item> items = List.of(
                Item.builder()
                        .id("MLA1")
                        .price(100)
                        .build(),
                Item.builder()
                        .id("MLA2")
                        .price(210)
                        .build(),
                Item.builder()
                        .id("MLA3")
                        .price(260)
                        .build(),
                Item.builder()
                        .id("MLA4")
                        .price(80)
                        .build(),
                Item.builder()
                        .id("MLA5")
                        .price(90)
                        .build()
        );

        List<Item> result = CouponOptimizerUtil
                .optimizeCoupon(500, items);
        List<Item> expectedResult = items.stream()
                .filter(i -> !i.getId().equals("MLA3"))
                .toList();

        assertArrayEquals(expectedResult.toArray(), result.stream().sorted(Comparator.comparing(Item::getId)).toArray());
    }

    @Test
    void optimizeCoupon_usingDouble() {
        List<Item> items = List.of(
                Item.builder()
                        .id("MLA1")
                        .price(25608)
                        .build(),
                Item.builder()
                        .id("MLA2")
                        .price(89.84)
                        .build(),
                Item.builder()
                        .id("MLA3")
                        .price(5000)
                        .build(),
                Item.builder()
                        .id("MLA4")
                        .price(539)
                        .build()
        );

        List<Item> result = CouponOptimizerUtil
                .optimizeCoupon(1000, items);
        List<Item> expectedResult = items.stream()
                .filter(i -> !i.getId().equals("MLA1") && !i.getId().equals("MLA3"))
                .toList();

        assertArrayEquals(expectedResult.toArray(), result.stream().sorted(Comparator.comparing(Item::getId)).toArray());
    }

    @Test
    void getBDIndex() {
        int x = CouponOptimizerUtil.getBDIndex(BigDecimal.valueOf(1.2));
        assertEquals(1, x);
        int y = CouponOptimizerUtil.getBDIndex(BigDecimal.valueOf(1.6));
        assertEquals(2, y);
    }
}