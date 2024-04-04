package ru.ddc.listing_6_8_lifecyclewebserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/*
Веб-сервер LifecycleWebServer в листинге 6.8 расширяет поддержку
жизненного цикла веб-сервера. Его можно выключить двумя способами:
программным путем, вызвав метод stop, и через запрос клиента, отправив
веб-серверу специально отформатированный HTTP-запрос.
*/
public class LifecycleWebServer {
    private static final ExecutorService exec = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws IOException {
        try (ServerSocket socket = new ServerSocket(80)) {
            while (!exec.isShutdown()) {
                try {
                    final Socket connection = socket.accept();
                    Runnable task = new Runnable() {
                        @Override
                        public void run() {
                            handleRequest(connection);
                        }
                    };
                    exec.execute(task);
                } catch (RejectedExecutionException e) {
                    if (!exec.isShutdown()) {
                        log("предоставленная задача отклонена", e);
                    }
                }
            }
        }
    }

    public static void stop() {
        exec.shutdown();
    }

    private static void log(String message, Exception e) {

    }

    private static void handleRequest(Socket connection) {
        Request req = readRequest(connection);
        if (isShutdownRequest(req)) {
            stop();
        } else {
            dispatchRequest(req);
        }
    }

    private static void dispatchRequest(Request req) {

    }

    private static boolean isShutdownRequest(Request req) {
        return false;
    }

    private static Request readRequest(Socket connection) {
        return null;
    }
}
