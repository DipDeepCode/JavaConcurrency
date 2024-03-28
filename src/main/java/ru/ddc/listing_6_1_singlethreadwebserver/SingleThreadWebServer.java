package ru.ddc.listing_6_1_singlethreadwebserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
Некоторые политики планирования задач используют потенциал конкурентности
лучше других. Одна из них предлагает выполнять задачи последовательно
в одном потоке. Класс SingleThreadWebServer в листинге 6.1
обрабатывает HTTP-запросы, поступающие на порт 80, последовательно.
Детали обработки запроса сейчас не важны — мы стараемся оценить
конкурентность различных политик планирования.
Простой и правильный класс SingleThreadedWebServer неэффективен
в рабочей среде, поскольку обрабатывает только один запрос за раз. Пока
главный поток попеременно принимает соединения и обрабатывает
ассоциированный запрос, новые соединения должны ожидать завершения
текущего запроса и вызова метода accept. К сожалению, handleRequest
не может возвращаться мгновенно.
*/
public class SingleThreadWebServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket socket = new ServerSocket(80)) {
            while (true) {
                Socket connection = socket.accept();
                handleRequest(connection);
            }
        }
    }

    private static void handleRequest(Socket connection) {

    }
}
