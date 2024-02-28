package ru.ddc.listing_4_2_oneinstance;

import ru.ddc.annotations.GuardedBy;
import ru.ddc.annotations.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

/*
Класс PersonSet в листинге 4.2 иллюстрирует, как ограничение одним
экземпляром и блокировка работают вместе. Состояние объекта PersonSet
управляется непотокобезопасным объектом HashSet. Но mySet является
приватным и не может ускользнуть, поэтому объект HashSet ограничен
объектом PersonSet. Единственными ветвями кода, которые могут обратиться
к mySet, являются методы addPerson и containsPerson, и каждый
из них приобретает замок на PersonSet, все состояние которого защищено
его внутренним замком.
*/
@ThreadSafe
public class PersonSet {
    @GuardedBy("this") private final Set<Person> mySet = new HashSet<>();

    public synchronized void addPerson(Person p) {
        mySet.add(p);
    }

    public synchronized boolean containsPerson(Person p) {
        return mySet.contains(p);
    }
}
