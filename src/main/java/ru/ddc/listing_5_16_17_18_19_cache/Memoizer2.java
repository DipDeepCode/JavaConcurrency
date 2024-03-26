package ru.ddc.listing_5_16_17_18_19_cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
Класс Memoizer2 в листинге 5.17 улучшает конкурентное поведение класса
Memoizer1, заменив HashMap на потокобезопасный ConcurrentHashMap,
который устраняет необходимость в синхронизации при обращении
к резервному ассоциативному массиву Map и сериализацию, вызванную
синхронизацией метода compute.
Но у класса есть ошибки в кэше. Он содержит окно уязвимости, в котором
два потока, вызывающие метод compute одновременно, могут вычислить
одно и то же значение, что противоречит цели кэширования.
Если один поток в Memoizer2 запускает дорогостоящее вычисление, другие
потоки, не зная о нем, могут начать то же самое вычисление, как показано
на рисунке 5.3. Мы же хотим, чтобы поток, который ищет некую функцию,
уже вычисляемую другим потоком, видел этот поток, наблюдал за его
работой и пользовался его результатом.
*/
public class Memoizer2<A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer2(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        V result  = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
