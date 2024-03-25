package ru.ddc.listing_5_15_barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
Класс CellularAutomata в листинге 5.15 демонстрирует использование
барьера в клеточных автоматах, таких как игра «Жизнь» Конвея
(Gardner, 1970). Во время параллелизации симуляции нецелесообразно
назначать отдельный поток каждому элементу (ячейке в игре «Жизнь»)
и принято разбивать задачу на несколько подчастей, каждую из которых
выполнит отдельный поток, а затем результаты объединятся. Cellular-
Automata разделяет доску на Ncpu частей (где Ncpu — это число имеющихся
процессоров) и назначает потокам подчасти. На каждом шаге рабочие
потоки вычисляют новые значения для всех ячеек в их части доски. Когда
потоки достигают барьера, барьерное действие передает новые значения
в модель данных. После этого потоки освобождаются для вычисления
следующего шага, который включает консультации с методом isDone по
определению необходимости в дальнейших итерациях.
*/

public class CellularAutomata {
    private final Board mainBoard;
    private final CyclicBarrier barrier;
    private final Worker[] workers;

    public CellularAutomata(Board board) {
        this.mainBoard = board;
        int count = Runtime.getRuntime().availableProcessors();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                mainBoard.commitNewValues();
            }
        };
        this.barrier = new CyclicBarrier(count, task);
        this.workers = new Worker[count];
        for (int i = 0; i < count; i++) {
            workers[i] = new Worker(mainBoard.getSubBoard(count, i));
        }
    }

    private class Worker implements Runnable {
        private final Board board;

        public Worker(Board board) {
            this.board = board;
        }

        public void run() {
            while (!board.hasConverted()) {
                for (int x = 0; x < board.getMaxX(); x++) {
                    for (int y = 0; y < board.getMaxY(); y++) {
                        board.setNewValue(x, y, computeValue(x, y));
                    }
                }
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException ex) {
                    return;
                }
            }
        }

        private Object computeValue(int x, int y) {
            return null;
        }
    }

    public void start() {
        for (Worker worker : workers) {
            new Thread(worker).start();
        }
        mainBoard.waitForConvergence();
    }
}
