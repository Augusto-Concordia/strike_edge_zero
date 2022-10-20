public class Stack<T> {
    private T[] backingArray;
    private int stackSize;

    public Stack()
    {
        this(4);
    }

    public Stack(int size)
    {
        this((T[])new Object[size]);
    }

    public Stack(T[] array)
    {
        stackSize = 0;
        backingArray = array.clone();
    }

    public void Push(T element) {
        ResizeArrayIfNeeded();

        backingArray[stackSize] = element;
        stackSize++;
    }

    public T Pop()
    {
        if (stackSize > 0)
        {
            stackSize--;
            return backingArray[stackSize];
        }

        return null;
    }

    public boolean Peek(T[] comparison, int minSuccesses)
    {
        int successes = 0;

        for (int i = 0; i < comparison.length; i++) {
            if (comparison[comparison.length - i - 1] == backingArray[backingArray.length - i - 1])
            {
                successes++;

                if (successes > minSuccesses) return true;
            }
        }

        return false;
    }

    private void ResizeArrayIfNeeded()
    {
        if (stackSize + 1 >= backingArray.length)
        {
            T[] tempArray = (T[])new Object[backingArray.length * 2];

            for (int i = 0; i < backingArray.length; i++) {
                tempArray[i] = backingArray[i];
            }

            backingArray = tempArray;
        }
    }
}
