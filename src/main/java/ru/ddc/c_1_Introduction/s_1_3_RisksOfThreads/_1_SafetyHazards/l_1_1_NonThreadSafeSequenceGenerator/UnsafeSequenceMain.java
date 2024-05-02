package ru.ddc.c_1_Introduction.s_1_3_RisksOfThreads._1_SafetyHazards.l_1_1_NonThreadSafeSequenceGenerator;

public class UnsafeSequenceMain {
    private static final UnsafeSequence sequence = new UnsafeSequence();

    public static void main(String[] args) {
        new Thread(new SomeThread(sequence), "thread 1").start();
        new Thread(new SomeThread(sequence), "thread 2").start();
    }
}
