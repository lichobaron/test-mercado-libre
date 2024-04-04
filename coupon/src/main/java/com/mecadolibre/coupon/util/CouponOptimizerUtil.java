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
                // Si el precio del item es menor o igual al precio de un cupon con capacidad j
                // esto significa que el item cabe en el cupon
                if (currentPrice.compareTo(BigDecimal.valueOf(j)) <= 0) {
                    // entonces:
                    // Guardamos en la posicion [item][cupon j], ll valor maximo entre:
                    //  El valor del meter el item anterior (i-1) en el cupon j
                    //  o
                    //  El valor del item anterior para un cupon (j - precio actual) + el precio actual (valor de meter item actual)
                    memoTable[i][j] = memoTable[i - 1][j].max(
                            memoTable[i - 1][j - getBDIndex(currentPrice)].add(currentPrice)
                    );
                } else {
                    // Si el item no cabe en el cupon
                    // Solo guardamos el valor del item anterior para un cupon j
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
            // Si el maximo gastado es diferente a el valor de usar el item anterior (i-1) en un cupon del maximo gastado
            // Esto significa que se uso el item actual
            if (!maxSpent.equals(memoTable[i - 1][getBDIndex(maxSpent)])) {
                // Se selecciona el item actual
                selectedItems.add(items.get(i - 1));
                // El maximo gastado se le resta el precio del item actual
                maxSpent = maxSpent.subtract(BigDecimal.valueOf(items.get(i - 1).getPrice()));
            }
            //Si el maximo gastado es igual el valor de usar el item anterior (i-1) en un cupon del maximo gastado
            // Esto significa que no se usa el item actual
        }

//        System.out.println("Max: ");
//        System.out.println(memoTable[n][couponValue]);
//        System.out.println(selectedItems);
//        for (int i = 0; i <= n; i++) {
//            for (int j = 0; j <= couponValue; j++) {
//                System.out.print(memoTable[i][j] + "  ");
//            }
//            System.out.println();
//        }

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
