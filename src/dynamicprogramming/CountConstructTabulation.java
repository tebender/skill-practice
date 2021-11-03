package dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class CountConstructTabulation {
    public static void main(String[] args) {
        String[] wordBank = {"a","p","ent","enter","ot","o","t"};
        String[] wordBankTwo = {"e","eee","eeee","eeeee","eeeeeeee"};
        String[] wordBankThree = {"ab","abc","cd","def","abcd"};

        System.out.println((countConstruct("abcdef", wordBankThree))); // 1
        System.out.println((countConstruct("eeeeeeeeeeeeeeeeeeeeeeeeeef", wordBankTwo))); // 0
        System.out.println((countConstruct("enterapotentpot", wordBank))); // 4

    }

    // how many ways can target be constructed by words in the wordBank?
    public static int countConstruct(String target, String[] wordBank) {
        int[] table = new int[target.length() + 1];
        table[0] = 1;

        for (int i = 0; i < table.length; i++) {
            if (table[i] > 0) {
                for (String word : wordBank) {
                    // check if word is a prefix to substring started at position i
                    String subString = target.substring(i);
                    if (subString.indexOf(word) == 0) {
                        table[i + word.length()] += table[i];
                    }
                }
            }
        }

        return table[target.length()];
    }
}
