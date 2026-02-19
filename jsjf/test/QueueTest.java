package jsjf.test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.*;

import jsjf.*;

public class QueueTest {
    private static final int TEST_QTY = 100000;
    private QueueADT<Integer> queue;

    // To test a different QueueInterface implementation, change the value
    // assigned in setup.
    @BeforeEach
    public void setup() {
        queue = new ArrayQueue<Integer>();
        // queue = new CircularArrayQueue<Integer>();
        //queue = new LinkedQueue<Integer>();
    }

    @Test
    public void givenEmptyQueue_whenDequeue_thenExceptionThrown() {
        Assertions.assertThrows(EmptyCollectionException.class, () -> {
            queue.dequeue();
        });
    }

    @Test
    public void givenEmptyQueue_whenEnqueueElements_thenDequeueInSameOrder() {
        for (int i = 1; i <= TEST_QTY; i++) {
            queue.enqueue(i);
        }

        for (Integer i = 1; i <= TEST_QTY; i++) {
            assertEquals(i, queue.dequeue());
        }
    }

    @Test
    public void givenEmptyQueue_whenEnqueueElementsTwice_thenDequeueInSameOrder() {
        givenEmptyQueue_whenEnqueueElements_thenDequeueInSameOrder();
        givenEmptyQueue_whenEnqueueElements_thenDequeueInSameOrder();
    }

    @Test
    public void givenEmptyQueue_whenFirst_thenExceptionThrown() {
        Assertions.assertThrows(EmptyCollectionException.class, () -> {
            queue.first();
        });
    }

    @Test
    public void givenEmptyQueue_whenEnqueue_thenFirstInSameOrder() {
        for (int i = 1; i <= TEST_QTY; i++) {
            queue.enqueue(i);
        }

        for (Integer i = 1; i <= TEST_QTY; i++) {
            assertEquals(i, queue.first());
            queue.dequeue();
        }
    }

    @Test
    public void givenEmptyQueue_whenEnqueueAndDequeue_ThenSizeChangeToMatch() {
        assertEquals(0, queue.size());

        for (int i = 1; i <= TEST_QTY; i++) {
            queue.enqueue(i);
            assertEquals(i, queue.size());
        }

        for (int i = TEST_QTY; i >= 1; i--) {
            queue.dequeue();
            assertEquals(i - 1, queue.size());
        }

        assertEquals(0, queue.size());
    }

    @Test
    void givenEmptyQueue_whenNothingChanged_thenIsEmptyTrue() {
        assertTrue(queue.isEmpty());
    }

    @Test
    public void givenEmptyQueue_whenEnqueueAndDequeue_thenIsEmptyMatchesQueueState() {
        queue.enqueue(0);
        assertFalse(queue.isEmpty());
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void givenEmptyQueue_whenNoChanges_thenToStringMatchesEmptyQueue() {
        assertEquals("front [ ] rear", queue.toString());
    }

    @Test
    public void givenEmptyQueue_whenEnqueueItems_thenToStringMatchesItems() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals("front [ 1, 2, 3 ] rear", queue.toString());
        // assert twice to confirm that toString didn't damage the queue
        assertEquals("front [ 1, 2, 3 ] rear", queue.toString());
    }
}
