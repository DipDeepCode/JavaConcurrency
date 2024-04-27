package ru.ddc.listing_7_7_irrevocabletask;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
Действия, которые не поддерживают отмену, должны вызывать прерываемые
блокирующие методы в цикле, повторяя попытки вызова при
обнаружении прерывания. Им следует сохранять статус прерванности
локально и восстанавливать его непосредственно перед возвратом, как
показано в листинге 7.7. Преждевременное установление статуса
прерванности может привести к бесконечному циклу.

*/
public class SomeClass {

    public Task getNextTask(BlockingQueue<Task> queue) {
        boolean interrupted = false;
        try {
            while (true) {
                try {
                    return queue.take();
                } catch (InterruptedException e) {
                    interrupted = true;
                    System.out.println(Thread.currentThread().isInterrupted());
                    break;
                }
            }
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
        return null;
    }

    public void demo() throws InterruptedException {
        ArrayBlockingQueue<Task> queue = new ArrayBlockingQueue<>(1);
        queue.put(new Task("new task"));

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(getNextTask(queue));
            }
        };

        (new Thread(r)).start();

        Thread t = new Thread(r);
        t.start();
        Thread.sleep(500);
        t.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        (new SomeClass()).demo();
    }
}
