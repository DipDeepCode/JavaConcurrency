package ru.ddc.c_3_SharingObjects.s_3_3_ThreadConfinement._2_StackConfinement.l_3_9_ThreadConfinementOfLocalPrimitiveAndReferenceVariables;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class SpeciesGenderComparator implements Comparator<Animal> {
    @Override
    public int compare(Animal o1, Animal o2) {
        return 0;
    }

    @Override
    public Comparator<Animal> reversed() {
        return Comparator.super.reversed();
    }

    @Override
    public Comparator<Animal> thenComparing(Comparator<? super Animal> other) {
        return Comparator.super.thenComparing(other);
    }

    @Override
    public <U> Comparator<Animal> thenComparing(Function<? super Animal, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return Comparator.super.thenComparing(keyExtractor, keyComparator);
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<Animal> thenComparing(Function<? super Animal, ? extends U> keyExtractor) {
        return Comparator.super.thenComparing(keyExtractor);
    }

    @Override
    public Comparator<Animal> thenComparingInt(ToIntFunction<? super Animal> keyExtractor) {
        return Comparator.super.thenComparingInt(keyExtractor);
    }

    @Override
    public Comparator<Animal> thenComparingLong(ToLongFunction<? super Animal> keyExtractor) {
        return Comparator.super.thenComparingLong(keyExtractor);
    }

    @Override
    public Comparator<Animal> thenComparingDouble(ToDoubleFunction<? super Animal> keyExtractor) {
        return Comparator.super.thenComparingDouble(keyExtractor);
    }
}
