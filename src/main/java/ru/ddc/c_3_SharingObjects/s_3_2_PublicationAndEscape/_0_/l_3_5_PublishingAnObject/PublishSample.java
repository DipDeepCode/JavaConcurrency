package ru.ddc.c_3_SharingObjects.s_3_2_PublicationAndEscape._0_.l_3_5_PublishingAnObject;

import java.util.HashSet;
import java.util.Set;

/*
Самая простая форма публикации — это ссылка в публичном статическом
поле. Метод initialize создает и публикует экземпляр нового хеш-множества,
сохраняя ссылку на него в knownSecrets.
*/

public class PublishSample {
    public static Set<Secret> knownSecrets;

    public void initialize() {
        knownSecrets = new HashSet<>();
    }
}
