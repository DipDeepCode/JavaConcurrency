package ru.ddc.c_3_SharingObjects.s_3_5_SafePublication._1_ImproperPublicationWhenGoodObjectsGoBad.l_3_15_ClassAtRiskOfFailureIfNotProperlyPublished;

/*
Иногда необходимо использовать объекты в потоках совместно. Как
показано в листинге 3.14, простого сохранения объектной ссылки
в публичном поле недостаточно, для того чтобы опубликовать объект
безопасно.
Вы будете удивлены, но из-за проблем видимости держатель Holder может
показаться другому потоку в противоречивом состоянии, даже если его
инварианты были правильно установлены его конструктором! Именно
ненадлежащая публикация заставит другой поток наблюдать частично
сконструированный объект.
*/

import ru.ddc.c_3_SharingObjects.s_3_5_SafePublication._0_.l_3_14_PublishingAnObjectWithoutAdequateSynchronization.Holder;

public class SomeClass {
    public Holder holder;

    public void initialize() {
        holder = new Holder(43);
    }
}
