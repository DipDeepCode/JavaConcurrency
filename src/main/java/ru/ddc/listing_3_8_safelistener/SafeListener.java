package ru.ddc.listing_3_8_safelistener;

/*
Чтобы зарегистрировать слушателя событий или запустить поток из
конструктора, воспользуйтесь приватным конструктором и публичным
фабричным методом, как показано в SafeListener в листинге 3.8.
*/

public class SafeListener {
    private final EventListener listener;
    private SafeListener() {
        listener = new EventListener() {
            public void onEvent(Event e) {
//                doSomething(e);
            }
        };
    }

    public static SafeListener newInstance(EventSource source) {
        SafeListener safe = new SafeListener();
        source.registerListener(safe.listener);
        return safe;
    }
}
