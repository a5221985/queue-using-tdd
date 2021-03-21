package org.example;

public class Queue {
    private static final int INITIAL_CAPACITY = 10;
    private int[] elements = new int[INITIAL_CAPACITY];
    private int size = 0;
    private int headIndex = 0;
    private int tailIndex = -1;
    private int capacity = INITIAL_CAPACITY;

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(int element) {
        expandQueueIfRequired();
        appendElement(element);
    }

    public int dequeue() {
        verifyThatQueueIsNotEmpty();
        int currentElement = removeElement();
        resetQueueIfRequired();
        return currentElement;
    }

    public int size() {
        return size;
    }

    private void appendElement(int element) {
        tailIndex = (++tailIndex) % capacity;
        elements[tailIndex] = element;
        size++;
    }

    private int removeElement() {
        int currentElement = elements[headIndex];
        headIndex = (++headIndex) % capacity;
        size--;
        return currentElement;
    }

    private void verifyThatQueueIsNotEmpty() {
        if (isEmpty())
            throw new UnderflowException();
    }

    private void resetQueueIfRequired() {
        if (isEmpty())
            resetQueue();
    }

    private void resetQueue() {
        headIndex = 0;
        tailIndex = -1;
    }

    private void expandQueueIfRequired() {
        if (size == capacity)
            expandQueue();
    }

    private void expandQueue() {
        capacity *= 2;
        elements = increaseCapacity(elements, capacity, headIndex, tailIndex);
        headIndex = 0;
        tailIndex = size() - 1;
    }

    private int[] increaseCapacity(final int[] elements, int newCapacity, int sourceStartIndex, int sourceEndIndex) {
        int[] newElements = new int[newCapacity];
        int k = 0;
        for (int i = sourceStartIndex; i <= sourceEndIndex; i++)
            newElements[k++] = elements[i];
        return newElements;
    }

    public class UnderflowException extends RuntimeException {
    }
}
