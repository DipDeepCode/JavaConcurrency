package ru.ddc.listing_3_6_publishing2;

/*
Класс UnsafeStates в листинге 3.6 старается опубликовать массив аббревиатур приватно.
*/
public class UnsafeStates {
    private String[] states = new String[]{"AK", "AL"};

    public String[] getStates() {
        return states;
    }
}
