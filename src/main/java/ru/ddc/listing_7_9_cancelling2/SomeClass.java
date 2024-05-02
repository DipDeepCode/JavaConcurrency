package ru.ddc.listing_7_9_cancelling2;

import ru.ddc.listing_5_12_futuretask.LaunderThrowable;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
В листинге 7.9 после запуска задачного потока метод timedRun выполняет
хронометрированный метод присоединения join1 с только что созданным
потоком. После своего возвращения метод join проверяет, выдала ли
задача исключение, и если да, то повторно выдает его в потоке, вызывающем
метод timedRun. Сохраненный объект Throwable используется двумя
потоками, и поэтому объявляется волатильным с целью его безопасной
публикации из задачного потока в поток метода timedRun.
Мы устранили проблемы, описанные в предыдущих примерах, но пока
не знаем, возвращено ли управление и истек ли тайм-аут метода join
*/
public class SomeClass {

    public static void timedRun(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        final ScheduledExecutorService cancelExec = new ScheduledThreadPoolExecutor(1);
        class RethrowableTask implements Runnable {
            private volatile Throwable t;

            @Override
            public void run() {
                try  {
                    r.run();
                } catch (Throwable t) {
                    this.t = t;
                }
            }

            void rethrow() {
                if (t != null) {
                    throw LaunderThrowable.launderThrowable(t);
                }
            }
        }

        RethrowableTask task = new RethrowableTask();
        final Thread taskThread = new Thread(task);
        taskThread.start();
        cancelExec.schedule(new Runnable() {
            @Override
            public void run() {
                taskThread.interrupt();
            }
        }, timeout, unit);
        taskThread.join(unit.toMillis(timeout));
        task.rethrow();
    }
}
