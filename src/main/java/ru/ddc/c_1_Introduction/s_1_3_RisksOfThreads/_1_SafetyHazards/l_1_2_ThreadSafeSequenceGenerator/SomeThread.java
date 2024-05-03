package ru.ddc.c_1_Introduction.s_1_3_RisksOfThreads._1_SafetyHazards.l_1_2_ThreadSafeSequenceGenerator;

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
