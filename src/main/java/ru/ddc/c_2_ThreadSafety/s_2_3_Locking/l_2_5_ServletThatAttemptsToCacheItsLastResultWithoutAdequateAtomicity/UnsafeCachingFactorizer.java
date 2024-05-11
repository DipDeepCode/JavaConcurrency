package ru.ddc.c_2_ThreadSafety.s_2_3_Locking.l_2_5_ServletThatAttemptsToCacheItsLastResultWithoutAdequateAtomicity;

import ru.ddc.annotations.NotThreadSafe;
import ru.ddc.tools.Servlet;
import ru.ddc.tools.ServletRequest;
import ru.ddc.tools.ServletResponse;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/*
К сожалению, такой подход не работает. Несмотря на то что атомарные
ссылки потокобезопасны, класс UnsafeCachingFactorizer содержит состояния гонки.
Определение потокобезопасности требует соблюдения инвариантов независимо
от временной координации или перемежения операций в многочисленных потоках.
Один инвариант класса UnsafeCachingFactorizer
заключается в том, что кэшированное в поле lastFactors произведение
множителей равно значению, кэшированному в поле lastNumber. Наш
сервлет является правильным, только если этот инвариант всегда соблюдается.
Когда в инварианте участвуют многочисленные переменные, поля
не являются независимыми: значение одного ограничивает допустимые
значения другого. Следовательно, при обновлении одного необходимо
обновлять другие в той же атомарной операции.
*/
@NotThreadSafe
public class UnsafeCachingFactorizer implements Servlet {
    private final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();
    private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<>();
    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        if (i.equals(lastNumber.get())) {
            encodeIntoResponse(resp, lastFactors.get());
        } else {
            BigInteger[] factors = factor(i);
            lastNumber.set(i);
            lastFactors.set(factors);
            encodeIntoResponse(resp, factors);
        }
    }

    private BigInteger extractFromRequest(ServletRequest req) {
        return req.getI();
    }

    private BigInteger[] factor(BigInteger n) {
        BigInteger value = BigInteger.ONE;
        BigInteger[] result = new BigInteger[n.intValue()];
        result[0] = value;
        for (int i = 2; i <= n.intValue(); i++) {
            value = value.multiply(BigInteger.valueOf(i));
            result[i - 1] = value;
        }
        return result;
    }

    private void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
        resp.setFactors(factors);
    }
}
