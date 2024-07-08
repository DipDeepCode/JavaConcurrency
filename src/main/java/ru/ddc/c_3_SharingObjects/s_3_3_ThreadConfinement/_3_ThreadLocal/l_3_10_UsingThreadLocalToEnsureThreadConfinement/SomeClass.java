package ru.ddc.c_3_SharingObjects.s_3_3_ThreadConfinement._3_ThreadLocal.l_3_10_UsingThreadLocalToEnsureThreadConfinement;

/*
Однопоточное приложение может поддерживать глобальное подключение
к базе данных, инициализируемое при запуске, чтобы избежать необходимости
передавать объект Connection в каждый метод. Если соединения
JDBC непотокобезопасны, многопоточное приложение, использующее
глобальное соединение без дополнительной координации, также не является
потокобезопасным. Используя класс ThreadLocal для хранения
соединения JDBC, как в ConnectionHolder в листинге 3.10, вы обеспечите
каждому потоку свое собственное соединение.
*/

public class SomeClass {
    private static final ThreadLocal<Connection> connectionHolder = new ThreadLocal<>() {
        @Override
        protected Connection initialValue() {
            return DriverManger.getConnection("DB_URL");
        }
    };

    public static Connection getConnection() {
        return connectionHolder.get();
    }
}
