package ru.ddc.c_4_ComposingObjects.s_4_2_InstanceConfinement._1_TheJavaMonitorPattern.l_4_3_GuardingStateWithAPrivateLock;

/*
Вместо мониторного шаблона Java, который является просто соглашением,
для защиты состояния объекта можно использовать другой замковый
объект, например приватный замок, как показано в листинге 4.3
Преимущество использования приватного замка — в его инкапсуляции,
которая не позволяет клиентскому коду приобрести его и участвовать
в политике его синхронизации, избавляя от необходимости проверки
всей программы.
*/

import ru.ddc.annotations.GuardedBy;

public class PrivateLock {
    private final Object myLock = new Object();
    @GuardedBy("myLock") private Widget widget;

    void someMethod() {
        synchronized (myLock) {
            // Обратиться и изменить состояние виджета
        }
    }
}
