package ru.ddc.listing_2_4_atomic;

import ru.ddc.tools.Servlet;
import ru.ddc.tools.ServletRequest;
import ru.ddc.tools.ServletResponse;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

/*
Пакет java.util.concurrent.atomic содержит атомарные перемен‑
ные (atomic variable) для управления состояниями классов. Заменив тип
счетчика с long на AtomicLong, мы гарантируем, что все действия, которые
обращаются к состоянию счетчика, являются атомарными1. Поскольку
состояние сервлета является состоянием счетчика, а счетчик является
потокобезопасным, наш сервлет становится потокобезопасным.
*/
public class CountingFactorizer implements Servlet {
    private final AtomicLong count = new AtomicLong(0);

    public long getCount() {
        return count.get();
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        count.incrementAndGet();
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
