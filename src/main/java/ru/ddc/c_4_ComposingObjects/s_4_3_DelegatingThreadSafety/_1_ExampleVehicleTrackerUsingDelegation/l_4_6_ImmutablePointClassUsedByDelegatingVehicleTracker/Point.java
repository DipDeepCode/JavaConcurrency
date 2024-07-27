package ru.ddc.c_4_ComposingObjects.s_4_3_DelegatingThreadSafety._1_ExampleVehicleTrackerUsingDelegation.l_4_6_ImmutablePointClassUsedByDelegatingVehicleTracker;

import ru.ddc.annotations.Immutable;

@Immutable
public class Point {
    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
