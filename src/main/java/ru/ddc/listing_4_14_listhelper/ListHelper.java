package ru.ddc.listing_4_14_listhelper;

import ru.ddc.annotations.NotThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
В листинге 4.14 показана безуспешная попытка создания вспомогательного
класса с атомарной операцией «добавить, если отсутствует» для
работы со списком List.
Почему безуспешная? Метод putIfAbsent синхронизирован? Да, но на
неправильном замке. Класс ListHelper обеспечивает только иллюзию
синхронизации: на самом деле операции используют разные замки, и метод
putIfAbsent не воспринимается другими операциями как атомарный.
*/

@NotThreadSafe
public class ListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !list.contains(x);
        if (absent) {
            list.add(x);
        }
        return absent;
    }
}
