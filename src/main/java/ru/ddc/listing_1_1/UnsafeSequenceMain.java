package ru.ddc.listing_1_1;

public class UnsafeSequenceMain {
    private static final UnsafeSequence sequence = new UnsafeSequence();

    public static void main(String[] args) {
        new Thread(new SomeThread(sequence), "thread 1").start();
        new Thread(new SomeThread(sequence), "thread 2").start();
    }
}
