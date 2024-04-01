package ru.ddc.listing_6_2_threadpertaskwebserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
Создание отдельного потока для обслуживания каждого запроса, как
показано в классе ThreadPerTaskWebServer в листинге 6.2, повышает отзывчивость.
При небольшой и умеренной нагрузке подход «поток в расчете на задачу»
уместнее последовательного выполнения. До тех пор, пока скорость поступления
запросов не превышает емкость сервера для обработки запросов, этот
подход обеспечивает высокую отзывчивость и пропускную способность.
*/
public class ThreadPreTaskWebServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket socket = new ServerSocket(80)) {
            while (true) {
                final Socket connection = socket.accept();
                Runnable task = new Runnable() {
                    @Override
                    public void run() {
                        handleRequest(connection);
                    }
                };
                new Thread(task).start();
            }
        }
    }

    private static void handleRequest(Socket connection) {

    }
}
