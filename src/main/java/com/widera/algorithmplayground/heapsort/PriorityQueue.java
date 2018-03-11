package com.widera.algorithmplayground.heapsort;

import java.util.Arrays;
import java.util.Comparator;

public class PriorityQueue<E extends Comparable> {

    private final int sizeOfQueue;
    private final Object[] array;
    private Comparator<? super E> comparator;
    private int numberOfElements;

    public PriorityQueue(final int sizeOfQueue, final Comparator<? super E> comparator) {
        if (sizeOfQueue < 0) {
            throw new IllegalArgumentException("size is lower zero.");
        }
        this.sizeOfQueue = sizeOfQueue;
        this.comparator = comparator;
        this.array = new Object[sizeOfQueue + 1];
        this.numberOfElements = 0;
    }

    public void add(final E element) {
        if (this.numberOfElements >= this.sizeOfQueue) {
            throw new IllegalArgumentException("priority queue overflow!");
        } else {
            this.numberOfElements++;
            this.array[this.numberOfElements] = element;
            bubbleUp(this.numberOfElements);
        }
    }

    @SuppressWarnings("unchecked")
    public E get() {
        E min;
        if (this.numberOfElements <= 0) {
            throw new IllegalArgumentException("priority queue is empty!");
        } else {
            min = (E) this.array[1];
            this.array[1] = this.array[this.numberOfElements];
            this.array[this.numberOfElements] = null;
            this.numberOfElements--;
            bubbleDown(1);
        }
        return min;
    }

    @SuppressWarnings("unchecked")
    private void bubbleDown(final int index) {
        int childIndex = youngChild(index);
        int counter;
        int minIndex = index;

        for (counter = 0; counter <= 1; counter++) {
            if (childIndex + counter <= this.numberOfElements) {
                if (comparator.compare( (E) this.array[minIndex], (E) this.array[childIndex + counter]) > 0) {
                    minIndex = childIndex + counter;
                }
            }
        }

        if (minIndex != index) {
            swap(index, minIndex);
            bubbleDown(minIndex);
        }
    }

    private int youngChild(final int index) {
        return 2 * index;
    }

    @SuppressWarnings("unchecked")
    private void bubbleUp(final int index) {
        if (parentIndex(index) != -1 &&
                comparator.compare((E)this.array[parentIndex(index)], (E)this.array[index]) > 0) {
            swap(index, parentIndex(index));
            bubbleUp(parentIndex(index));
        }
    }

    private void swap(final int indexA, final int indexB) {
        Object temp = this.array[indexA];
        this.array[indexA] = this.array[indexB];
        this.array[indexB] = temp;
    }

    private int parentIndex(final int index) {
        if (index == 1) {
            return -1;
        } else {
            return index / 2;
        }
    }

    @Override
    public String toString() {
        return "PriorityQueue{" +
                "sizeOfQueue=" + sizeOfQueue +
                ", array=" + Arrays.toString(array) +
                ", numberOfElements=" + numberOfElements +
                '}';
    }

}
