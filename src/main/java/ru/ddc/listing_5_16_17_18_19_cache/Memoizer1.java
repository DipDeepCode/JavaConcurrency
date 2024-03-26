package ru.ddc.listing_5_16_17_18_19_cache;

import ru.ddc.annotations.GuardedBy;

import java.util.HashMap;
import java.util.Map;

/*
Интерфейс Computable<A,V> в листинге 5.16 описывает функцию с типом
A на входе и типом V на выходе. Класс ExpensiveFunction, который
реализует интерфейс Computable, занимает много времени для вычисления
своего результата. Мы создадим оболочку для Computable, которая
запомнит результаты предыдущих вычислений и инкапсулирует процесс
кэширования. (Это техническое решение называется мемоизацией —
memoization.)
Класс Memoizer1 в листинге 5.16 показывает использование хеш-массива
HashMap для хранения результатов предыдущих вычислений. Метод
compute проверяет, находится ли нужный результат в кэше, и возвращает
предварительно вычисленное значение, если это так. В противном случае
перед возвращением результат вычисляется и кэшируется в HashMap.
Класс HashMap не является потокобезопасным. Чтобы два потока не
обращались к HashMap одновременно, Memoizer1 синхронизирует метод compute
целиком, создавая проблему масштабируемости, так как выполнять
вычисления может только один поток за раз
*/
public class Memoizer1<A, V> implements Computable<A, V> {
    @GuardedBy("this")
    private final Map<A, V> cache = new HashMap<>();
    private final Computable<A, V> c;

    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V result  = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
