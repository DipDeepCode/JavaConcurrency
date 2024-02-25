package ru.ddc.listing_3_13_volatilecachedservlet;

import ru.ddc.annotations.ThreadSafe;
import ru.ddc.listing_3_12_immutabecacheholder.OneValueCache;
import ru.ddc.tools.Servlet;
import ru.ddc.tools.ServletRequest;
import ru.ddc.tools.ServletResponse;

import java.math.BigInteger;

/*
Класс VolatileCachedFactorizer в листинге 3.13 использует кэш OneValueCache
для хранения кэшированного числа и множителей. Когда поток задаст
для волатильного поля cache ссылку на новый объект OneValueCache,
новые кэшированные данные станут видимыми для других потоков.
Операции, связанные с кэшем, не могут взаимодействовать, поскольку объект
OneValueCache является немутируемым, а поле cache доступно только
один раз в каждой из релевантных ветвей кода. Комбинация немутируемого
держателя родственных через инвариант переменных состояния и мутируемой
ссылки, обеспечивающей его своевременную видимость, позволяет
классу VolatileCachedFactorizer быть потокобезопасным без блокировки.
*/
@ThreadSafe
public class VolatileCachedFactorizer implements Servlet {
    private volatile OneValueCache cache = new OneValueCache(null, null);
    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = cache.getFactors(i);
        if (factors == null) {
            factors = factor(i);
            cache = new OneValueCache(i, factors);
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
