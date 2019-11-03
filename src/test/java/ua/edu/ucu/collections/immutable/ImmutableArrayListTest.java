package ua.edu.ucu.collections.immutable;

import org.junit.Test;
import static org.junit.Assert.*;

public class ImmutableArrayListTest {
    private final int capacityTest = 5;
    private final Object[] valuesTest = {1, 2, 3, 4, 5};
    private final int bigIndex = 1000;
    private final int normIndex = 3;

    @Test
    public void testConstructors() {
        ImmutableArrayList ialEmpty = new ImmutableArrayList();
        ImmutableArrayList ialWithValues = new ImmutableArrayList(valuesTest);
    }

    @Test
    public void testCopy() {
    }

    @Test
    public void testAdd() {
        Object[] arr = new Object[] {1, 2, 3, 4, 5};
        ImmutableList immArr = new ImmutableArrayList(arr);
        ImmutableList finalArr = immArr.add("test");
        Object[] expArr = new Object[] {1, 2, 3, 4, 5, "test"};
        System.out.println(finalArr.toArray());
        assertArrayEquals(finalArr.toArray(), expArr);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddIndexException() {
        Object[] arr = new Object[] {1, 2, 3, 4, 5};
        ImmutableList immArr = new ImmutableArrayList(arr);
        ImmutableList finalArr = immArr.add(bigIndex, "test");
    }

    @Test
    public void testAddIndex() {
        Object[] arr = new Object[] {1, 2, 3, 4, 5};
        ImmutableList immArr = new ImmutableArrayList(arr);
        ImmutableList finalArr = immArr.add(normIndex, "test");
        Object[] expArr = new Object[] {1, 2, 3, "test", 4, 5};
        assertArrayEquals(finalArr.toArray(), expArr);
    }

    @Test
    public void testAddAll() {
        Object[] arr = new Object[] {1, 2, 3, 4, 5};
        ImmutableList immArr = new ImmutableArrayList(arr);
        ImmutableList finalArr = immArr.addAll(valuesTest);
        Object[] expArr = new Object[] {1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
        assertArrayEquals(finalArr.toArray(), expArr);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAllIndexException() {
        Object[] arr = new Object[] {1, 2, 3, 4, 5};
        ImmutableList immArr = new ImmutableArrayList(arr);
        ImmutableList finalArr = immArr.addAll(bigIndex, valuesTest);
    }

    @Test
    public void testAddAllIndex() {
        Object[] arr = new Object[] {1, 2, 3, 4, 5};
        ImmutableList immArr = new ImmutableArrayList(arr);
        ImmutableList finalArr = immArr.addAll(normIndex, valuesTest);
        Object[] expArr = new Object[] {1, 2, 3, 1, 2, 3, 4, 5, 4, 5};
        assertArrayEquals(finalArr.toArray(), expArr);
    }

    @Test
    public void testSize() {
        ImmutableList immArr = new ImmutableArrayList(valuesTest);
        int actSize = immArr.size();
        int expSize = valuesTest.length;
        assertEquals(actSize, expSize);
    }

    @Test
    public void testClear() {
        ImmutableList immArr = new ImmutableArrayList(valuesTest);
        ImmutableList immClear = immArr.clear();
        int actSize = immClear.size();
        int expSize = 0;
        assertEquals(actSize, expSize);
    }

    @Test
    public void testIsEmptyNotEmpty() {
        ImmutableList immArr = new ImmutableArrayList(valuesTest);
        assertEquals(immArr.isEmpty(), false);
    }

    @Test
    public void testIsEmptyEmpty() {
        ImmutableList immArr = new ImmutableArrayList();
        assertEquals(immArr.isEmpty(), true);
    }
}
