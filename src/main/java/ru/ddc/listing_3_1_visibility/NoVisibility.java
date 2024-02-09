package ru.ddc.listing_3_1_visibility;

/*
Класс NoVisibility в листинге 3.1 иллюстрирует, как потоки используют
данные совместно, без синхронизации. Два потока — главный и читающий —
обращаются к совместным переменным ready и number. Главный поток
запускает читающий поток, а затем устанавливает number значение 42,
и ready — значение true. Читающий поток видит, что ready равна true,
и распечатывает number. Очевидно, что NoVisibility должен напечатать 42,
но этого может не произойти!
*/

public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}
