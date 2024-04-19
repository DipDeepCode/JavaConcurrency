package ru.ddc.listing_7_3_5_brokenprimeproducer;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/*
Класс BrokenPrimeProducer можно упростить, используя прерывание
для запроса отмены вместо флажка, как показано в листинге 7.5. В каждой
итерации цикла есть две точки обнаружения прерывания: в вызове
метода put и в заголовке цикла. Явная проверка здесь не является строго
необходимой из-за вызова put, но она делает класс PrimeProducer более
отзывчивым к прерыванию, так как он проверяет прерывание перед началом
длительной задачи поиска простого числа, а не после нее.
*/
public class PrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;

    public PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!Thread.currentThread().isInterrupted()) {
                queue.put(p = p.nextProbablePrime());
            }
        } catch (InterruptedException ignored) {

        }
    }

    public void cancel() {
        interrupt();
    }

    public void getT() throws InterruptedException {
        BigInteger a = queue.take();
    }

    public void putT() throws InterruptedException {
        queue.put(BigInteger.ONE);
    }
}
