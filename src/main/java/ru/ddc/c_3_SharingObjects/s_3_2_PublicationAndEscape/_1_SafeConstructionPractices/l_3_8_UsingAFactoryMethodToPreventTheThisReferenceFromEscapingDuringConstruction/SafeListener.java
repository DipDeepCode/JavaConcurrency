package ru.ddc.c_3_SharingObjects.s_3_2_PublicationAndEscape._1_SafeConstructionPractices.l_3_8_UsingAFactoryMethodToPreventTheThisReferenceFromEscapingDuringConstruction;

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
