package ru.ddc.c_4_ComposingObjects.s_4_1_DesigningAThreadSafeClass._0_.l_4_1_SimpleThreadSafeCounterUsingTheJavaMonitorPattern;

import ru.ddc.annotations.GuardedBy;
import ru.ddc.annotations.ThreadSafe;

/*
Если все поля объекта имеют примитивный тип, то можно говорить, что
они содержат все состояние объекта. В листинге 4.1 единственное поле
value содержит состояние счетчика. Несколько примитивных полей представляют
собой n-элементый кортеж значений. Состоянием двумерной
точки Point является ее значение (x, y). Состояние списка LinkedList
включает состояния всех объектов связных узлов, входящих в список,
если содержит поля-ссылки на эти узлы.
*/
@ThreadSafe
public final class Counter {
    @GuardedBy("this") private long value = 0;

    public synchronized long getValue() {
        return value;
    }

    public synchronized long increment() {
        if (value == Long.MAX_VALUE) {
            throw new IllegalArgumentException("Переполнение счетчика");
        }
        return ++value;
    }
}
