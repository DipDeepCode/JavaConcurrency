package ru.ddc.c_2_ThreadSafety.s_2_3_Locking._1_IntrinsicLocks.l_2_6_ServletThatCachesLastResultButWithUnacceptablyPoorConcurrency;

import ru.ddc.annotations.GuardedBy;
import ru.ddc.annotations.ThreadSafe;
import ru.ddc.tools.Servlet;
import ru.ddc.tools.ServletRequest;
import ru.ddc.tools.ServletResponse;

import java.math.BigInteger;

/*
Механизм синхронизации позволяет легко восстановить потокобезопасность
сервлета разложения на множители. В листинге 2.6 метод service
делается синхронизированным, то есть впускающим один поток за раз.
Класс SynchronizedFactorizer теперь является потокобезопасным, но
многочисленные клиенты больше не могут использовать сервлет одновременно,
что приводит к неприемлемо низкой отзывчивости.
*/
@ThreadSafe
public class SynchronizedFactorizer implements Servlet {
    @GuardedBy("this") private BigInteger lastNumber;
    @GuardedBy("this") private BigInteger[] lastFactors;
    @Override
    public synchronized void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        if (i.equals(lastNumber)) {
            encodeIntoResponse(resp, lastFactors);
        } else {
            BigInteger[] factors = factor(i);
            lastNumber = i;
            lastFactors = factors;
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
