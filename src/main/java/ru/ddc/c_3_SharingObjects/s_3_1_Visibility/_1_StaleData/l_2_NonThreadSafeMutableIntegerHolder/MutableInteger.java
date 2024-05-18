package ru.ddc.c_3_SharingObjects.s_3_1_Visibility._1_StaleData.l_2_NonThreadSafeMutableIntegerHolder;

import ru.ddc.annotations.NotThreadSafe;

/*
Класс MutableInteger в листинге 3.2 не является потокобезопасным,
поскольку доступ к полю value осуществляется как из get, так и из set без
синхронизации. Причем он подвержен устаревшим значениям: если один
поток вызывает set, то другие потоки, вызывающие get, могут не увидеть
обновления.
*/
@NotThreadSafe
public class MutableInteger {
    private int value;

    public int get() {
        return value;
    }

    public void set(int value) {
        this.value = value;
    }
}
