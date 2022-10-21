//COMP 352 - Assignment #2, Part 2B
//Due Date: October 30th
//Written by: Augusto Mota Pinheiro (40208080)

/**
 * Generic ArrayList implementation (with an array).
 * @param <T> Type that's going to be stored.
 */
public class ArrayList<T> {
    /**
     * The array backing this ArrayList
     */
    private T[] backingArray;

    /**
     * Current size of the list (not the capacity).
     */
    private int listSize;

    /**
     * Default constructor.
     */
    public ArrayList() {
        this(4);
    }

    /**
     * Parametrized constructor where you can set the initial capacity of the ArrayList.
     * @param size Desired initial capacity.
     */
    public ArrayList(int size) {
        backingArray = (T[]) new Object[size];
    }

    /**
     * Adds the element to the desired index.
     * @param index Index where to add the element.
     * @param element Element to be added.
     */
    public void Add(int index, T element) {
        CheckRangeForAdd(index); // checks if the passed index is valid
        ResizeArrayIfNeeded(); //resize the array if needed

        backingArray[index] = element;
        listSize++;
    }

    /**
     * Removes the element from the desired index.
     * @param index Index where to remove.
     * @return The removed element.
     */
    public T Remove(int index) {
        CheckRange(index);

        T removed = backingArray[index];

        //shifts every element by one (to account for the empty space)
        for (int i = index; i < listSize - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }

        return removed;
    }

    /**
     * Gets the element at the specified index.
     * @param index Index where to get the element.
     * @return The element at the desired index.
     */
    public T Get(int index) {
        CheckRange(index);

        return backingArray[index];
    }

    /**
     * Sets the element at the specified index.
     * @param index Index where to set the element.
     * @param element Element to set.
     */
    public void Set(int index, T element) {
        CheckRange(index);

        backingArray[index] = element;
    }

    /**
     * Returns the size of the ArrayList.
     * @return Size of the ArrayList.
     */
    public int Size()
    {
        return listSize;
    }

    /**
     * Returns the current capacity of the ArrayList.
     * @return Current capacity of the ArrayList.
     */
    public int Capacity()
    {
        return  backingArray.length;
    }

    /**
     * Checks if the passed index is valid for a general operation.
     * @param index Index to check.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    private void CheckRange(int index) {
        if (index < 0 || index >= listSize) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Checks if the passed index is valid for an add operation.
     * @param index Index to check.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    private void CheckRangeForAdd(int index) {
        if (index < 0 || index > listSize) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Resizes the array if needed using doubling.
     */
    private void ResizeArrayIfNeeded() {
        if (listSize + 1 >= backingArray.length) {
            T[] tempArray = (T[]) new Object[backingArray.length * 2];

            for (int i = 0; i < backingArray.length; i++) {
                tempArray[i] = backingArray[i];
            }

            backingArray = tempArray;
        }
    }
}
