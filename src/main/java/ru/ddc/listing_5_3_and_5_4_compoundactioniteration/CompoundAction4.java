package ru.ddc.listing_5_3_and_5_4_compoundactioniteration;

import java.util.Vector;

/*
Блокировка на стороне клиента влияет на масштабируемость. В течение
всего времени итеративного обхода владея замком на Vector, мы не
позволяем другим потокам изменять его (см. листинг 5.4). К сожалению, от
этого страдает конкурентность.

*/
public class CompoundAction4 {
    public static void iterate(Vector<Object> vector) {
        synchronized (vector) {
            for (Object o : vector) {
                doSomething(o);
            }
        }
    }

    private static void doSomething(Object object) {

    }
}
