package ru.ddc.c_3_SharingObjects.s_3_4_Immutability._0_.l_3_11_ImmutableClassBuiltOutOfMutableUnderlyingObjects;

import ru.ddc.annotations.Immutable;

import java.util.HashSet;
import java.util.Set;

/*
Немутируемые объекты могут использовать мутируемые объекты для
управления своим состоянием, как показано в листинге 3.11. Объект
Set, который хранит имена, является мутируемым, но построение класса
ThreeStooges делает невозможным изменение этого объекта после конструирования.
Ссылка stooges является финальной, и состояние объекта
достигается через финальное поле. Конструктор не делает ничего, что могло
бы сделать ссылку this доступной для кода, отличного от конструктора
и элемента кода, вызывающего объект.
*/
@Immutable
public class ThreeStooges {
    private final Set<String> stooges = new HashSet<>();

    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }
}
