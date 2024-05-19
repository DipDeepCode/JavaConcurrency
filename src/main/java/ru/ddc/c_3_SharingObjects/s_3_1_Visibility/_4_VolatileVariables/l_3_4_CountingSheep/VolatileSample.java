package ru.ddc.c_3_SharingObjects.s_3_1_Visibility._4_VolatileVariables.l_3_4_CountingSheep;

/*
Листинг 3.4 иллюстрирует типичное использование волатильных переменных.
Они должны проверить статусный флажок и определить время
выхода из цикла. Поток пытается «уснуть» с помощью метода подсчета
овец. Флажок asleep должен быть волатильным, иначе поток может не
заметить, когда флажок был установлен другим потоком2. Обеспечить
видимость изменений во флажке asleep может и блокировка, но она
сделает код громоздким
*/
public class VolatileSample {
    volatile boolean asleep;

    public void someMethod() {
        while (!asleep) {
            countsSomeSheep();
        }
    }

    private void countsSomeSheep() {

    }
}
