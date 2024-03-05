package ru.ddc.listing_4_9_delegatingtosomefilds;

import java.security.Key;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/*
Класс VisualComponent в листинге 4.9 представляет собой графический
компонент, который поддерживает список зарегистрированных слушателей
в виде двух независимых множеств (слушателей мыши и слушателей
клавиш), чтобы при наступлении события активировать соответствующих
слушателей. VisualComponent может делегировать свои обязательства по
потокобезопасности двум потокобезопасным спискам.
*/
public class VisualComponents {
    private final List<KeyListener> keyListeners = new CopyOnWriteArrayList<>();
    private final List<MouseListener> mouseListeners = new CopyOnWriteArrayList<>();

    public void addKeyListener(KeyListener listener) {
        keyListeners.add(listener);
    }

    public void addMouseListener(MouseListener listener) {
        mouseListeners.add(listener);
    }

    public void removeKeyListener(KeyListener listener) {
        keyListeners.remove(listener);
    }

    public void removeMouseListener(MouseListener listener) {
        mouseListeners.remove(listener);
    }
}
