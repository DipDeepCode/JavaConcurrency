package ru.ddc.listing_1_2;

/*
Класс UnsafeSequence можно исправить, сделав метод getNext синхрони-
зированным, как показано в классе Sequence в листинге 1.22, тем самым
предотвратив неудачное взаимодействие, показанное на рис. 1.1. (По-
дробности описаны в главах 2 и 3.)
*/

public class Sequence {
    private int nextValue;
    public synchronized int getNext() {
        return nextValue++;
    }
}
