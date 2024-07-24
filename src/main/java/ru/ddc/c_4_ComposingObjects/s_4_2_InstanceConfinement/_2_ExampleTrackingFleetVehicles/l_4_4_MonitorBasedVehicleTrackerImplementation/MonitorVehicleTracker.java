package ru.ddc.c_4_ComposingObjects.s_4_2_InstanceConfinement._2_ExampleTrackingFleetVehicles.l_4_4_MonitorBasedVehicleTrackerImplementation;

import ru.ddc.annotations.GuardedBy;
import ru.ddc.annotations.ThreadSafe;
import ru.ddc.c_4_ComposingObjects.s_4_2_InstanceConfinement._2_ExampleTrackingFleetVehicles.l_4_5_MutablePointClassSimilarToJavaAwtPoint.MutablePoint;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/*
Потоки будут обращаться к модели данных конкурентно, и она должна
быть потокобезопасной. В листинге 4.4 показана реализация отслеживателя
такси с использованием мониторного шаблона Java и класса
MutablePoint из листинга 4.5 для представления местоположений.
В отличие от класса MutablePoint, класс трекера потокобезопасен. Ни
ассоциативный массив, ни какие-либо мутируемые точки, которые он содержит,
не публикуются. Чтобы вернуть значение местоположения такси
вызывающему элементу кода, мы копируем его с помощью конструктора
копирования в классе MutablePoint или метода deepCopy, который создает
новый ассоциативный массив Map, значения которого являются копиями
ключей и значений из старого массива
*/
@ThreadSafe
public class MonitorVehicleTracker {
    @GuardedBy("this")
    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = locations;
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized void setLocations(String id, int x, int y) {
        MutablePoint loc = locations.get(id);
        if (loc == null) {
            throw new IllegalArgumentException("No such ID: " + id);
        }
        loc.x = x;
        loc.y = y;
    }

    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m) {
        Map<String, MutablePoint> result = new HashMap<>();
        for (String id : m.keySet()) {
            result.put(id, new MutablePoint(m.get(id)));
        }
        return Collections.unmodifiableMap(result);
    }
}
