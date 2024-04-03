package ru.ddc.listing_6_4_5_6webserverwithexecutor;

import java.util.concurrent.Executor;

/*
После внедрения исполнителя Executor класс TaskExecutionWebServer
станет вести себя как ThreadPerTaskWebServer (см. листинг 6.5).
*/
public class ThreadPerTaskExecutor implements Executor {
    @Override
    public void execute(Runnable r) {
        new Thread(r).start();
    }
}
