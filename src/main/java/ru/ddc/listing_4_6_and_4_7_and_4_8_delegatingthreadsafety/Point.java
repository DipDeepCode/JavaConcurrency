package ru.ddc.listing_4_6_and_4_7_and_4_8_delegatingthreadsafety;

import ru.ddc.annotations.Immutable;

@Immutable
public class Point {
    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
