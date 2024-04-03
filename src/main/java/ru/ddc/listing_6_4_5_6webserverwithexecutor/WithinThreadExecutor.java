package ru.ddc.listing_6_4_5_6webserverwithexecutor;

import java.util.concurrent.Executor;

/*
Также с помощью исполнителя можно побудить TaskExecutionWebServer
вести себя как один поток и выполнять каждую задачу синхронно перед
возвращением из метода execute, как показано в WithinThreadExecutor
в листинге 6.6.
*/
public class WithinThreadExecutor implements Executor {
    @Override
    public void execute(Runnable r) {
        r.run();
    }
}
