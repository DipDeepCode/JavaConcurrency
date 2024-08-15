package ru.ddc.c_5_BuildingBlocks.s_5_1_SynchronizedCollections._1_ProblemsWithSynchronizedCollections.l_5_1_CompoundActionsOnAVectorThatMayProduceConfusingResults;

import java.util.Vector;

/*
В листинге 5.1 показаны два метода, работающие над объектом Vector:
getLast и deleteLast, которые являются последовательностями действий
«проверить и затем действовать». Каждый вызывает метод size для определения
размера массива и использует результирующее значение для
извлечения или удаления последнего элемента.
Сами методы не могут повредить Vector — опасен элемент кода, вызывающий их.
Если поток A вызывает метод getLast, а поток B — метод
deleteLast, то эти операции перемежаются, как показано на рисунке 5.1,
и метод getLast выдает исключение ArrayIndexOutOfBoundsException.
Между вызовом метода size и последующим вызовом метода getlast
объект Vector сжимается и индекс, вычисленный на первом шаге,
перестает быть допустимым.
*/
public class CompoundActions {
    public static Object getLast(Vector<Object> list) {
        int lastIndex = list.size() - 1;
        return list.get(lastIndex);
    }

    public static void deleteLast(Vector<Object> list) {
        int lastIndex = list.size() - 1;
        list.remove(lastIndex);
    }
}
