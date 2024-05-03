package ru.ddc.c_1_Introduction.s_1_3_RisksOfThreads._1_SafetyHazards.l_1_2_ThreadSafeSequenceGenerator;

import ru.ddc.annotations.GuardedBy;
import ru.ddc.annotations.ThreadSafe;

/*
Класс UnsafeSequence можно исправить, сделав метод getNext синхронизированным,
как показано в классе Sequence в листинге 1.2, тем самым
предотвратив неудачное взаимодействие, показанное на рис. 1.1.
(Подробности описаны в главах 2 и 3.)
*/

@ThreadSafe
public class Sequence {
    @GuardedBy("this") private int nextValue;
    public synchronized int getNext() {
        return nextValue++;
    }
}
