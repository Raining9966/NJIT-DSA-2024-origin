package oy.tol.tra;

public class StackImplementation<E> implements StackInterface<E> {

    private E[] itemArray;
    private int capacity;
    private int currentIndex = -1;
    private static final int DEFAULT_STACK_SIZE = 10;

    public StackImplementation() throws StackAllocationException {
        this(DEFAULT_STACK_SIZE);
    }

    @SuppressWarnings("unchecked")
    public StackImplementation(int capacity) throws StackAllocationException {
        if (capacity < 2) {
            throw new StackAllocationException("Capacity must be at least 2.");
        }
        this.capacity = capacity;
        this.itemArray = (E[]) new Object[capacity];
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public void push(E element) throws StackAllocationException, NullPointerException {
        if (element == null) {
            throw new NullPointerException("Cannot push null into the stack.");
        }
        ensureCapacity();
        itemArray[++currentIndex] = element;
    }

    @Override
    public E pop() throws StackIsEmptyException {
        if (isEmpty()) {
            throw new StackIsEmptyException("Cannot pop from an empty stack.");
        }
        E poppedElement = itemArray[currentIndex];
        itemArray[currentIndex] = null; // Remove the element from the internal storage
        currentIndex--;
        return poppedElement;
    }

    @Override
    public E peek() throws StackIsEmptyException {
        if (isEmpty()) {
            throw new StackIsEmptyException("Cannot peek into an empty stack.");
        }
        return itemArray[currentIndex];
    }

    @Override
    public int size() {
        return currentIndex + 1;
    }

    @Override
    public void clear() {
        currentIndex = -1;
    }

    @Override
    public boolean isEmpty() {
        return currentIndex == -1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int index = 0; index <= currentIndex; index++) {
            builder.append(itemArray[index].toString());
            if (index < currentIndex) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    private void ensureCapacity() {
        if (size() == capacity()) {
            int newCapacity = capacity * 2 + 1;
            E[] newArray = (E[]) new Object[newCapacity];
            System.arraycopy(itemArray, 0, newArray, 0, itemArray.length);
            itemArray = newArray;
            capacity = newCapacity;
        }
    }
}

