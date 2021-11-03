package graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class DepthFirstSearch {
    public static void main(String[] args) {
        Map<String, String[]> graph = new HashMap<String, String[]>();
        graph.put("a", new String[] {"b", "c"});
        graph.put("b", new String[] {"d"});
        graph.put("c", new String[] {"e"});
        graph.put("d", new String[] {"f"});
        graph.put("e", new String[0]);
        graph.put("f", new String[0]);

        printDepthFirstSearch(graph, "a"); // acebdf
        System.out.println();
        printDepthFirstSearchRecursively(graph, "a"); // abdfce
    }

    public static void printDepthFirstSearch(Map<String, String[]> graph, String startNode) {
        Stack<String> stack = new Stack<String>();
        stack.push(startNode);
        while(!stack.empty()) {
            String currNode = stack.pop();
            System.out.print(currNode);
            String[] neighbors = graph.get(currNode);
            for (String node : neighbors) {
                stack.push(node);
            }
        }
    }

    public static void printDepthFirstSearchRecursively(Map<String, String[]> graph, String node) {
        System.out.print(node);
        String[] neighbors = graph.get(node);
        for (String n : neighbors) {
            printDepthFirstSearchRecursively(graph, n);
        }
    }
}
