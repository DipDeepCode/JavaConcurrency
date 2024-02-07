package ru.ddc.listing_1_2;

import ru.ddc.annotations.GuardedBy;
import ru.ddc.annotations.ThreadSafe;

/*
Класс UnsafeSequence можно исправить, сделав метод getNext синхрони-
зированным, как показано в классе Sequence в листинге 1.22, тем самым
предотвратив неудачное взаимодействие, показанное на рис. 1.1. (По-
дробности описаны в главах 2 и 3.)
*/

@ThreadSafe
public class Sequence {
    @GuardedBy("this") private int nextValue;
    public synchronized int getNext() {
        return nextValue++;
    }
}
