package ru.ddc.listing_1_2;

public class SequenceMain {
    private static final Sequence sequence = new Sequence();
    public static void main(String[] args) {
        new Thread(new SomeThread(sequence), "thread 1").start();
        new Thread(new SomeThread(sequence), "thread 2").start();
    }
}
