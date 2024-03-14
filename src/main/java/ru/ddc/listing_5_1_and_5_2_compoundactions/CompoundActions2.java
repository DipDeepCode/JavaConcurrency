package ru.ddc.listing_5_1_and_5_2_compoundactions;

import java.util.Vector;

/*
Поскольку синхронизированные коллекции поддерживают блокировку
на стороне клиента1, существует возможность создания новых операций,
атомарных по отношению к другим операциям над коллекцией, если
известен замок. Синхронизированные коллекционные классы защищают
каждый метод замком на самом синхронизированном коллекционном
объекте. Как показано в листинге 5.2, приобретая коллекционный замок,
мы можем сделать методы getLast и deleteLast атомарными, обеспечив
неизменность размера Vector на время между вызовами методов size
и get.
*/
public class CompoundActions2 {
    public static Object getLast(Vector<Object> list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            return list.get(lastIndex);
        }
    }

    public static void deleteLast(Vector<Object> list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            list.remove(lastIndex);
        }
    }
}
