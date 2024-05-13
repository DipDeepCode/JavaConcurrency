package ru.ddc.c_2_ThreadSafety.s_2_3_Locking._2_Reentrancy.l_2_7_CodeThatWouldDeadlockIfIntrinsicLocksWereNotReentrant;

public class Widget {
    public  synchronized void doSomething() {
        System.out.println(this + ": doSomething from parent");
    }
}
