package ru.ddc.listing_5_16_17_18_19_cache;

import ru.ddc.listing_5_12_futuretask.LaunderThrowable;

import java.util.Map;
import java.util.concurrent.*;

/*
В листинге 5.18 использован ConcurrentHashMap<A,Future<V>> вместо
ConcurrentHashMap<A,V>. Класс Memoizer3 сначала проверяет факт запуска
вычисления (в Memoizer2 проверялось завершение) и ожидает результата
существующего вычисления. В отсутствие подтверждения запуска он
создает объект FutureTask, регистрирует его в ассоциативном массиве
и запускает вычисление. Результат прозрачен для элемента кода,
вызывающего метод Future.get.
Реализация класса Memoizer3 демонстрирует очень хорошую конкурентность,
но остается окно уязвимости (оно меньше, чем в Memoizer2),
в котором два потока могут вычислить одно и то же значение. Блок if
в методе compute по-прежнему является неатомарной последовательностью
«проверить и затем действовать», и два потока могут одновременно
вызвать метод compute с одним и тем же значением, увидеть, что кэш не
содержит желаемого значения, и начать вычисление
*/
public class Memoizer3<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (f == null) {
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<>(eval);
            f = ft;
            cache.put(arg, ft);
            ft.run(); // Вызов c.compute происходит тут
        }
        try {
            return f.get();
        } catch (ExecutionException e) {
            throw LaunderThrowable.launderThrowable(e.getCause());
        }
    }
}
