package ru.ddc.listing_3_3_synchronizedinteger;

import ru.ddc.annotations.GuardedBy;
import ru.ddc.annotations.ThreadSafe;

/*
Чтобы сделать MutableInteger потокобезопасным, необходимо синхронизировать
методы доступа get и set, как показано в Synchronizedinteger
в листинге 3.3. Синхронизации только метода set будет недостаточно:
потоки, вызывающие get, все равно будут видеть устаревшие значения.
*/
@ThreadSafe
public class SynchronizedInteger {
    @GuardedBy("this") private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized void set(int value) {
        this.value = value;
    }
}
