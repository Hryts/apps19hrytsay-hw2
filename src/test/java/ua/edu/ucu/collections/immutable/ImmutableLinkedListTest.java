package ua.edu.ucu.collections.immutable;

import org.junit.Test;
import static org.junit.Assert.*;

public class ImmutableLinkedListTest {
    private final Object[] valuesTest = {1, 2, 3, 4, 5};
    private final int bigIndex = 1000;
    private final int normIndex = 3;

    @Test
    public void testConstructors() {
        ImmutableLinkedList illEmpty = new ImmutableLinkedList();
    }

    @Test
    public void testAdd() {
        ImmutableLinkedList illEmpty = new ImmutableLinkedList();
        for (Object o : valuesTest) {
            illEmpty = illEmpty.add(o);
        }
        Object[] finalArr = illEmpty.toArray();
        assertArrayEquals(finalArr, valuesTest);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddIndexException() {
        ImmutableList ill = new ImmutableLinkedList();
        for (Object o : valuesTest) {
            ill = ill.add(o);
        }
        ImmutableList finalArr = ill.add(bigIndex, "test");
    }

    @Test
    public void testAddIndex() {
        ImmutableList ill = new ImmutableLinkedList();
        for (Object o : valuesTest) {
            ill = ill.add(o);
        }
        ill = ill.add(normIndex, "test");
        Object[] finalArr = ill.toArray();
        Object[] exp = new Object[] {1, 2, 3, "test", 4, 5};
        assertArrayEquals(exp, finalArr);
    }

    @Test
    public void testAddAll() {
        ImmutableList ill = new ImmutableLinkedList();
        for (Object o : valuesTest) {
            ill = ill.add(o);
        }
        ImmutableList finalArr = ill.addAll(valuesTest);
        Object[] expArr = new Object[] {1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
        assertArrayEquals(finalArr.toArray(), expArr);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAllIndexException() {
        ImmutableList immArr = new ImmutableLinkedList();
        ImmutableList finalArr = immArr.addAll(bigIndex, valuesTest);
    }

    @Test
    public void testAddAllIndex() {
        ImmutableList ill = new ImmutableLinkedList();
        for (Object o : valuesTest) {
            ill = ill.add(o);
        }
        ill = ill.addAll(normIndex, valuesTest);
        Object[] finalArr = ill.toArray();
        Object[] expArr = new Object[] {1, 2, 3, 1, 2, 3, 4, 5, 4, 5};
        assertArrayEquals(finalArr, expArr);
    }

    @Test
    public void testSize() {
        ImmutableList ill = new ImmutableLinkedList();
        for (Object o : valuesTest) {
            ill = ill.add(o);
        }
        int actSize = ill.size();
        int expSize = valuesTest.length;
        assertEquals(actSize, expSize);
    }

    @Test
    public void testClear() {
        ImmutableList ill = new ImmutableLinkedList();
        for (Object o : valuesTest) {
            ill = ill.add(o);
        }
        ImmutableList immClear = ill.clear();
        int actSize = immClear.size();
        int expSize = 0;
        assertEquals(actSize, expSize);
    }

    @Test
    public void testIsEmptyNotEmpty() {
        ImmutableList ill = new ImmutableLinkedList();
        for (Object o : valuesTest) {
            ill = ill.add(o);
        }
        assertEquals(ill.isEmpty(), false);
    }

    @Test
    public void testIsEmptyEmpty() {
        ImmutableList ill = new ImmutableLinkedList();
        assertEquals(ill.isEmpty(), true);
    }
}
