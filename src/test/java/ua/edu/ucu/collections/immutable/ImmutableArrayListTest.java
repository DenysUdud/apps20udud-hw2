package ua.edu.ucu.collections.immutable;

import org.junit.Before;
import org.junit.Test;
// import sun.jvm.hotspot.oops.ObjArrayKlass;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ImmutableArrayListTest {
    private static final Object[] defaultArray = {1, 2.0, "three"};
    public ImmutableList testArray = new ImmutableArrayList(defaultArray);


    @Test
    public void testAdd() {
        Object[] expectedResult = {1, 2.0, "three", 4};
        int value = 4;

        ImmutableList modifiedArray = testArray.add(value);

        assertArrayEquals(expectedResult, modifiedArray.toArray());
    }

    @Test
    public void testAddIndex() {
        Object[] expectedResult = {1, 2.0, "insert", "three"};
        int index = 2;
        Object value = "insert";

        ImmutableList modifiedArray = testArray.add(index, value);

        assertArrayEquals(expectedResult, modifiedArray.toArray());
    }

    @Test
    public void testAddAll() {
        Object[] expectedResult = {1, 2.0, "three", 4, 5, 6};
        Object[] arrayToAdd = {4, 5, 6};

        ImmutableList modifiedArray = testArray.addAll(arrayToAdd);
        System.out.println(Arrays.toString(modifiedArray.toArray()));

        assertArrayEquals(expectedResult, modifiedArray.toArray());
    }

    @Test
    public void TestAddAllIndex() {
        Object[] expectedResult = {1, 4, 5, 6, 2.0, "three"};
        Object[] arrayToAdd = {4, 5, 6};
        int index = 1;

        ImmutableList modifiedArray = testArray.addAll(index, arrayToAdd);

        assertArrayEquals(expectedResult, modifiedArray.toArray());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testAddIndexError() {
        int index = 5, value = 4;
        testArray.add(index, value);

        int index1 = -1, value1 = 4;
        testArray.add(index1, value1);
    }

    @Test
    public void testGet() {
        Object expectedValue = 1;
        int index = 0;

        assertEquals(expectedValue, testArray.get(index));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetIndexError() {
        int index = testArray.size() + 1;
        testArray.get(index);
    }

    @Test
    public void testRemove() {
        Object[] expectedResult = {1, 2.0};
        int index = 2;

        ImmutableList modifiedArray = testArray.remove(index);

        assertArrayEquals(expectedResult, modifiedArray.toArray());
    }

    @Test
    public void testSet() {
        Object[] expectedResult = {1, 2.0, 3};
        int elementToSet = 3;
        int index = 2;

        ImmutableList modifiedArray = testArray.set(index,
                elementToSet
        );

        assertArrayEquals(expectedResult, modifiedArray.toArray());
    }

    @Test
    public void testIndexOf() {
        // standard case
        int expectedResult = 2;
        String value = "three";

        int resultIndex = testArray.indexOf(value);
        assertEquals(expectedResult, resultIndex);

        // case Array without element
        expectedResult = -1;
        value = "3";

        int resultIndex2 = testArray.indexOf(value);
        assertEquals(expectedResult, resultIndex2);

        // case Empty ArrayList
        ImmutableList emptyArrayList = testArray.clear();

        int resresultIndex3 = emptyArrayList.indexOf(value);

        assertEquals(expectedResult, resresultIndex3);
    }

    @Test
    public void testSize() {
        // Standard case
        int expectedValue = 3;
        int realSize = testArray.size();

        assertEquals(expectedValue, realSize);

        // Empty case
        ImmutableList emptyList = testArray.clear();
        expectedValue = 0;
        realSize = emptyList.size();

        assertEquals(expectedValue, realSize);
    }

    @Test
    public void testClear() {
        int expectedValue = 0;

        // clear array
        ImmutableList emptyList = testArray.clear();
        int sizeAfterClear = emptyList.size();

        assertEquals(expectedValue, sizeAfterClear);
    }

    @Test
    public void testisEmpty() {
        // base case
        boolean result = testArray.isEmpty();

        assertFalse(result);

        // empty list case
        ImmutableList emptyArray = new ImmutableArrayList();
        result = emptyArray.isEmpty();

        assertTrue(result);
    }

    @Test
    public void testToArray() {
        Object[] expectedResult = {1, 2.0, "three"};
        Object[] result = testArray.toArray();

        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void testToString() {
        String expectedResult = "[1, 2.0, three]";
        String result = testArray.toString();

        assertEquals(expectedResult, result);
    }

    @Test
    public void testImmutable() {
        Object[] basicArray = {1, 2, 3};
        ImmutableList immutableList = new ImmutableArrayList(
                basicArray);

        // if our object is immutable, after cleaning it won't change
        immutableList.clear();
        assertArrayEquals(basicArray, immutableList.toArray());
    }
}
