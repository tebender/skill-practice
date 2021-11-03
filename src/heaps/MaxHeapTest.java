package heaps;

import dynamicprogramming.CanSum;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;

public class MaxHeapTest extends TestCase {
    private MaxHeap heap;

    @BeforeClass
    public void setUp() {
        heap = new MaxHeap();
    }

    @Test
    public void testAdd() {
        assertEquals(0, heap.getSize());
        heap.add(5);
        heap.add(8);
        heap.add(3);
        heap.add(100);
        heap.add(0);
        int[] items = heap.getItems();
        int[] expected = {100, 8, 3, 5, 0};
        assertEquals(5, heap.getSize());
        for (int i = 0; i < heap.getSize(); i++) {
            assertEquals(expected[i], items[i]);
        }
    }

    @Test
    public void testDeleteRoot() {
        // quick lil negative test
        assertEquals(-1, heap.deleteRoot());

        heap.add(5);
        heap.add(8);
        heap.add(3);
        heap.add(100);
        heap.add(0);

        int deleted = heap.deleteRoot();
        assertEquals(100, deleted);
        int[] items = heap.getItems();
        int[] expected = {8, 5, 3, 0};
        assertEquals(4, heap.getSize());
        for (int i = 0; i < heap.getSize(); i++) {
            assertEquals(expected[i], items[i]);
        }
    }

    @Test
    public void testHeapifyAndStore() {
        int[] original = {5, 8, 3, 100, 0};
        int[] result = heap.heapifyAndStore(original);
        int[] items = heap.getItems();
        int[] expected = {100, 8, 3, 5, 0};
        for (int i = 0; i < heap.getSize(); i++) {
            assertEquals(expected[i], result[i]);
            assertEquals(expected[i], items[i]);
        }
    }

    @Test
    public void testHeapifyInPlace() {
        int[] original = {5, 8, 3, 100, 0};
        int[] result = heap.heapifyInPlace(original);
        int[] expected = {100, 8, 3, 5, 0};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result[i]);
        }

        assertEquals(0, heap.getSize());
    }

    @Test
    public void testHeapSortInPlace() {
        int[] original = {4, 7, 6, 1, 8, 2, 0, 5, 9, 3};
        int[] result = heap.heapSortInPlace(original);
        int[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result[i]);
        }

        assertEquals(0, heap.getSize());
    }
}