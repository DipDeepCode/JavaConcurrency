package ru.ddc.c_4_ComposingObjects.s_4_3_DelegatingThreadSafety._1_ExampleVehicleTrackerUsingDelegation.l_4_7_DelegatingThreadSafetyToAConcurrentHashMap;

import ru.ddc.annotations.ThreadSafe;
import ru.ddc.c_4_ComposingObjects.s_4_3_DelegatingThreadSafety._1_ExampleVehicleTrackerUsingDelegation.l_4_6_ImmutablePointClassUsedByDelegatingVehicleTracker.Point;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
Давайте сконструируем версию трекера такси, которая делегирует свои
обязанности по потокобезопасности неизменяемому классу. Местоположения
хранятся в ассоциативном массиве Map, поэтому мы начнем
с потокобезопасной реализации хеш-массива ConcurrentHashMap. Как показано
в листинге 4.6, хранение местоположений происходит с помощью
немутируемого класса Point вместо MutablePoint.

Обратите внимание, что мы немного изменили поведение класса трекера такси: мониторная
версия возвращала снимок местоположений, а делегирующая версия
возвращает их неизменяемое, но «живое» представление. Это означает,
что если поток A вызывает метод getLocations, а поток B позже изменяет
местоположение некоторых точек, эти изменения отражаются в хешмассиве,
возвращенном потоку A. Как мы отмечали ранее, это может
быть преимуществом (более актуальные данные) либо недостатком (потенциально
противоречивое представление таксопарка), в зависимости от ваших требований.

Если требуется неизменное представление парка, то метод getUnmodifiableLocations
может вернуть неглубокую копию хеш-массива местоположений. Поскольку
содержимое хеш-массива является немутируемым, необходимо
копировать только структуру хеш-массива, а не его содержимое. Как показано в
листинге 4.8, возвращается простой хеш-массив HashMap.
*/
@ThreadSafe
public class DelegatingVehicleTracker {

    private final ConcurrentMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        locations = new ConcurrentHashMap<>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, Point> getLocations() {
        return unmodifiableMap;
    }

    public Map<String, Point> getUnmodifiableLocations() {
        return Collections.unmodifiableMap(new HashMap<>(locations));
    }

    public Point getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (locations.replace(id, new Point(x, y)) == null) {
            throw new IllegalArgumentException("недопустимое имя такси: " + id);
        }
    }
}
