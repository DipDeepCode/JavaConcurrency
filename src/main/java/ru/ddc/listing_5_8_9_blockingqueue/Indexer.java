package ru.ddc.listing_5_8_9_blockingqueue;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/*
В листинге 5.8 разложим на производителей и потребителей работу агента,
который сканирует локальные диски на наличие документов и индексирует
их для последующего поиска (подобно Google Desktop или службе
индексирования Windows). Класс DiskCrawler показывает задачу
производителя по поиску нужных файлов и постановке их имен в рабочую
очередь. Класс Indexer показывает задачу потребителя по принятию имен
файлов из очереди и их индексации.
*/
public class Indexer implements Runnable {
    private final BlockingQueue<File> queue;

    public Indexer(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                indexFile(queue.take());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void indexFile(File take) {

    }

}
