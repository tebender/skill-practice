package dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class CanConstruct {
    public static void main(String[] args) {
        String[] wordBank = {"bo","rd","ate","t","ska","sk","boar"};
        String[] wordBankTwo = {"e","eee","eeee","eeeee","eeeeeeee"};
        Map<String, Boolean> memo = new HashMap<String, Boolean>();
        System.out.println(canConstruct("", wordBank, memo)); // true
        memo.clear();
        System.out.println(canConstruct("skaboarrd", wordBank, memo)); // true
        memo.clear();
        System.out.println(canConstruct("asdf", wordBank, memo)); // false
        memo.clear();
        System.out.println(canConstruct("skateboard", wordBank, memo)); // false
        memo.clear();
        System.out.println(canConstruct("eeeeeeeeeeeeeeeeeeeeeeeeeeeef", wordBankTwo, memo)); // false
    }

    // can target be constructed by Strings in wordBank?
    public static boolean canConstruct(String target, String[] wordBank, Map<String, Boolean> memo) {
        if (memo.containsKey((target))) {
            return memo.get((target));
        }
        if (target.equals("")) {
            return true;
        }

        for (String word : wordBank) {
            if (target.indexOf(word) == 0) {
                String remainder = target.substring(word.length());
                if (canConstruct(remainder, wordBank, memo)) {
                    memo.put(remainder, true);
                    return true;
                }
            }
        }
        memo.put(target, false);
        return false;
    }
}
