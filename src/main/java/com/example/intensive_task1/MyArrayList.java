package com.example.intensive_task1;

import java.util.Comparator;

/**
 * MyArrayList - это простая реализация динамического массива с базовыми методами для управления элементами.
 * Данная реализация не потокобезопасна и должна использоваться в однопоточной среде.
 *
 * @param <T> тип элементов в данном списке
 */
public class MyArrayList<T> {
    private T[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Конструктор, создающий пустой список с начальной емкостью.
     */
    public MyArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element элемент, который нужно добавить
     */
    public void add(T element) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = element;
    }

    /**
     * Добавляет элемент по указанному индексу.
     *
     * @param index индекс, по которому нужно вставить элемент
     * @param element элемент, который нужно вставить
     * @throws IndexOutOfBoundsException если индекс выходит за границы списка
     */
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Некорректный индекс: " + index);
        }
        if (size == elements.length) {
            resize();
        }
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index индекс элемента, который нужно получить
     * @return элемент по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за границы списка
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Некорректный индекс: " + index);
        }
        return elements[index];
    }

    /**
     * Удаляет элемент по указанному индексу и возвращает его.
     *
     * @param index индекс элемента, который нужно удалить
     * @return удаленный элемент
     * @throws IndexOutOfBoundsException если индекс выходит за границы списка
     */
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Некорректный индекс: " + index);
        }
        T removedElement = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
        return removedElement;
    }

    /**
     * Очищает список, удаляя все элементы.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * Сортирует элементы списка с использованием переданного компаратора.
     *
     * @param comparator компаратор, используемый для сортировки элементов
     */
    public void sort(Comparator<? super T> comparator) {
        quickSort(0, size - 1, comparator);
    }

    /**
     * Публичный метод для сортировки всего списка с использованием компаратора.
     *
     * @param comparator компаратор для сравнения элементов
     */
    public void quickSort(Comparator<? super T> comparator) {
        quickSort(0, size - 1, comparator);
    }

    /**
     * Приватный метод, реализующий быструю сортировку элементов списка с использованием компаратора.
     *
     * @param low начальный индекс
     * @param high конечный индекс
     * @param comparator компаратор для сравнения элементов
     */
    private void quickSort(int low, int high, Comparator<? super T> comparator) {
        if (low < high) {
            int pivotIndex = partition(low, high, comparator);
            quickSort(low, pivotIndex - 1, comparator);
            quickSort(pivotIndex + 1, high, comparator);
        }
    }

    /**
     * Метод для разделения массива вокруг опорного элемента.
     *
     * @param low начальный индекс
     * @param high конечный индекс
     * @param comparator компаратор для сравнения элементов
     * @return индекс опорного элемента
     */
    private int partition(int low, int high, Comparator<? super T> comparator) {
        T pivot = elements[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(elements[j], pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    /**
     * Метод для обмена двух элементов массива.
     *
     * @param i индекс первого элемента
     * @param j индекс второго элемента
     */
    private void swap(int i, int j) {
        T temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    /**
     * Метод для увеличения емкости массива в два раза при необходимости.
     */
    private void resize() {
        int newCapacity = elements.length * 2;
        T[] newArray = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = elements[i];
        }
        elements = newArray;
    }

    /**
     * Возвращает текущий размер списка.
     *
     * @return количество элементов в списке
     */
    public int size() {
        return size;
    }
}
