package ru.ddc.c_1_Introduction.s_1_3_RisksOfThreads._1_SafetyHazards.l_1_2_ThreadSafeSequenceGenerator;

public class SequenceMain {
    private static final Sequence sequence = new Sequence();
    public static void main(String[] args) {
        new Thread(new SomeThread(sequence), "thread 1").start();
        new Thread(new SomeThread(sequence), "thread 2").start();
    }
}
