package dynamicprogramming;

import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Stack;

public class CanSumTest extends TestCase {
    private CanSum canSumObj;

    @BeforeClass
    public void setUp() {
        canSumObj = new CanSum();
    }

    @Test
    public void testCanSum() {
        canSumObj.clearCanSumMemo();
        canSumObj.clearHowSumMemo();
        int[] numbers = {1, 2, 3};
        assertTrue(canSumObj.canSum(5, numbers));
        Stack<Integer> addends = new Stack<Integer>();
        Stack<Integer> result = canSumObj.howSum(5, numbers, addends);

        int curr = result.pop();
        int total = curr;
        while (!result.empty()) {
            curr = result.pop();
            total += curr;
        }
        assertEquals(total, 5);
    }

    @Test
    public void testCanSumBig() {
        canSumObj.clearCanSumMemo();
        canSumObj.clearHowSumMemo();
        int[] numbers = {1,2,5,25};
        assertTrue(canSumObj.canSum(100, numbers));
        Stack<Integer> addends = new Stack<Integer>();
        Stack<Integer> result = canSumObj.howSum(100, numbers, addends);

        int curr = result.pop();
        int total = curr;
        while (!result.empty()) {
            curr = result.pop();
            total += curr;
        }
        assertEquals(total, 100);
    }


    @Test
    public void testCannotSum() {
        canSumObj.clearCanSumMemo();
        canSumObj.clearHowSumMemo();
        int[] numbers = {2, 4};
        assertFalse(canSumObj.canSum(5, numbers));
        Stack<Integer> stack = new Stack<Integer>();
        assertNull(canSumObj.howSum(5, numbers, stack));
    }

    @Test
    public void testCannotSumBig() {
        canSumObj.clearCanSumMemo();
        canSumObj.clearHowSumMemo();
        int[] numbers = {3, 9};
        assertFalse(canSumObj.canSum(500, numbers));
        Stack<Integer> stack = new Stack<Integer>();
        assertNull(canSumObj.howSum(500, numbers, stack));
    }

    @Test
    public void testBestSumSmall() {
        canSumObj.clearBestSumMemo();

        int[] numbers = {1,2,3,5};
        ArrayList<Integer> result = canSumObj.bestSum(8, numbers);

        int total = 0;
        for (int item : result) {
            total += item;
        }
        assertEquals(total, 8);
    }

    @Test
    public void testBestSumBig() {
        canSumObj.clearBestSumMemo();

        int[] numbers = {1,2,5,25};
        ArrayList<Integer> result = canSumObj.bestSum(100, numbers);

        int total = 0;
        for (int item : result) {
            total += item;
            assertEquals(item, 25);
        }
        assertEquals(total, 100);
    }
}