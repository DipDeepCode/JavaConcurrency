package ru.ddc.listing_6_17_invokeall;

import java.util.concurrent.*;

public class QuoteTask implements Callable<TravelQuote> {
    private final TravelCompany company;
    private final TravelInfo travelInfo;

    public QuoteTask(TravelCompany company, TravelInfo travelInfo) {
        this.company = company;
        this.travelInfo = travelInfo;
    }

    @Override
    public TravelQuote call() throws Exception {
        return company.solicitQuote(travelInfo);
    }

    public TravelQuote getTimeoutQuote(CancellationException e) {
        return null;
    }

    public TravelQuote getFailureQuote(Throwable cause) {
        return null;
    }
}
