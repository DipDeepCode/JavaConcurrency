package ru.ddc.listing_6_10_singlethreadrenderer;

import java.util.ArrayList;
import java.util.List;

/*
Последовательная обработка HTML-документа подразумевает при
появлении текстовой разметки ее отрисовку в буфере изображений, а при
появлении ссылок на изображение — их доставку по сети и отрисовку
в буфере изображений. Такая обработка требует прикосновения к каждому
входному элементу всего один раз, но может занять много времени.
Другой (тоже последовательный) подход включает в себя отрисовку
сначала текстовых элементов, а затем — изображений в соответствующем
заполнителе. Этот подход показан в классе SingleThreadRenderer
в листинге 6.10.
Скачивание изображения в основном включает в себя ожидание завершения
ввода-вывода. Поэтому последовательный подход может недоиспользовать
процессор, а также заставит пользователя долго ожидать
завершения отрисовки. Мы можем добиться лучшей задействованности
ресурсов и отзывчивости приложения, разбив задачу на независимые
подзадачи, которые могут выполняться конкурентно.
*/
public class SingleThreadRenderer {
    void renderPage(CharSequence source) {
        renderText(source);
        List<ImageData> imageData = new ArrayList<>();
        for (ImageInfo imageInfo : scanForImageInfo(source)) {
            imageData.add(imageInfo.downloadImage());
        }
        for (ImageData data : imageData) {
            renderImage(data);
        }
    }

    private void renderImage(ImageData data) {

    }

    private List<ImageInfo> scanForImageInfo(CharSequence source) {
        return new ArrayList<>();
    }

    private void renderText(CharSequence source) {

    }
}
