package com.widera.algorithmplayground.heapsort;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PriorityQueueTest {

    @Test(expected = IllegalArgumentException.class)
    public void testAddingElementToZeroQueueThrowOverflow() {
        PriorityQueue<Integer> queue = new PriorityQueue<>(0, Comparator.<Integer>naturalOrder());
        queue.add(42);
    }

    @Test
    public void testAddingThreeElements() {
        PriorityQueue<Integer> queue = new PriorityQueue<>(3, Comparator.<Integer>naturalOrder());
        queue.add(42);
        queue.add(10);
        queue.add(55);
        System.out.println(queue);
        assertEquals(Integer.valueOf(10), queue.get());
        assertEquals(Integer.valueOf(42), queue.get());
        assertEquals(Integer.valueOf(55), queue.get());
    }

    @Test
    public void testAdding100RandomElements() {
        PriorityQueue<Integer> queue = new PriorityQueue<>(100, Comparator.<Integer>naturalOrder());
        for (int i = 0; i < 100; i++) {
            queue.add((int) (Math.random() * 100d));
        }
        System.out.println(queue);
        int last = 0;
        for (int i = 0; i < 100; i++) {
            Integer next = queue.get();
            assertTrue(last <= next);
            last = next;
        }
    }

}