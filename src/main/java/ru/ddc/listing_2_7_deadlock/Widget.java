package ru.ddc.listing_2_7_deadlock;

public class Widget {
    public  synchronized void doSomething() {
        System.out.println(this + ": doSomething from parent");
    }
}
