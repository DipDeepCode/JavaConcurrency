package ru.ddc.listing_1_1;

public class SomeThread extends Thread {

    private final UnsafeSequence sequence;

    public SomeThread(UnsafeSequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " - " +  sequence.getNext());
        }
    }
}
