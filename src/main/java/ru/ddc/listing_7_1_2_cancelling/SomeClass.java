package ru.ddc.listing_7_1_2_cancelling;

import java.math.BigInteger;
import java.util.List;

/*
В листинге 7.2 показано, как генератор простых чисел может работать
в течение одной секунды перед остановкой. Конечно, возникает задержка
между временем запроса на отмену и временем следующей проверки
флажка циклом run, но метод cancel, вызываемый из блока finally, все
равно обеспечивает отмену генерирования простых чисел, даже если
вызов метода sleep прерван. Без вызова метода cancel поток поиска
простых чисел работал бы вечно, потребляя ресурсы и не позволяя JVM
выйти.
*/
public class SomeClass {
    List<BigInteger> aSecondPrimes() throws InterruptedException {
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();
        try {
            Thread.sleep(1000);
        } finally {
            generator.cancel();
        }
        return generator.get();
    }
}
