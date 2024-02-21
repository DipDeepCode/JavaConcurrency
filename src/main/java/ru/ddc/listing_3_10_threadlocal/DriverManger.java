package ru.ddc.listing_3_10_threadlocal;

public class DriverManger {
    public static Connection getConnection(String dbUrl) {
        return new Connection();
    }
}
