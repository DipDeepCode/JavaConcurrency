package ru.ddc.c_2_ThreadSafety.s_2_1_WhatIsThreadSafety._1_ExampleAStatelessServlet.l_2_1_AStatelessServlet;

import ru.ddc.annotations.ThreadSafe;
import ru.ddc.tools.Servlet;
import ru.ddc.tools.ServletRequest;
import ru.ddc.tools.ServletResponse;

import java.math.BigInteger;

/*
Класс StatelessFactorizer, как и большинство сервлетов, не имеет
внутреннего состояния: не содержит полей и не ссылается на поля из других классов.
Состояние для конкретного вычисления существует только в локальных
переменных, которые хранятся в потоковом стеке и доступны только для
выполняющего потока. Один поток, обращающийся к Stateless Factorizer,
не может повлиять на результат другого потока, делающего то же самое,
поскольку эти потоки не используют состояние совместно
*/
@ThreadSafe
public class StatelessFactorizer implements Servlet {
    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
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
