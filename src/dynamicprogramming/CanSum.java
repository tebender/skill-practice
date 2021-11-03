package dynamicprogramming;

import java.util.*;

public class CanSum {
    private Map<Integer, Boolean> canSumMemo = new HashMap<Integer, Boolean>();
    private Map<Integer, Stack<Integer>> howSumMemo = new HashMap<Integer, Stack<Integer>>();
    private Map<Integer, ArrayList<Integer>> bestSumMemo = new HashMap<Integer, ArrayList<Integer>>();

    public boolean canSum(int target, int[] numbers) {
        if (canSumMemo.containsKey(target)) {
            return canSumMemo.get(target);
        }
        if (target == 0) {
            return true;
        }
        if (target < 0) {
            return false;
        }

        for (int i = 0; i < numbers.length; i++) {
            int remainder = target - numbers[i];
            if (canSum(remainder, numbers)) {
                canSumMemo.put(remainder, true);
                return true;
            }
        }
        canSumMemo.put(target, false);
        return false;
    }

    public Stack<Integer> howSum(int target, int[] numbers, Stack<Integer> addends) {
        if (howSumMemo.containsKey(target)) {
            return howSumMemo.get(target);
        }
        if (target == 0) {
            return addends;
        }
        if (target < 0) {
            return null;
        }

        for (int number : numbers) {
            int remainder = target - number;
            if (howSum(remainder, numbers, addends) != null) {
                Stack<Integer> completeAddends = addends;
                completeAddends.push(number);
                howSumMemo.put(remainder, completeAddends);
                return addends;
            }
        }
        howSumMemo.put(target, null);
        return null;
    }

    public ArrayList<Integer> bestSum(int target, int[] numbers) {
        // base cases
        if (bestSumMemo.containsKey(target)) {
            return bestSumMemo.get(target);
        }
        if (target == 0) return new ArrayList<Integer>();
        if (target < 0) return null;

        // keep track of shortest sum
        ArrayList<Integer> shortestSum = null;

        // iterate through each number, subtract each
        for (int num : numbers) {
            int remainder = target - num;
            ArrayList<Integer> sum = bestSum(remainder, numbers);
            if (sum != null) {
                // copy over to new list as not to poison list in memo
                ArrayList<Integer> copiedSum = new ArrayList<Integer>();
                for (int item : sum) {
                    copiedSum.add(item);
                }
                copiedSum.add(num);
                // some sort of sum has been found, need to check if it's shorter
                if (shortestSum == null || copiedSum.size() < shortestSum.size()) {
                    shortestSum = copiedSum;
                }
            }
        }
        bestSumMemo.put(target, shortestSum);
        return shortestSum;
    }

    public void clearCanSumMemo() {
        canSumMemo.clear();
    }

    public void clearHowSumMemo() {
        howSumMemo.clear();
    }

    public void clearBestSumMemo() {
        bestSumMemo.clear();
    }
}
