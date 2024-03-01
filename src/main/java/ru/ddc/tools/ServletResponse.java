package ru.ddc.tools;

import java.math.BigInteger;

public class ServletResponse {

    private BigInteger[] factors;
    private String message;

    public BigInteger[] getFactors() {
        return factors;
    }

    public void setFactors(BigInteger[] factors) {
        this.factors = factors;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
