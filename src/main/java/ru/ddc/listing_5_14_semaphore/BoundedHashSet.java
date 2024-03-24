package ru.ddc.listing_5_14_semaphore;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/*
Семафоры полезны для реализации ресурсных пулов. Можно построить
пул фиксированного размера, который выдаст сбой, если запросить из
него лишний ресурс. Но лучше блокировать продвижение пустого пула,
которое можно при пополнении пула разблокировать. Инициализируя
Semaphore размером пула, вы приобретаете разрешение (методом acquire)
до того, как будет сделана попытка доставить ресурс из пула, и освобождаете
разрешение (методом release) после помещения ресурса обратно
в пул. Метод acquire блокирует продвижение, до тех пор пока пул не
перестанет быть пустым. Это техническое решение используется в классе
ограниченного буфера, описанном в главе 12. Более простой способ
конструирования блокирующего пула — очередь BlockingQueue для хранения
объединенных в пул ресурсов.
Как показано в классе BoundedHashSet в листинге 5.14, Semaphore превращает
любую коллекцию в блокирующую связанную коллекцию. Он
инициализируется желаемым максимальным размером коллекции. Операция
add приобретает разрешение для добавления элемента в базовую
коллекцию, и если она ничего не добавляет, то разрешение освобождается.
Схожим образом действует операция remove.
*/
public class BoundedHashSet<T> {
    private final Set<T> set;
    private final Semaphore sem;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<>());
        sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        sem.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        } finally {
            if (!wasAdded) {
                sem.release();
            }
        }
    }

    public boolean remove(T o) {
        boolean wasRemoved = set.remove(o);
        if (wasRemoved) {
            sem.release();
        }
        return wasRemoved;
    }
}
