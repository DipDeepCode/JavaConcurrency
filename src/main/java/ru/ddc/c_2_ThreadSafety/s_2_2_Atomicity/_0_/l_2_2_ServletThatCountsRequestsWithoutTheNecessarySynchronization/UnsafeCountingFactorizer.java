package ru.ddc.c_2_ThreadSafety.s_2_2_Atomicity._0_.l_2_2_ServletThatCountsRequestsWithoutTheNecessarySynchronization;

import ru.ddc.annotations.NotThreadSafe;
import ru.ddc.tools.Servlet;
import ru.ddc.tools.ServletRequest;
import ru.ddc.tools.ServletResponse;

import java.math.BigInteger;

/*
К сожалению, класс UnsafeCountingFactorizer не является потокобезо-
пасным, даже если отлично работает в однопоточной среде. Так же, как
UnsafeSequence, он предрасположен к потерянным обновлениям (lost
updates). Хотя операция приращения ++count имеет компактный син-
таксис, она не является атомарной (atomic), то есть неделимой, а пред-
ставляет собой последовательность из трех операций: доставки текущего
значения, прибавления к нему единицы и записи нового значения обратно.
В операциях «прочитать, изменить, записать» результирующее состояние
является производным от предыдущего
*/
@NotThreadSafe
public class UnsafeCountingFactorizer implements Servlet {
    private long count = 0;

    public long getCount() {
        return count;
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        ++count;
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
