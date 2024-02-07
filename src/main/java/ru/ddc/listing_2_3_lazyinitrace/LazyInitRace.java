package ru.ddc.listing_2_3_lazyinitrace;

import ru.ddc.annotations.NotThreadSafe;

/*
Класс LazyInitRace содержит состояния гонки. Предположим, что потоки
A и B выполняют метод getInstance в одно и то же время. A видит, что
поле instance равно null, и создает новый ExpensiveObject. Поток B также
проверяет, равно ли поле instance тому же значению null. Наличие в поле
значения null в этот момент зависит от временной координации, включая
капризы планирования и количество времени, нужного для создания экземпляра
объекта ExpensiveObject и установки значения в поле instance.
Если поле instance равно null, когда B его проверяет, два элемента кода,
вызывающих метод getInstance, могут получить два разных результата,
даже если метод getInstance предположительно должен всегда возвращать
один и тот же экземпляр.
*/
@NotThreadSafe
public class LazyInitRace {
    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance() {
        if (instance == null) {
            instance = new ExpensiveObject();
        }
        return instance;
    }
}
