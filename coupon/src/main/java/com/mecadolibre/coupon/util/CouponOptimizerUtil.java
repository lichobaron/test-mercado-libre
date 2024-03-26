package com.mecadolibre.coupon.util;

import com.mecadolibre.coupon.dto.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CouponOptimizerUtil {

    private CouponOptimizerUtil() {}

    public static List<Item> optimizeCoupon(int couponValue, List<Item> items) {
        int n = items.size();
        BigDecimal[][] memoTable = new BigDecimal[n + 1][couponValue + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= couponValue; j++) {
                memoTable[i][j] = BigDecimal.ZERO;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= couponValue; j++) {
                BigDecimal currentPrice = BigDecimal.valueOf(items.get(i - 1).getPrice());
                if (currentPrice.compareTo(BigDecimal.valueOf(j)) <= 0) {
                    memoTable[i][j] = memoTable[i - 1][j].max(
                            memoTable[i - 1][j - getBDIndex(currentPrice)].add(currentPrice)
                    );
                } else {
                    memoTable[i][j] = memoTable[i - 1][j];
                }
            }
        }

        BigDecimal maxSpent = memoTable[n][couponValue];
        List<Item> selectedItems = new ArrayList<>();
        for (int i = n; i > 0; i--) {
            if (maxSpent.compareTo(BigDecimal.ZERO) <= 0) {
                break;
            }
            if (!maxSpent.equals(memoTable[i - 1][getBDIndex(maxSpent)])) {
                selectedItems.add(items.get(i - 1));
                maxSpent = maxSpent.subtract(BigDecimal.valueOf(items.get(i - 1).getPrice()));
            }
        }

        System.out.println("Max: ");
        System.out.println(memoTable[n][couponValue]);

        return selectedItems;
    }

    public static int getBDIndex(BigDecimal value) {
        BigDecimal[] intAndFractional = value.divideAndRemainder(BigDecimal.ONE);
        BigDecimal intPart = intAndFractional[0];
        BigDecimal fractionalPart = intAndFractional[1];

        if (fractionalPart.compareTo(BigDecimal.valueOf(0.5)) == 0) {
            //TODO: possible error here
            return intPart.intValue();
        }

        if (fractionalPart.compareTo(BigDecimal.valueOf(0.5)) > 0) {
            return intPart.intValue() + 1;
        }

        return intPart.intValue();
    }
}
