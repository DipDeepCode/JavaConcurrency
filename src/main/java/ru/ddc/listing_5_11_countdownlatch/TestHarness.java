package ru.ddc.listing_5_11_countdownlatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/*
В листинге 5.11 TestHarness создает несколько потоков, выполняющих
одну задачу конкурентно, и использует две защелки: начальную
(инициализируется числом 1) и конечную (инициализируется числом рабочих
потоков). Потоки ожидают на начальной защелке готовности каждого из
них к запуску. В конце выполнения задачи каждый поток проведет отсчет
в обратном направлении на конечной защелке, чтобы главный поток
дождался завершения всех потоков и вычислил затраченное время.
Защелки в TestHarness нужны именно для того, чтобы измерить время,
необходимое для выполнения задачи n раз конкурентно.
*/
public class TestHarness {
    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        final List<String> abc = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        System.out.println("Thread " + Thread.currentThread().getId() + " await");

                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException ignored) {

                    }
                }
            };
            t.start();
        }

        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                int n = new Random().nextInt(6) + 1;
                System.out.println(Thread.currentThread().getId() + " - n=" + n);
                long fact = 1;
                for (int i = 2; i <= n; i++) {
                    fact = fact * i;
                }
                System.out.println(Thread.currentThread().getId() + " - fact=" + fact);
            }
        };
        long timeTasks = new TestHarness().timeTasks(10, task);
        System.out.println(timeTasks);
    }
}
