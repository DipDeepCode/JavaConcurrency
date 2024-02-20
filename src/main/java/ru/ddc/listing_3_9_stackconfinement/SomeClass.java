package ru.ddc.listing_3_9_stackconfinement;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

/*
При ограничении стеком (stack confinement) объект может быть достигнут
только через локальные переменные. Они внутренне ограничены стеком
выполняющего потока, в котором находятся. Ограничение стеком также
называют внутрипоточным или локальным для потока использованием.
Примитивные локальные переменные, такие как numPairs в loadTheArk
в листинге 3.9, всегда ограничены стеком, и получить на них ссылку невозможно.
*/

public class SomeClass {
    private final Ark ark = new Ark();
    public int loadTheArk(Collection<Animal> candidates) {
        SortedSet<Animal> animals;
        int numPairs = 0;
        Animal candidate = null;
        // не дайте animals ускользнуть!
        animals = new TreeSet<>(new SpeciesGenderComparator());
        animals.addAll(candidates);
        for (Animal a : animals) {
            if (candidate == null || !candidate.isPotentialMate(a))
                candidate = a;
            else {
                ark.load(new AnimalPair(candidate, a));
                ++numPairs;
                candidate = null;
            }
        }
        return numPairs;
    }
}
