package ru.ddc.listing_4_11_and_4_12_publishingvehicletracker;

import ru.ddc.annotations.GuardedBy;
import ru.ddc.annotations.ThreadSafe;
/*
Класс SafePoint в листинге 4.11 предоставляет метод доступа get, который
извлекает значения x и y одновременно, возвращая двухэлементный
массив. Наличие двух отдельных методов get для x и y заставило бы
вызывающий элемент кода видеть противоречивое значение. Используя
SafePoint, мы можем создать отслеживатель такси, который публикует
базовое мутируемое состояние без ущерба для потокобезопасности, как
показано в классе PublishingVehicleTracker в листинге 4.12.
Приватный конструктор существует для того, чтобы избежать состояния
гонки, возникающего при реализации конструктора копирования, такого как
this(p.x, p.y); это пример захвата частного конструктора (private constructor
capture) (Bloch и Gafte, 2005).
*/
@ThreadSafe
public class SafePoint {
    @GuardedBy("this") private int x, y;

    private SafePoint(int[] a) {
        this.x = a[0];
        this.y = a[1];
    }

    public SafePoint(SafePoint p) {
        this(p.get());
    }

    public SafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized int[] get() {
        return new int[] {x, y};
    }

    public synchronized void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
