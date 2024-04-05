package ru.ddc.listing_6_9_timer;

import java.util.Timer;
import java.util.TimerTask;

import static java.util.concurrent.TimeUnit.SECONDS;

/*
Класс OutOfTime в листинге 6.9 иллюстрирует, как Timer путается сам
и путает следующий вызывающий элемент кода, который пытается предоставить
задачу TimerTask. Программа терминируется через одну секунду
с исключением IllegalStateException и текстом сообщения «Timer отменен».
Исполнитель ScheduledThreadPoolExecutor надлежащим образом
справляется с неблагополучными задачами, и в Java 5.0 Timer почти не
используется.
*/
public class OutOfTime {
    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(), 1);
        SECONDS.sleep(1);
        timer.schedule(new ThrowTask(), 1);
        SECONDS.sleep(5);
    }

    static class ThrowTask extends TimerTask {
        @Override
        public void run() {
            throw new RuntimeException();
        }
    }
}
