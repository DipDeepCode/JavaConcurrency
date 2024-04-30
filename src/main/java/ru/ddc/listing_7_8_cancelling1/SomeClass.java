package ru.ddc.listing_7_8_cancelling1;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
В листинге 7.8 объект Runnable запускает задачу в вызывающем потоке
и планирует вторую задачу, которая отменит первую после заданного
интервала времени.
Данный пример нарушает правило: прежде чем прерывать поток, узнать
его политику прерывания. Метод timedRun вызывается из произвольного
потока, и если первая задача завершится до истечения тайм-аута,
то задача отмены может начать действовать после возвращения метода
к вызвавшему его элементу кода. Чтобы устранить риск нежелательного
результата, нужно использовать объект ScheduledFuture, возвращаемый
методом schedule, чтобы отменить задачу отмены.
Если задача не отзывается на прерывание, то метод timedRun не вернется
до тех пор, пока она не завершится, что может произойти не скоро
*/
public class SomeClass {
    private static final ScheduledExecutorService cancelExec = new ScheduledThreadPoolExecutor(1);

    public static void timedRun(Runnable r, long timeout, TimeUnit unit) {
        final Thread taskThread = Thread.currentThread();
        cancelExec.schedule(new Runnable() {
            @Override
            public void run() {
                taskThread.interrupt();
            }
        }, timeout, unit);
        r.run();
    }
}
