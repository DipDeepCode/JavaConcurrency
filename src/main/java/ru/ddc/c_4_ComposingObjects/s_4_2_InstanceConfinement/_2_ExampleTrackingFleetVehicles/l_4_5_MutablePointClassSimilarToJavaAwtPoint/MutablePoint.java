package ru.ddc.c_4_ComposingObjects.s_4_2_InstanceConfinement._2_ExampleTrackingFleetVehicles.l_4_5_MutablePointClassSimilarToJavaAwtPoint;

import ru.ddc.annotations.NotThreadSafe;

@NotThreadSafe
public class MutablePoint {
    public int x, y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
}
