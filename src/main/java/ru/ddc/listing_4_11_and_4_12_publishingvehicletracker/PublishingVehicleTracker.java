package ru.ddc.listing_4_11_and_4_12_publishingvehicletracker;

import ru.ddc.annotations.ThreadSafe;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/*
Класс PublishingVehicleTracker делегирует свои обязанности по
потокобезопасности базовому хеш-массиву ConcurrentHashMap, содержимым
которого на этот раз являются потокобезопасные мутируемые точки.
Метод getLocation возвращает неизменяемую копию базового ассоциативного
массива Map. Вызывающие элементы кода не могут добавлять
или удалять такси, но могут изменять местоположение одного из них
путем мутирования значений SafePoint в возвращенном ассоциативном
массиве Map. «Живой» характер ассоциативного массива Map может быть
преимуществом либо недостатком, в зависимости от требований. Класс
PublishingVehicleTracker является потокобезопасным, благодаря тому
что он не накладывает дополнительные ограничения на допустимые значения
местоположений такси. Чтобы запретить изменения местоположения
такси или снабдить их дополнительными свойствами, нужно отказаться
от класса PublishingVehicleTracker.
*/
@ThreadSafe
public class PublishingVehicleTracker {
    private final Map<String, SafePoint> locations;
    private final Map<String, SafePoint> unmodifiableMap;

    public PublishingVehicleTracker(Map<String, SafePoint> locations) {
        this.locations = new ConcurrentHashMap<>(locations);
        this.unmodifiableMap = Collections.unmodifiableMap(this.locations);
    }

    public Map<String, SafePoint> getLocations() {
        return unmodifiableMap;
    }

    public SafePoint getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (!locations.containsKey(id)) {
            throw new IllegalArgumentException("недопустимое имя транспортного средства: " + id);
        } else {
            locations.get(id).set(x, y);
        }
    }
}
