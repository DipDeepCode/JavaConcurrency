package ru.ddc.listing_7_7_irrevocabletask;

public class Task {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
