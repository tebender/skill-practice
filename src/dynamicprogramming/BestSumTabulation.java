package dynamicprogramming;

import java.util.ArrayList;

public class BestSumTabulation {
    public static void main(String[] args) {
        int[] numbers = {2, 3};
        int[] numbersTwo = {2, 4};
        int[] numbersThree = {7, 14};
        int[] numbersFour = {2, 3, 5};
        int[] numbersFive = {1, 2, 5, 25};
        System.out.println(bestSum(7, numbers)); // 3, 2, 2
        System.out.println(bestSum(7, numbersTwo)); // null
        System.out.println(bestSum(300, numbersThree)); // null
        System.out.println(bestSum(8, numbersFour)); // 3, 5
        System.out.println(bestSum(100, numbersFive)); // 25, 25, 25, 25
    }

    public static ArrayList<Integer> bestSum(int target, int[] numbers) {
        ArrayList<Integer> emptyList = new ArrayList<Integer>();
        ArrayList<Integer>[] table = new ArrayList[target + 1];
        table[0] = emptyList;

        for (int i = 0; i <= target; i++) {
            if (table[i] != null) {
                for (int num : numbers) {
                    if (i + num <= target) {
                        // copy contents of our current index's list, add num, save in table for i + num
                        ArrayList<Integer> copiedList = copyList(table[i]);
                        copiedList.add(num);
                        if (table[i + num] == null || copiedList.size() < table[i + num].size()) {
                            table[i + num] = copiedList;
                        }
                    }
                }
            }
        }

        return table[target];
    }

    private static ArrayList<Integer> copyList(ArrayList<Integer> list) {
        ArrayList<Integer> listCopy = new ArrayList<Integer>();
        for (int item : list) {
            listCopy.add(item);
        }
        return listCopy;
    }
}
