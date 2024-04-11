package ru.ddc.listing_6_16_timeoutfuture;

import java.util.concurrent.*;

/*
Типичное приложение хронометрируемого метода Future.get в листинге 6.16
генерирует составную веб-страницу с запрошенным содержимым
и рекламой, доставленной из сервера. Оно предоставляет задачу доставки
рекламы исполнителю, вычисляет остальную часть содержимого страницы,
а затем ожидает рекламного сообщения, до тех пор пока не истечет его
бюджет времени. Если время ожидания метода get истечет, то задача
доставки рекламы отменяется, и вместо нее будет использовано рекламное
сообщение, принятое по умолчанию.
*/
public class SomeClass {
    private static final long TIME_BUDGET = 2000000L;
    private static final Ad DEFAULT_AD = new Ad();

    private final ExecutorService exec;

    public SomeClass(ExecutorService executor) {
        this.exec = executor;
    }

    Page renderPageWithAd() throws InterruptedException {
        long endNanos = System.nanoTime() + TIME_BUDGET;

        Callable<Ad> fetchAdTask = new Callable<Ad>() {
            @Override
            public Ad call() throws Exception {
                return null;
            }
        };

        Future<Ad> f = exec.submit(fetchAdTask);
        // Отрисовать страницу в ожидании рекламы
        Page page = renderPageBody();
        Ad ad;
        try {
            // Ожидать только на протяжении оставшегося бюджета времени
            long timeLeft = endNanos - System.nanoTime();
            ad = f.get(timeLeft, TimeUnit.NANOSECONDS);
        } catch (ExecutionException e) {
            ad = DEFAULT_AD;
        } catch (TimeoutException e) {
            ad = DEFAULT_AD;
            f.cancel(true);
        }

        page.setAd(ad);
        return page;
    }

    private Page renderPageBody() {
        return null;
    }
}
