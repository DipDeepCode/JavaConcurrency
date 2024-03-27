package ru.ddc.listing_5_20_cachedservlet;

public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
