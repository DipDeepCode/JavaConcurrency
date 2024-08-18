package ru.ddc.c_5_BuildingBlocks.s_5_1_SynchronizedCollections._1_ProblemsWithSynchronizedCollections.l_5_3_IterationThatMayThrowArrayIndexOutOfBoundsException;

import java.util.Vector;

/*
Риск изменения размера списка между вызовами методов также
присутствует во время итеративного обхода элементов Vector, как показано
в листинге 5.3.
Если другой поток удалит элемент во время итеративного обхода Vector,
появится исключение ArrayIndexOutOfBoundsException.
*/
public class CompoundAction3 {
    public static void iterate(Vector<Object> vector) {
        for (Object o : vector) {
            doSomething(o);
        }
    }

    private static void doSomething(Object object) {

    }
}
