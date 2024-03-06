package ru.ddc.listing_4_10_faildelegating;

import java.util.concurrent.atomic.AtomicInteger;

/*
Большинство композитных классов не так просты, как VisualComponent:
у них есть инварианты, которые связывают их компонентные переменные
состояния. Класс NumberRange в листинге 4.10 для управления своим
состоянием использует два объекта AtomicInteger, но накладывает
дополнительное ограничение — первое число должно быть меньше или равно
второму.
Класс NumberRange не является потокобезопасным: он не соблюдает инвариант,
который ограничивает lower и upper. Методы setLower и setUpper
пытаются соблюсти этот инвариант, но безуспешно, так как являются
последовательностями операций «проверить и затем действовать», но их
блокировка не обеспечивает им атомарности. Если в диапазоне (0, 10) один
поток вызовет setLower(5), а другой в то же время — setUpper(4), то при
неудачной временной координации оба потока пройдут проверку в методах
доступа set, и выполнятся два изменения, создав диапазон (5, 4) —
недопустимое состояние. В отличие от базовых AtomicInteger, составной
класс непотокобезопасен, а класс NumberRange не может делегировать
потокобезопасность переменным состояния lower и upper, так как они не
являются независимыми.
Потокобезопасность класса NumberRange может обеспечить блокировка,
которая сохранит его инварианты и защитит lower и upper замком, чтобы
препятствовать их публикации.
*/
public class NumberRange {
    // INVARIANT: lower <= upper
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    public void setLower(int i) {
        // Предупреждение - небезопасная операция "проверить и затем действовать"
        if (i > upper.get()) {
            throw new IllegalArgumentException("не могу установить lower равным " + i + " > upper");
        }
        lower.set(i);
    }

    public void setUpper(int i) {
        // Предупреждение - небезопасная операция "проверить и затем действовать"
        if (i < lower.get()) {
            throw new IllegalArgumentException("не могу установить upper равным " + i + " < lower");
        }
        upper.set(i);
    }

    public boolean isInRange(int i) {
        return (i >= lower.get()) && i <= upper.get();
    }
}
