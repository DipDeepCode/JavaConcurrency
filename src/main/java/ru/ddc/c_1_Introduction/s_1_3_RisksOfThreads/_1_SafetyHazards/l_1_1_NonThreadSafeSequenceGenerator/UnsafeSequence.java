package ru.ddc.c_1_Introduction.s_1_3_RisksOfThreads._1_SafetyHazards.l_1_1_NonThreadSafeSequenceGenerator;

import ru.ddc.annotations.NotThreadSafe;

/*
При неудачной временной координации два потока могут вызвать метод
getNext и получить неуникальные значения (см. рис. 1.1). Операция
приращения nextvalue++ состоит из трех отдельных операций: чтения значения,
добавления в него единицы и записи нового значения. Поскольку операции
в потоках произвольно перемежаются, два потока могут прочитать
значение в одно и то же время, добавить в него единицу и вернуть один
и тот же порядковый номер.
*/
@NotThreadSafe
public class UnsafeSequence {
    private int value;

    public int getNext() {
        return value++;
    }
}
