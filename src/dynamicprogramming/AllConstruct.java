package dynamicprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AllConstruct {
    public static void main(String[] args) {
        String[] wordBank = {"purp", "p", "ur", "le", "purpl"};
        String[] wordBankTwo = {"ab", "abc", "cd", "def", "abcd", "ef", "c"};
        String[] wordBankThree = {"e", "ee", "eee", "eeee"};
        Map<String, ArrayList<ArrayList<String>>> memo = new HashMap<String, ArrayList<ArrayList<String>>>();


        ArrayList<ArrayList<String>> constructions = allConstruct("purple", wordBank, memo);
        for (ArrayList<String> list : constructions) {
            System.out.println(list.toString());
        }
        memo.clear();

        constructions = allConstruct("abcdef", wordBankTwo, memo);
        for (ArrayList<String> list : constructions) {
            System.out.println(list.toString());
        }

        memo.clear();

        constructions = allConstruct("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef", wordBankThree, memo);
        for (ArrayList<String> list : constructions) {
            System.out.println(list.toString());
        }
    }



    // return all the string constructions from wordBank that form target
    // still not optimized in worst case
    public static ArrayList<ArrayList<String>> allConstruct(String target, String[] wordBank, Map<String, ArrayList<ArrayList<String>>> memo) {
        // base cases
        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        if (target.equals("")) {
            ArrayList<ArrayList<String>> returnedList = new ArrayList<ArrayList<String>>();
            returnedList.add(new ArrayList<String>());
            return returnedList;
        }

        ArrayList<ArrayList<String>> fullConstructions = new ArrayList<ArrayList<String>>();

        for (String word : wordBank) {
            if (target.indexOf(word) == 0) {
                String remainder = target.substring(word.length());
                ArrayList<ArrayList<String>> remainderConstructions = allConstruct(remainder, wordBank, memo);

                // copy constructions over to fullConstructions
                for (ArrayList<String> remainderList : remainderConstructions) {
                    ArrayList<String> targetList = new ArrayList<String>();
                    for (String remListWord: remainderList) {
                        targetList.add(remListWord);
                    }
                    targetList.add(0, word);
                    fullConstructions.add(targetList);
                }
            }
        }

        memo.put(target, fullConstructions);
        return fullConstructions;
    }
}
