package ru.ddc.listing_5_20_cachedservlet;

import ru.ddc.annotations.ThreadSafe;
import ru.ddc.tools.Servlet;
import ru.ddc.tools.ServletRequest;
import ru.ddc.tools.ServletResponse;

import java.math.BigInteger;

/*
Теперь, когда конкурентная реализация кэша завершена, мы можем добавить
реальное кэширование в сервлет разложения на множители из
главы 2, как и обещали. Класс Factorizer в листинге 5.20 эффективно
использует класс Memoizer для кэширования ранее вычисленных значений
*/
@ThreadSafe
public class Factorizer implements Servlet {
    private final Computable<BigInteger, BigInteger[]> c = new Computable<>() {
        @Override
        public BigInteger[] compute(BigInteger arg) throws InterruptedException {
            return factor(arg);
        }
    };
    private final Computable<BigInteger, BigInteger[]> cache = new Memoizer<>(c);

    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        try {
            BigInteger i = extractFromRequest(req);
            BigInteger[] factors = cache.compute(i);
            encodeIntoResponse(resp, factors);
        } catch (InterruptedException e) {
            encodeError(resp, "факторизация прервана");
        }
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

    private BigInteger extractFromRequest(ServletRequest req) {
        return req.getI();
    }

    private void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
        resp.setFactors(factors);
    }

    private void encodeError(ServletResponse resp, String message) {
        resp.setMessage(message);
    }
}
