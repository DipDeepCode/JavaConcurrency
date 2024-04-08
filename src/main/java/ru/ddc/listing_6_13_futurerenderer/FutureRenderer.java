package ru.ddc.listing_6_13_futurerenderer;

import ru.ddc.listing_5_12_futuretask.LaunderThrowable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/*
Объекты Callable и Future способны помочь нам выразить взаимодействие
между подзадачами. В классе FutureRenderer в листинге 6.13 мы
создадим объект Callable для скачивания всех изображений и предоставим
его в службу ExecutorService, которая возвратит объект Future
описывающий выполнение задачи. Когда главная задача дойдет до точки,
где ей потребуется заполнить пустые области изображениями, она вызовет
метод Future.get. Даже если при изолированном скачивании изображений
мы не сможем получить результаты по первому требованию, все равно
получим преимущество в производительности
*/
public class FutureRenderer {
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    void renderPage(CharSequence source) {
        final List<ImageInfo> imageInfos = scanForImageInfo(source);
        Callable<List<ImageData>> task = new Callable<>() {
            @Override
            public List<ImageData> call() {
                List<ImageData> result = new ArrayList<>();
                for (ImageInfo imageInfo : imageInfos) {
                    result.add(imageInfo.downloadImage());
                }
                return result;
            }
        };
        Future<List<ImageData>> future = executor.submit(task);
        renderText(source);
        try {
            List<ImageData> imageData = future.get();
            for (ImageData data : imageData) {
                renderImage(data);
            }
        } catch (ExecutionException e) {
            throw LaunderThrowable.launderThrowable(e.getCause());
        } catch (InterruptedException e) {
            // Переподтвердить статус прерванности потока
            Thread.currentThread().interrupt();
            // Нам не нужен результат, поэтому отменить задачу
            future.cancel(true);
        }
    }

    private void renderImage(ImageData data) {

    }

    private void renderText(CharSequence source) {

    }

    private List<ImageInfo> scanForImageInfo(CharSequence source) {
        return null;
    }
}
