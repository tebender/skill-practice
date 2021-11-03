package dynamicprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllConstructTabulation {
    public static void main(String[] args) {
        String[] wordBank = {"purp", "p", "ur", "le", "purpl"};
        String[] wordBankTwo = {"ab", "abc", "cd", "def", "abcd", "ef", "c"};
        String[] wordBankThree = {"e", "ee", "eee", "eeee"};


        List<List<String>> constructions = allConstruct("purple", wordBank);
        for (List<String> list : constructions) {
            System.out.println(list.toString());
        }

        constructions = allConstruct("abcdef", wordBankTwo);
        for (List<String> list : constructions) {
            System.out.println(list.toString());
        }

        // too big! method is still exponential
//        constructions = allConstruct("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef", wordBankThree);
//        for (List<String> list : constructions) {
//            System.out.println(list.toString());
//        }
    }

    public static List<List<String>> allConstruct(String target, String[] wordBank) {
        List<List<String>>[] table = new ArrayList[target.length() + 1];
        List<List<String>> initialList = new ArrayList<List<String>>();
        initialList.add(new ArrayList<String>());

        table[0] = initialList;
        for (int i = 1; i < table.length; i++) {
            table[i] = new ArrayList<List<String>>();
        }

        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (String word : wordBank) {
                    // check if word is a prefix of substring starting at position i of target
                    String subString = target.substring(i);
                    if (subString.indexOf(word) == 0) {
                        for (List<String> list : table[i]) {
                            List<String> copy = copyList(list);
                            copy.add(word);
                            table[i + word.length()].add(copy);
                        }
                    }
                }
            }
        }

        return table[target.length()];
    }

    private static List<String> copyList(List<String> list) {
        List<String> listCopy = new ArrayList<String>();
        for (String item : list) {
            listCopy.add(item);
        }

        return listCopy;
    }
}
