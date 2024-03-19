package ru.ddc.listing_5_8_9_blockingqueue;

import java.io.File;
import java.io.FileFilter;
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
public class FileCrawler implements Runnable {
    private final BlockingQueue<File> fileQueue;
    private final FileFilter fileFilter;
    private final File root;

    public FileCrawler(BlockingQueue<File> fileQueue, FileFilter fileFilter, File root) {
        this.fileQueue = fileQueue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    @Override
    public void run() {
        try {
            crawl(root);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void crawl(File root) throws InterruptedException {
        File[] entries = root.listFiles(fileFilter);
        if (entries != null) {
            for (File entry : entries) {
                if (entry.isDirectory()) {
                    crawl(entry);
                } else if (!alreadyIndexed(entry)) {
                    fileQueue.put(entry);
                }
            }
        }
    }

    private boolean alreadyIndexed(File entry) {
        return false;
    }
}
