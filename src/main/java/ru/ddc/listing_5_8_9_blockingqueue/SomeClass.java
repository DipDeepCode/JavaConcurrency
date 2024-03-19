package ru.ddc.listing_5_8_9_blockingqueue;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/*
Листинг 5.9 запускает несколько обходчиков и индексаторов в отдельных
потоках. Потребляющие потоки никогда не завершаются, что не дает
программе терминироваться (мы рассмотрим решения этой проблемы
в главе 7). В этом примере используются явно управляемые потоки, но
паттерны «производитель-потребитель» могут быть выражены и с
помощью структуры выполнения задач Executor.
*/
public class SomeClass {
    private static final int BOUND = 100;
    private static final int N_CONSUMERS = 10;
    public static void startIndexing(File[] roots) throws InterruptedException {
        BlockingQueue<File> queue = new LinkedBlockingQueue<>(BOUND);
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        };

        for (File root : roots) {
            new Thread(new FileCrawler(queue, filter, root)).start();
        }

        for (int i = 0; i < N_CONSUMERS; i++) {
            new Thread(new Indexer(queue)).start();
        }
    }
}
