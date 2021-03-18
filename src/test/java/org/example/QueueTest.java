package org.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueueTest {

    private Queue queue;

    @Before
    public void setUp() throws Exception {
        queue = new Queue();
    }

    @Test
    public void onCreation_QueueIsEmpty() {
        assertTrue(queue.isEmpty());
    }

    @Test(expected = Queue.UnderflowException.class)
    public void onDequeueOfNewQueue_QueueThrowsException() {
        queue.dequeue();
    }

    @Test
    public void onEnqueue_QueueIsNotEmpty() {
        queue.enqueue(0);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void onEnqueueAndDequeueOnce_SameElementMustBeReturned() {
        queue.enqueue(25);
        assertEquals(25, queue.dequeue());
        queue.enqueue(45);
        assertEquals(45, queue.dequeue());
    }

    @Test
    public void onEnqueueTwiceAndDequeueOnce_QueueIsNotEmpty() {
        queue.enqueue(25);
        queue.enqueue(34);
        queue.dequeue();
        assertFalse(queue.isEmpty());
    }

    @Test
    public void onEnqueueTwiceAndDequeueTwice_QueueEmpty() {
        queue.enqueue(25);
        queue.enqueue(34);
        queue.dequeue();
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void onEnqueueTwiceAndDequeueTwice_ElementsShouldReturnInFIFOOrder() {
        queue.enqueue(25);
        queue.enqueue(56);
        assertEquals(25, queue.dequeue());
        assertEquals(56, queue.dequeue());
    }

    @Test
    public void onEnqueueAndDequeueFiveTimes_ElementsShouldReturnInFIFOOrder() {
        for (int i = 0; i < 5; i++)
            queue.enqueue(i);

        for (int i = 0; i < 5; i++)
            assertEquals(i, queue.dequeue());
    }

    @Test
    public void onIndefiniteEnqueue_QueueShouldExpandIndefinitely() {
        for (int i = 0; i < 1000; i++)
            queue.enqueue(i);
        for (int i = 0; i < 1000; i++)
            assertEquals(i, queue.dequeue());
    }

    @Test
    public void ifCurrentSizeHasNotReachedCapacity_AddingNewElementsShouldFillTheQueue() {
        for (int i = 0; i < 10; i++)
            queue.enqueue(i);
        for (int i = 0; i < 5; i++)
            queue.dequeue();
        for (int i = 0; i < 5; i++)
            queue.enqueue(i);
        assertEquals(10, queue.size());
    }

    @Test
    public void ifCurrentSizeHasNotReachedCapacity_RemovingAllElementsShouldEmptyTheQueue() {
        for (int i = 0; i < 10; i++)
            queue.enqueue(i);
        for (int i = 0; i < 5; i++)
            assertEquals(i, queue.dequeue());
        for (int i = 0; i < 5; i++)
            queue.enqueue(i);
        for (int i = 5; i < 15; i++)
            assertEquals(i % 10, queue.dequeue());
    }

    @Test
    public void ifCurrentQueueIsEmptied_AddingNewElementsAndRemovingShouldWork() {
        for (int i = 0; i < 10; i++)
            queue.enqueue(i);
        for (int i = 0; i < 5; i++)
            assertEquals(i, queue.dequeue());
        for (int i = 0; i < 5; i++)
            queue.enqueue(i);
        for (int i = 5; i < 15; i++)
            assertEquals(i % 10, queue.dequeue());
        for (int i = 0; i < 100; i++)
            queue.enqueue(i);
        for (int i = 0; i < 100; i++)
            assertEquals(i, queue.dequeue());
    }
}
