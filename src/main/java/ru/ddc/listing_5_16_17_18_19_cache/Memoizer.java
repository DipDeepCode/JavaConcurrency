package ru.ddc.listing_5_16_17_18_19_cache;

import ru.ddc.listing_5_12_futuretask.LaunderThrowable;

import java.util.Map;
import java.util.concurrent.*;

/*
Класс Memoizer3 уязвим, потому что составная операция «добавить, если
отсутствует» выполняется на резервном ассоциативном массиве, и ее нельзя
сделать атомарной с помощью замка. Класс Memoizer в листинге 5.19
закрывает окно уязвимости атомарным методом putIfAbsent ассоциативного
массива ConcurrentMap.
Кэширование Future создает угрозу загрязнения кэша: если вычисление
отменяется или завершится сбоем, то дальнейшие попытки вычислить
результат тоже будут указывать на отмену или сбой. Поэтому Memoizer
удаляет Future из кэша, если обнаруживает, что вычисление было отменено.
Также можно удалить Future при обнаружении исключения
Runtime Exception, если вычисление может быть успешным при будущей
попытке. Memoizer не решает задачу с истечением срока действия кэша,
но он может обратиться к подклассу FutureTask, который ассоциирует
время истечения срока с каждым результатом и периодически проверяет
кэш на наличие данных с истекшим сроком. (Также Memoizer не решает
проблему вытеснения кэша, при котором старые записи удаляются для
разгрузки памяти.)
*/
public class Memoizer<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    public Memoizer(Computable<A, V> c) {
        this.c = c;
    }

    public V compute(final A arg) throws InterruptedException {
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> eval = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return c.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<>(eval);
                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();
            } catch (CancellationException e) {
                cache.remove(arg, f);
            } catch (ExecutionException e) {
                throw LaunderThrowable.launderThrowable(e.getCause());
            }
        }
    }
}
