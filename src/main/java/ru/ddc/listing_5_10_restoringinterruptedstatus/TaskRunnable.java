package ru.ddc.listing_5_10_restoringinterruptedstatus;

import java.util.concurrent.BlockingQueue;

/*
Вызывая метод, который выдает исключение InterruptedException, ваш
метод также становится блокирующим и должен уметь откликаться на
прерывание. В случае библиотечного кода существует два варианта:
- Распространить исключение InterruptedException. Эта политика
предусматривает неотлавливание исключения либо его отлавливание
и повторную выдачу после выполнения краткой очистки.
- Восстановить прерывание. Когда распространить исключение
невозможно (например, код является частью Runnable), необходимо
перехватить исключение и восстановить статус прерванности, вызвав
метод interrupt в текущем потоке, чтобы код выше в стеке вызовов
видел выполненное прерывание (см. листинг 5.10)
*/
public class TaskRunnable implements Runnable {
    BlockingQueue<Task> queue;

    public void run() {
        try {
            processTask(queue.take());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void processTask(Task take) {

    }
}
