package dynamicprogramming;

import java.util.ArrayList;

public class HowSumTabulation {
    public static void main(String[] args) {
        int[] numbers = {2, 3};
        int[] numbersTwo = {2, 4};
        int[] numbersThree = {7, 14};
        int[] numbersFour = {2, 3, 5};
        System.out.println(howSum(7, numbers)); // 3, 2, 2
        System.out.println(howSum(7, numbersTwo)); // null
        System.out.println(howSum(300, numbersThree)); // null
        System.out.println(howSum(8, numbersFour)); // 2, 2, 2, 2
    }

    public static ArrayList<Integer> howSum(int target, int[] numbers) {
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
                        table[i + num] = copiedList;
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
