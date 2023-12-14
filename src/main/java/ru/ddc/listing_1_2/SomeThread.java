package ru.ddc.listing_1_2;

public class SomeThread extends Thread {
    private final Sequence sequence;

    public SomeThread(Sequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " - " +  sequence.getNext());
        }
    }
}
