package ru.ddc.listing_7_6_spreadingexception;

import java.util.concurrent.BlockingQueue;

/*
Распространение исключения InterruptedException сравнимо по простоте
с его добавлением в спецификатор throws, как показано в листинге 7.6.
*/
public class SomeClass {
    BlockingQueue<Task> queue;

    public Task getNextTask() throws InterruptedException {
        return queue.take();
    }
}
