package ru.ddc.listing_5_16_17_18_19_cache;

public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
