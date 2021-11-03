package dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class CountConstruct {
    public static void main(String[] args) {
        String[] wordBank = {"a","p","ent","enter","ot","o","t"};
        String[] wordBankTwo = {"e","eee","eeee","eeeee","eeeeeeee"};
        String[] wordBankThree = {"ab","abc","cd","def","abcd"};
        Map<String, Integer> memo = new HashMap<String, Integer>();

        System.out.println((countConstruct("abcdef", wordBankThree, memo))); // 1
        memo.clear();
        System.out.println((countConstruct("eeeeeeeeeeeeeeeeeeeeeeeeeef", wordBankTwo, memo))); // 0
        memo.clear();
        System.out.println((countConstruct("enterapotentpot", wordBank, memo))); // 4

    }

    // how many ways can target be constructed by words in the wordBank?
    public static int countConstruct(String target, String[] wordBank, Map<String, Integer> memo) {
        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        if (target.equals("")) {
            return 1;
        }

        int totalWays = 0;

        for (String word : wordBank) {
            if (target.indexOf(word) == 0) {
                String remainder = target.substring(word.length());
                int ways = countConstruct(remainder, wordBank, memo);
                memo.put(remainder, ways);
                totalWays += ways;
            }
            else {
                memo.put(target, 0);
            }
        }
        memo.put(target, totalWays);
        return totalWays;
    }
}
