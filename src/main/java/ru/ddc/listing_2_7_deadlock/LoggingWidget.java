package ru.ddc.listing_2_7_deadlock;

/*
Повторная входимость способствует инкапсуляции замкового поведения
и упрощает разработку объектно-ориентированного конкурентного кода.
Без повторно входимых замков код в листинге 2.7 был бы заперт взаимной
блокировкой (deadlock), а вызов super.doSomething никогда не смог бы
приобрести замок, считающийся занятым
*/
public class LoggingWidget extends Widget {

    @Override
    public synchronized void doSomething() {
        System.out.println(this + ": calling doSomething");
        super.doSomething();
    }

    public static void main(String[] args) {
        LoggingWidget loggingWidget = new LoggingWidget();
        loggingWidget.doSomething();
    }
}
