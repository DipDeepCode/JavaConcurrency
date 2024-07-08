package ru.ddc.c_3_SharingObjects.s_3_3_ThreadConfinement._3_ThreadLocal.l_3_10_UsingThreadLocalToEnsureThreadConfinement;

public class DriverManger {
    public static Connection getConnection(String dbUrl) {
        return new Connection();
    }
}
