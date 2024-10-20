package com.example.intensive_task1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Comparator;

public class MyArrayListTest {

    private MyArrayList<Integer> list;

    /**
     * Инициализация перед каждым тестом: создается новый экземпляр списка.
     */
    @BeforeEach
    public void setUp() {
        list = new MyArrayList<>();
    }

    @Test
    public void testAddElement() {
        list.add(1);
        list.add(2);
        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    public void testAddElementAtIndex() {
        list.add(1);
        list.add(3);
        list.add(1, 2); // Вставка 2 на индекс 1
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void testGetElement() {
        list.add(5);
        list.add(10);
        assertEquals(5, list.get(0));
        assertEquals(10, list.get(1));
    }

    @Test
    public void testRemoveElement() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(2, list.remove(1));
        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
    }

    @Test
    public void testClear() {
        list.add(1);
        list.add(2);
        list.clear();
        assertEquals(0, list.size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    public void testSort() {
        list.add(3);
        list.add(1);
        list.add(2);
        list.sort(Comparator.naturalOrder());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void testQuickSort() {
        list.add(5);
        list.add(2);
        list.add(8);
        list.add(1);
        list.quickSort(Comparator.naturalOrder());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(5, list.get(2));
        assertEquals(8, list.get(3));
    }

    @Test
    public void testEnsureCapacity() {
        for (int i = 0; i < 15; i++) {
            list.add(i);
        }
        assertEquals(15, list.size());
        assertEquals(14, list.get(14));
    }

    @Test
    public void testGetElementOutOfBounds() {
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    @Test
    public void testRemoveElementOutOfBounds() {
        list.add(1);
        list.add(2);
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(2));
    }

    @Test
    public void testAddElementAtNegativeIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 1));
    }

    @Test
    public void testAddElementAtIndexGreaterThanSize() {
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(2, 2));
    }
}
