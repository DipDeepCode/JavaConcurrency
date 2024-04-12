package ru.ddc.listing_6_17_invokeall;

import java.util.*;
import java.util.concurrent.*;

/*
В листинге 6.17 используется хронометрированная версия метода
invokeAll для предоставления многочисленных задач в службу
ExecutorService и получения результатов. Метод принимает коллекцию
задач и возвращает коллекцию объектов Future. Две коллекции имеют
идентичные структуры; invokeAll добавляет объекты Future в возвращаемую
коллекцию в порядке, установленном итератором коллекции задач,
позволяя вызывающему элементу кода связать объект Future с объектом
Callable, который он представляет. Хронометрированная версия метода
invokeAll возвратится после завершения всех задач, прерывания вызывающего
потока либо истечения тайм-аута. Все задачи, которые не были завершены
по истечении тайм-аута, отменяются. По возвращении из метода
invokeAll каждая задача будет либо завершена нормально, либо отменена.
Клиентский код сможет вызывать метод get или метод isCancelled для
того, чтобы выяснять, что произошло с задачей
*/
public class SomeClass {
    private final ExecutorService exec;

    public SomeClass(ExecutorService exec) {
        this.exec = exec;
    }

    public List<TravelQuote> getRankedTravelQuotes(
            TravelInfo travelInfo, Set<TravelCompany> companies,
            Comparator<TravelQuote> ranking, long time, TimeUnit unit) throws InterruptedException{

        List<QuoteTask> tasks = new ArrayList<>();
        for (TravelCompany company : companies) {
            tasks.add(new QuoteTask(company, travelInfo));
        }

        List<Future<TravelQuote>> futures = exec.invokeAll(tasks, time, unit);

        List<TravelQuote> quotes = new ArrayList<>(tasks.size());
        Iterator<QuoteTask> taskIter = tasks.iterator();
        for (Future<TravelQuote> f : futures) {
            QuoteTask task = taskIter.next();
            try {
                quotes.add(f.get());
            } catch (ExecutionException e) {
                quotes.add(task.getFailureQuote(e.getCause()));
            } catch (CancellationException e) {
                quotes.add(task.getTimeoutQuote(e));
            }
        }

        quotes.sort(ranking);
        return quotes;
    }
}
