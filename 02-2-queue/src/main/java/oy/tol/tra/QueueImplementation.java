package oy.tol.tra;

public class QueueImplementation<E> implements QueueInterface<E> {

    private E[] itemArray;
    private int capacity;
    private int current = 0;
    private int head = 0;
    private int tail = -1;
    private static final int DEFAULT_QUEUE_SIZE = 10;

    public QueueImplementation() throws QueueAllocationException {
        this(DEFAULT_QUEUE_SIZE);
    }

    @SuppressWarnings("unchecked")
    public QueueImplementation(int capacity) throws QueueAllocationException {
        if (capacity < 2) {
            throw new QueueAllocationException("Capacity must be at least 2.");
        }
        this.capacity = capacity;
        this.itemArray = (E[]) new Object[capacity];
        // Initialize head and tail in the parameterized constructor
        this.head = 0;
        this.tail = -1;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {
        // Check for null elements before reallocation
        if (element == null) {
            throw new NullPointerException("Cannot enqueue null into the queue.");
        }
        ensureCapacity();
        tail = (tail + 1) % capacity;
        itemArray[tail] = element;
        current++;
    }

    @Override
    public E dequeue() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Cannot dequeue from an empty queue.");
        }
        E returnE = element();
        // Set the removed element to null
        itemArray[head] = null;
        head = (head + 1) % capacity;
        current--;
        return returnE;
    }

    @Override
    public E element() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Cannot dequeue from an empty queue.");
        }
        return itemArray[head];
    }

    @Override
    public int size() {
        return current;
    }

    @Override
    public boolean isEmpty() {
        return current == 0;
    }

    @Override
    public void clear() {
        head = 0;
        tail = -1;
        current = 0;
        // Optionally, clear the array to free up memory
        for (int i = 0; i < capacity; i++) {
            itemArray[i] = null;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        int index = head;
        int loopTime = current;
        while (loopTime-- > 0) {
            builder.append(itemArray[index].toString());
            index = (index + 1) % capacity;
            if (loopTime != 0) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    private void ensureCapacity() {
        if (current == capacity) {
            int newCapacity = capacity * 2 + 1;
            E[] newArray = (E[]) new Object[newCapacity];
            int indexOfItemArray = head;
            int index = 0;
            int loop = current;
            while (loop-- > 0) {
                newArray[index++] = itemArray[indexOfItemArray];
                indexOfItemArray = (indexOfItemArray + 1) % capacity;
            }
            head = 0;
            tail = index - 1;
            itemArray = newArray;
            capacity = newCapacity;
        }
    }
}

