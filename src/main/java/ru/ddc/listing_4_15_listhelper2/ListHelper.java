package ru.ddc.listing_4_15_listhelper2;

import ru.ddc.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Документация для Vector и синхронизированных оберточных классов
утверждает, хотя и косвенно, что они поддерживают блокировку на стороне
клиента, используя внутренний замок для Vector или оберточной
коллекции (не путайте с обернутой коллекцией). Листинг 4.15 показывает
в потокобезопасном списке List, как операция putIfAbsent правильно
использует блокировку на стороне клиента.
*/
@ThreadSafe
public class ListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    public boolean putIfAbsent(E x) {
        synchronized (list) {
            boolean absent = !list.contains(x);
            if (absent) {
                list.add(x);
            }
            return absent;
        }
    }
}
