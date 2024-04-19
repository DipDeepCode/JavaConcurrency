package ru.ddc.listing_7_3_5_brokenprimeproducer;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
В классе BrokenPrimeProducer в листинге 7.3 производящий поток
генерирует простые числа и помещает их в блокирующую очередь. Если
производитель опережает потребителя, то очередь заполнится и метод put
станет блокировать продвижение. Чтобы отменить задачу производителя,
потребитель может вызвать метод cancel, который установит флажок
cancelled. Но производитель ни разу не проверит этот флажок, потому
что он никогда не выберется из блокирующего метода put.
*/
public class SomeClass {
    void consumePrimes() throws InterruptedException {
        BlockingQueue<BigInteger> primes = new ArrayBlockingQueue<>(10);
        BrokenPrimeProducer producer = new BrokenPrimeProducer(primes);
        producer.start();
        try {
            while (needMorePrimes()) {
                consume(primes.take());
            }
        } finally {
            producer.cancel();
        }
    }

    private void consume(BigInteger take) {

    }

    private boolean needMorePrimes() {
        return false;
    }
}
