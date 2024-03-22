package ru.ddc.listing_5_12_futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
Класс предварительной
загрузки Preloader в листинге 5.12 использует FutureTask для выполнения
дорогостоящих вычислений, результаты которых потребуются позже.
Досрочные вычисления сэкономят время в будущем.
Класс Preloader создает FutureTask, который описывает задачу загрузки
информации из базы данных, и поток, в котором будет выполняться вычисление.
Также он предоставляет метод start для запуска потока, так
как не рекомендуется запускать поток из конструктора или статического
инициализатора. Когда программе позже потребуется ProductInfo, она
сможет вызвать метод get, который возвратит загруженные данные, если
они готовы, либо сначала дождется завершения загрузки.
*/
public class PreLoader {

    private ProductInfo loadProductInfo() throws DataLoadException {
        return new ProductInfo();
    }

    Callable<ProductInfo>  callable = new Callable<ProductInfo>() {
        public ProductInfo call() throws DataLoadException {
            return loadProductInfo();
        }
    };

    private final FutureTask<ProductInfo> future = new FutureTask<>(callable);
    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public ProductInfo get() throws DataLoadException, InterruptedException {
        try {
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof DataLoadException) {
                throw (DataLoadException) cause;
            } else {
                throw LaunderThrowable.launderThrowable(cause);
            }
        }
    }
}
