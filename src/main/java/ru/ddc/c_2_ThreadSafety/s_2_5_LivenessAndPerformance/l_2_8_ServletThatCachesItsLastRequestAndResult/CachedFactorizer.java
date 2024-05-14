package ru.ddc.c_2_ThreadSafety.s_2_5_LivenessAndPerformance.l_2_8_ServletThatCachesItsLastRequestAndResult;

import ru.ddc.annotations.GuardedBy;
import ru.ddc.annotations.ThreadSafe;
import ru.ddc.tools.Servlet;
import ru.ddc.tools.ServletRequest;
import ru.ddc.tools.ServletResponse;

import java.math.BigInteger;

/*
Класс CachedFactorizer в листинге 2.8 реструктурирует сервлет за счет
использования двух синхронизированных блоков, где каждый ограничен
коротким фрагментом кода. Один защищает последовательность
«проверить и затем действовать» и проверяет возможность вернуть
кэшированный результат, а другой защищает обновление кэшированного
числа и кэшированных множителей. В качестве бонуса мы вновь ввели
счетчик посещений и добавили еще один счетчик посещений в кэш,
обновив их в первоначальном синхронизированном блоке. Поскольку
эти счетчики также составляют совместно используемое мутируемое
состояние, мы должны добавить синхронизацию везде, где к ним
осуществляется доступ. Части кода, находящиеся вне синхронизированных
блоков, работают эксклюзивно на локальных (стековых) переменных,
которые не используются совместно во всех потоках и поэтому не
требуют синхронизации.
*/
@ThreadSafe
public class CachedFactorizer implements Servlet {
    @GuardedBy("this") private BigInteger lastNumber;
    @GuardedBy("this") private BigInteger[] lastFactors;
    @GuardedBy("this") private long hits;
    @GuardedBy("this") private long cacheHits;

    public synchronized long getHits() {
        return hits;
    }

    public synchronized double getCachedHitRatio() {
        return (double) cacheHits / (double) hits;
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = null;
        synchronized (this) {
            ++hits;
            if (i.equals(lastNumber)) {
                ++cacheHits;
                factors = lastFactors.clone();
            }
        }
        if (factors == null) {
            factors = factor(i);
            synchronized (this) {
                lastNumber = i;
                lastFactors = factors.clone();
            }
        }
        encodeIntoResponse(resp, factors);
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
