package heaps;

public class MaxHeap {
    private int capacity = 10;
    private int size = 0;
    private int[] items = new int[capacity];

    public boolean add(int value) {
        if (size == capacity) {
            expandArray();
        }
        int index = size;
        size++;
        items[index] = value;
        // bubble item up to correct place
        while (items[index] > items[getParent(index)]) {
            int temp = items[getParent(index)];
            items[getParent(index)] = items[index];
            items[index] = temp;
            index = getParent(index);
        }
        return true;
    }

    public int deleteRoot() {
        if (size == 0) return -1;
        int root = items[0];
        items[0] = items[size - 1];
        items[size - 1] = root;
        size--;
        // bubble item down to correct place
        int index = 0;
        while (hasLeftChild(index, size)) {
            int largestChildIndex = getLeftChild(index);

            if (hasRightChild(index, size) && items[getRightChild(index)] > items[largestChildIndex]) {
                largestChildIndex = getRightChild(index);
            }

            if (items[index] > items[largestChildIndex]) {
                break;
            } else {
                int temp = items[largestChildIndex];
                items[largestChildIndex] = items[index];
                items[index] = temp;
                index = largestChildIndex;
            }
        }
        return root;
    }

    // given a filled array, heapify it and store it in the class
    public int[] heapifyAndStore(int[] arr) {
        size = arr.length;
        capacity = arr.length;
        items = heapifyInPlace(arr);
        return items;
    }

    // given a full array, heapify it using the same array
    public int[] heapifyInPlace(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            int index = i;
            while (hasLeftChild(index, arr.length)) {
                int largestChildIndex = getLeftChild(index);
                if (hasRightChild(index, arr.length) && arr[largestChildIndex] < arr[getRightChild(index)]) {
                    largestChildIndex = getRightChild(index);
                }

                if (arr[largestChildIndex] < arr[index]) {
                    break;
                }
                else {
                    int temp = arr[largestChildIndex];
                    arr[largestChildIndex] = arr[index];
                    arr[index] = temp;
                    index = largestChildIndex;
                }
            }
        }
        return arr;
    }

    public int[] heapSortInPlace(int[] arr) {
        arr = heapifyInPlace(arr);
        int size = arr.length;
        while (size > 0) {
            // delete items one by one
            int root = arr[0];
            arr[0] = arr[size - 1];
            arr[size - 1] = root;
            size--;
            // bubble element down
            int index = 0;
            while (hasLeftChild(index, size)) {
                int largestChildIndex = getLeftChild(index);
                if (hasRightChild(index, size) && arr[getRightChild(index)] > arr[largestChildIndex]) {
                    largestChildIndex = getRightChild(index);
                }

                if (arr[index] > arr[largestChildIndex]) {
                    break;
                } else {
                    int temp = arr[largestChildIndex];
                    arr[largestChildIndex] = arr[index];
                    arr[index] = temp;
                    index = largestChildIndex;
                }
            }
        }
        return arr;
    }

    private void expandArray() {
        int newCap = capacity * 2;
        int[] newArr = new int[newCap];
        for (int i = 0; i < items.length; i++) {
            newArr[i] = items[i];
        }
        items = newArr;
        capacity = newCap;
    }

    private int getParent(int i) {
        return (i - 1) / 2;
    }

    private int getLeftChild(int i) {
        return (i * 2) + 1;
    }

    private int getRightChild(int i) {
        return (i * 2) + 2;
    }

    private boolean hasLeftChild(int i, int size) {
        return getLeftChild(i) < size;
    }

    private boolean hasRightChild(int i, int size) {
        return getRightChild(i) < size;
    }

    // used for testing
    public int[] getItems() {
        return items;
    }

    public int getSize() {
        return size;
    }
}
