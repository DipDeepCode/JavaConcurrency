package ru.ddc.c_3_SharingObjects.s_3_2_PublicationAndEscape._0_.l_3_7_ImplicitlyAllowingTheThisReferenceToEscape;

/*
Еще одним механизмом публикации объекта или его внутреннего состояния
является публикация экземпляра внутреннего класса, как показано в
листинге 3.7. Когда класс ThisEscape публикует слушателя EventListener,
он неявно публикует и окаймляющий его экземпляр ThisEscape,
потому что экземпляры внутреннего класса содержат скрытую ссылку
на него.
*/

public class ThisEscape {

    public ThisEscape(EventSource source) {
        source.registerListener(new EventListener() {
            public void onEvent(Event e) {
//                doSomeThing(e);
            }
        });
    }
}
