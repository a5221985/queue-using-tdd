package org.example;

public class Queue {
    private static final int INITIAL_CAPACITY = 10;
    private int[] elements = new int[INITIAL_CAPACITY];
    private int size = 0;
    private int first = 0;
    private int last = -1;
    private int capacity = INITIAL_CAPACITY;

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(int element) {
        expandQueueIfRequired();
        last = (++last) % capacity;
        elements[last] = element;
        size++;
    }

    public int dequeue() {
        if (isEmpty())
            throw new UnderflowException();
        int current = first;
        first = (++first) % capacity;
        size--;
        resetQueueIfRequired();
        return elements[current];
    }

    public int size() {
        return size;
    }

    private void resetQueueIfRequired() {
        if (isEmpty())
            resetQueue();
    }

    private void resetQueue() {
        first = 0;
        last = -1;
    }

    private void expandQueueIfRequired() {
        if (size == capacity)
            expandQueue();
    }

    private void expandQueue() {
        capacity *= 2;
        elements = increaseCapacity(elements, capacity, first, last);
        first = 0;
        last = size() - 1;
    }

    private int[] increaseCapacity(final int[] elements, int newCapacity, int sourceStartIndex, int sourceEndIndex) {
        int[] newElements = new int[newCapacity];
        for (int i = sourceStartIndex, k = 0; i <= sourceEndIndex; i++)
            newElements[k++] = elements[i];
        return newElements;
    }

    public class UnderflowException extends RuntimeException {
    }
}
