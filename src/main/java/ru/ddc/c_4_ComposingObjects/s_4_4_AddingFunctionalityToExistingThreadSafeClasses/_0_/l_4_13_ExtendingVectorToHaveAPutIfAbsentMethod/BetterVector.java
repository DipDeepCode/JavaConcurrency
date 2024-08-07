package ru.ddc.c_4_ComposingObjects.s_4_4_AddingFunctionalityToExistingThreadSafeClasses._0_.l_4_13_ExtendingVectorToHaveAPutIfAbsentMethod;

import ru.ddc.annotations.ThreadSafe;

import java.util.Vector;
/*
Можно расширить класс (предназначенный для расширения). Класс
BetterVector в листинге 4.13 расширяет класс Vector, добавляя метод
putIfAbsent.
Но после расширения реализация политики синхронизации распределится
по нескольким отдельно сопровождаемым исходным файлам. То
есть, если базовый класс изменит замок для защиты своих переменных
состояния, то подкласс станет использовать неправильный замок для
управления конкурентным доступом к состоянию базового класса.
(Политика синхронизации класса Vector зафиксирована в его спецификации,
поэтому BetterVector от этой проблемы не пострадал.)
*/
@ThreadSafe
public class BetterVector<E> extends Vector<E> {
    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !contains(x);
        if (absent) {
            add(x);
        }
        return absent;
    }
}
