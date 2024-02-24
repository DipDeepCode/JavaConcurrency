package ru.ddc.listing_3_12_immutabecacheholder;

import ru.ddc.annotations.Immutable;

import java.math.BigInteger;
import java.util.Arrays;

/*
Сервлет разложения на множители выполняет две операции, которые
должны быть атомарными: обновление кэшированного результата и условную
доставку кэшированных множителей, если кэшированное число
совпадает с запрошенным числом. Всякий раз, когда группа родственных
элементов данных должна действовать атомарно, рассмотрите
возможность создания для них немутируемого держателя, такого как
OneValueCache1 в листинге 3.12
*/
@Immutable
public class OneValueCache {
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger lastNumber, BigInteger[] lastFactors) {
        this.lastNumber = lastNumber;
        this.lastFactors = lastFactors;
    }

    public BigInteger[] getFactors(BigInteger i) {
        if (lastNumber == null || !lastNumber.equals(i)) {
            return null;
        } else {
            return Arrays.copyOf(lastFactors, lastFactors.length);
        }
    }
}
