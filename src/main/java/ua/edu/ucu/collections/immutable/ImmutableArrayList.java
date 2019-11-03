package ua.edu.ucu.collections.immutable;

public class ImmutableArrayList implements ImmutableList {
    private int capacity;
    private int size;
    private Object[] values;

    public ImmutableArrayList() {
        capacity = 0;
        size = 0;
        values = new Object[]{};
    }

    ImmutableArrayList(Object[] values) {
        this.values = values;
        size = values.length;
        capacity = values.length;
    }

    private ImmutableArrayList(int size, Object[] values) {
        capacity = values.length;
        this.values = values;
        this.size = size;
    }

    private ImmutableArrayList copy() {
        Object[] valuesCopy = new Object[capacity];
        if (size >= 0) System.arraycopy(values, 0, valuesCopy, 0, size);
        return new ImmutableArrayList(size, valuesCopy);
    }

    private void resize(int actualSize) {
        while (size > capacity){
            Object[] valuesResized = new Object[capacity * 2];
            System.arraycopy(values, 0, valuesResized, 0, actualSize);
            values = valuesResized;
            capacity *= 2;
        }
    }

    private Object[] getValues() {return values;}

    private int getSize() {return size;}

    private void setSize(int size) {this.size = size;}

    @Override
    public ImmutableList add(Object e) {
        ImmutableArrayList res = copy();
        res.setSize(res.getSize() + 1);
        res.resize(size);
        res.getValues()[res.getSize()-1] = e;
        return res;
    }

    @Override
    public ImmutableList add(int index, Object e) {
        if (index >= size) throw new IndexOutOfBoundsException();
        ImmutableArrayList res = copy();
        res.setSize(res.getSize() + 1);
        res.resize(size);
        Object temp = e;
        Object tempNext;
        for (int i = index; i <= res.size; i++) {
            tempNext = res.getValues()[i];
            res.getValues()[i] = temp;
            temp = tempNext;
        }
        return res;
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        ImmutableArrayList res = copy();
        int cLen = c.length;
        res.setSize(res.getSize() + cLen);
        res.resize(size);
        //noinspection ManualArrayCopy
        for (int i = size; i < res.getSize(); i++) {
            res.getValues()[i] = c[i-cLen];
        }
        return res;
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        int cLen = c.length;
        Object[] newValues = new Object[size + cLen];
        System.arraycopy(values, 0, newValues, 0, index);
        System.arraycopy(c, 0 , newValues, index, cLen);
        System.arraycopy(values, index, newValues, index + cLen,
                size - index);
        return new ImmutableArrayList(newValues.length, newValues);
    }

    @Override
    public Object get(int index) {
        return values[index];
    }

    @Override
    public ImmutableList remove(int index) {
        Object[] newValues = new Object[size-1];
        System.arraycopy(values, 0, newValues, 0, index++);
        System.arraycopy(values, index, newValues, index,
                values.length - index);
        return new ImmutableArrayList(capacity, newValues);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        ImmutableArrayList res = copy();
        res.getValues()[index] = e;
        return res;
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == e) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {return size;}

    @Override
    public ImmutableList clear() {return new ImmutableArrayList();}

    @Override
    public boolean isEmpty() {return size == 0;}

    @Override
    public Object[] toArray() {
        Object[] res = new Object[size];
        System.arraycopy(values, 0 , res, 0, size);
        return res;
    }
}
