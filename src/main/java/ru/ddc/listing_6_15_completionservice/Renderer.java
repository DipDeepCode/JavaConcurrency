package ru.ddc.listing_6_15_completionservice;

import ru.ddc.listing_5_12_futuretask.LaunderThrowable;

import java.util.List;
import java.util.concurrent.*;

/*
Служба CompletionService позволяет повысить производительность
страничного отрисовщика двумя путями: более коротким суммарным временем
работы и улучшенной отзывчивостью. Мы можем создать отдельную задачу
для скачивания каждого изображения и его выполнения в пуле потоков,
превратив последовательное скачивание в параллельное: это сократит время
скачивания всех изображений. А путем доставки результатов из службы
CompletionService и отрисовки каждого изображения, как только оно будет
в наличии, мы дадим пользователю динамичный и отзывчивый пользовательский
интерфейс. Эта реализация показана в Renderer в листинге 6.15.
*/
public class Renderer {
    private final ExecutorService executor;

    public Renderer(ExecutorService executor) {
        this.executor = executor;
    }

    void renderPage(CharSequence source) {
        final List<ImageInfo> info = scanForImageInfo(source);
        CompletionService<ImageData> completionService = new ExecutorCompletionService<>(executor);
        for (final ImageInfo imageInfo : info) {
            Callable<ImageData> task = new Callable<>() {
                @Override
                public ImageData call() {
                    return imageInfo.downloadImage();
                }
            };
            completionService.submit(task);
        }

        renderText(source);

        try {
            for (int t = 0, n = info.size(); t < n; t++) {
                Future<ImageData> f = completionService.take();
                ImageData imageData = f.get();
                renderImage(imageData);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw LaunderThrowable.launderThrowable(e.getCause());
        }
    }

    private void renderImage(ImageData imageData) {

    }

    private void renderText(CharSequence source) {

    }

    private List<ImageInfo> scanForImageInfo(CharSequence source) {
        return null;
    }
}
