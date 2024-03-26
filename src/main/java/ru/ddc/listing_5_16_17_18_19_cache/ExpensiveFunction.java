package ru.ddc.listing_5_16_17_18_19_cache;

import java.math.BigInteger;

public class ExpensiveFunction implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        // после глубокого раздумья...
        return new BigInteger(arg);
    }
}
