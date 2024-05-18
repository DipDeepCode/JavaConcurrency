package ru.ddc.c_3_SharingObjects.s_3_1_Visibility._1_StaleData.l_3_3_ThreadSafeMutableIntegerHolder;

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
