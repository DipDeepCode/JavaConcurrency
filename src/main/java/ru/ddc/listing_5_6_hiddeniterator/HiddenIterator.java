package ru.ddc.listing_5_6_hiddeniterator;

import ru.ddc.annotations.GuardedBy;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/*
Использовать блокировку везде, где может выполняться итеративный
обход совместной коллекции, не так просто. Итераторы бывают скрытыми,
как в HiddenIterator в листинге 5.6, где явного итеративного обхода нет,
но код, выделенный жирным шрифтом, его подразумевает. Конкатенация
строк преобразуется компилятором в вызов StringBuilder.append(Object),
который, в свою очередь, активирует метод toString, который в стандартных
коллекциях выполняет итеративный обход.
Пока коллекция перебирается методом toString, метод addTenThings может
выдавать исключение ConcurrentModificationException. Дело в том,
что класс HiddenIterator не является потокобезопасным, и замок его итератора
должен быть приобретен перед использованием метода set в вызове
println, но код отладки и журналирования обычно этим пренебрегает.
Чем больше расстояние между состоянием и синхронизацией, защищающей
его, тем больше вероятность того, что кто-то забудет применить
надлежащую синхронизацию при доступе к этому состоянию. Если
HiddenIterator обернет хеш-множество HashSet в synchronizedSet,
инкапсулируя синхронизацию, ошибки не будет.
*/
public class HiddenIterator {
    @GuardedBy("this")
    private final Set<Integer> set = new HashSet<>();

    public synchronized void add(Integer i) {
        set.add(i);
    }

    public synchronized void remove(Integer i) {
        set.remove(i);
    }

    public void addTenThings() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            add(r.nextInt());
        }
        System.out.println("Добавлено десять элементов в " + set);
    }
}
