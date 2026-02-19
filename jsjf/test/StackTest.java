package jsjf.test;

import org.junit.jupiter.api.*;
import static org.junit.Assert.*;

import jsjf.*;

public class StackTest {
    private static final int TEST_QTY = 10;
    private StackADT<Integer> testStack;

    // To test a different StackInterface implementation, change the value assigned
    // in setup.
    @BeforeEach
    public void setup() {
        testStack = new ArrayStack<>();
        // testStack = new LinkedStack<>();
    }

    @Test
    public void givenEmptyStack_whenPushElements_thenPopElementsAreReversed() {
        for (int i = 1; i <= TEST_QTY; i++) {
            testStack.push(i);
        }
        for (Integer i = TEST_QTY; i >= 1; i--) {
            assertEquals(i, testStack.pop());
        }
    }

    @Test
    public void givenEmptyStack_whenNothing_thenPopThrowException() {
        Assertions.assertThrows(EmptyCollectionException.class, () -> testStack.pop());
    }

    @Test
    public void givenEmptyStack_whenNothing_thenPeekThrowException() {
        Assertions.assertThrows(EmptyCollectionException.class, () -> {
            testStack.peek();
        });
    }

    @Test
    public void givenEmptyStack_whenPushAndPopElements_thenPeekMatchesLastPushedElement() {
        for (Integer i = 0; i < TEST_QTY; i++) {
            testStack.push(i);
            assertEquals(i, testStack.peek());
        }

        for (Integer i = TEST_QTY - 1; i > 0; i--) {
            assertEquals(i, testStack.peek());
            testStack.pop();
        }

    }

    @Test
    public void givenEmptyStack_whenPushandPopElements_thenSizeMatchesNumberOfElementsOnStack() {
        assertEquals(0, testStack.size());

        for (int i = 1; i <= TEST_QTY; i++) {
            testStack.push(i);
            assertEquals(i, testStack.size());
        }

        for (int i = TEST_QTY; i >= 1; i--) {
            testStack.pop();
            assertEquals(i - 1, testStack.size());
        }

        assertEquals(0, testStack.size());
    }

    @Test
    public void givenEmptyStack_whenPushAndPopElements_thenIsEmptyTrueWhenStackEmpty() {
        assertTrue(testStack.isEmpty());
        testStack.push(0);
        assertFalse(testStack.isEmpty());
        testStack.pop();
        assertTrue(testStack.isEmpty());
    }

    @Test
    public void givenEmptyStack_whenPushElements_thenToStringMatchesReversedValues() {
        assertEquals("top [ ]", "" + testStack);
        testStack.push(1);
        testStack.push(2);
        testStack.push(3);
        assertEquals("top [ 3, 2, 1 ]", "" + testStack);
    }
}
