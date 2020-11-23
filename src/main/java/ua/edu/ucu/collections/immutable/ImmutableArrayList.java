package ua.edu.ucu.collections.immutable;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.Arrays;

public class ImmutableArrayList implements ImmutableList {

    private static final int DEFAULT_SIZE = 0;
    private final Object[] elements;

    public ImmutableArrayList() {
        elements = new Object[DEFAULT_SIZE];
    }

    public ImmutableArrayList(Object[] elements) {
        // as long as we defined immutable object we make a copy
        this.elements = Arrays.copyOf(elements, elements.length);
    }

    @Override
    public ImmutableList add(Object e) {
        return addAll(elements.length, new Object[]{e});
    }

    @Override
    public ImmutableList add(int index, Object e) {
        return addAll(index, new Object[]{e});
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return addAll(elements.length, c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        if (index < 0 || index > elements.length) {
            throw new ArrayIndexOutOfBoundsException();
        }

        // define new array
        Object[] newArray = Arrays.copyOf(elements, elements.length + c.length);
        // copy elements from old array to the right position
        // make space for c elements
        System.arraycopy(elements, index, newArray,
                index + c.length,
                elements.length - index);

        System.arraycopy(c, 0, newArray, index, c.length);

        return new ImmutableArrayList(newArray);
    }

    @Override
    public Object get(int index) {
        checkIndex(index);
        return elements[index];
    }

    @Override
    public ImmutableList remove(int index) {
        checkIndex(index);

        Object[] newArray = new Object[elements.length - 1];

        // copy part before index
        System.arraycopy(elements, 0, newArray,
                        0, index
        );
        // copy another part of Array
        System.arraycopy(elements, index + 1,
                newArray, index,
                elements.length - index - 1
        );

        return new ImmutableArrayList(newArray);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        checkIndex(index);

        Object[] newArray = Arrays.copyOf(elements, elements.length);

        // set e to to the right position
        newArray[index] = e;
        return new ImmutableArrayList(newArray);
    }

    @Override
    public int indexOf(Object e) {
        if (isEmpty()) {
            return -1;
        }

        int index = 0;
        for (Object element : elements) {
            if (element.equals(e)) {
                return index;
            }
            index++;
        }
        // element e was not found
        return -1;
    }

    @Override
    public int size() {
        return elements.length;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        return (elements.length == 0);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, elements.length);
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

    private void checkIndex(int index) {
        // there we can realize if index < 1 index = length - 1
        if (index < 0 || index >= elements.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
