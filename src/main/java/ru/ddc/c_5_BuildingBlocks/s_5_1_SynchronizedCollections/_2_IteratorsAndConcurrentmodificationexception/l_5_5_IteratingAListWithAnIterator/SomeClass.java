package ru.ddc.c_5_BuildingBlocks.s_5_1_SynchronizedCollections._2_IteratorsAndConcurrentmodificationexception.l_5_5_IteratingAListWithAnIterator;

import ru.ddc.c_2_ThreadSafety.s_2_3_Locking._2_Reentrancy.l_2_7_CodeThatWouldDeadlockIfIntrinsicLocksWereNotReentrant.Widget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
В листинге 5.5 показан итеративный обход коллекции с использованием
синтаксиса цикла for-each. javac генерирует код, который использует
итератор, многократно вызывая hasNext и next. Чтобы предотвратить
исключение ConcurrentModificationException, надо владеть коллекционным
замком в течение всего времени итеративного обхода.
Однако блокировка коллекции во время итеративного обхода не всегда
уместна. При больших коллекциях и задачах ожидание завершения итерации
становится слишком долгим. Если коллекция заперта, как в листинге 5.4,
то метод doSomething вызывается с удержанием замка, что является
фактором риска возникновения взаимной блокировки (см. главу 10).
Любое запирание коллекций на значительные периоды времени снижает
масштабируемость приложения: чем дольше замок занят, тем больше вероятность
того, что он будет оспариваться другими потоками
*/
public class SomeClass {

    List<Widget> widgetList = Collections.synchronizedList(new ArrayList<>());

    public void someMethod() {
        for (Widget w : widgetList) {
            doSomething();
        }
    }

    private void doSomething() {

    }
}
