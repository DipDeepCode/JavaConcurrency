package ru.ddc.c_3_SharingObjects.s_3_2_PublicationAndEscape._0_.l_3_6_AllowingInternalMutableStateToEscape;

/*
Класс UnsafeStates в листинге 3.6 старается опубликовать массив аббревиатур приватно.
*/
public class UnsafeStates {
    private String[] states = new String[]{"AK", "AL"};

    public String[] getStates() {
        return states;
    }
}
