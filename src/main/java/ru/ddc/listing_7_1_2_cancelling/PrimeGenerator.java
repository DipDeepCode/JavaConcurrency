package ru.ddc.listing_7_1_2_cancelling;

import ru.ddc.annotations.GuardedBy;
import ru.ddc.annotations.ThreadSafe;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/*
В Java нет других способов безопасно остановить задачу, кроме
использования кооперативных механизмов и их непротиворечивых протоколов.
Одним из таких кооперативных механизмов является установка флажка
«запрошена отмена», который задача периодически проверяет. Обнаружив,
что флажок установлен, задача терминируется досрочно. Класс Prime -
Generator в листинге 7.1, в котором простые числа выводятся до тех пор,
пока действие не будет отменено, иллюстрирует это техническое решение.
Метод cancel устанавливает флажок cancelled, и главный цикл опрашивает
этот флажок перед поиском следующего простого числа. (Флажок
cancelled должен быть волатильным.)
*/
@ThreadSafe
public class PrimeGenerator implements Runnable {
    @GuardedBy("this")
    private final List<BigInteger> primes = new ArrayList<>();
    private volatile boolean cancelled;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled) {
            p = p.nextProbablePrime();
            synchronized (this) {
                primes.add(p);
            }
        }
    }

    public void cancel() {
        cancelled = true;
    }

    public synchronized List<BigInteger> get() {
        return new ArrayList<>(primes);
    }
}
