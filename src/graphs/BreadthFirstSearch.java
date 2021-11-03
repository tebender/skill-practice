package graphs;

import java.util.*;

public class BreadthFirstSearch {

    public static void main(String[] args) {
        Map<String, String[]> graph = new HashMap<String, String[]>();
        graph.put("a", new String[] {"b", "c"});
        graph.put("b", new String[] {"d"});
        graph.put("c", new String[] {"e"});
        graph.put("d", new String[] {"f"});
        graph.put("e", new String[0]);
        graph.put("f", new String[0]);

        printBreadthFirstSearch(graph, "a"); // abcdef
    }

    public static void printBreadthFirstSearch(Map<String, String[]> graph, String startNode) {
        Queue<String> queue = new LinkedList<String>();
        queue.add(startNode);

        while (queue.peek() != null) {
            String currNode = queue.poll();
            System.out.print(currNode);
            String[] neighbors = graph.get(currNode);
            for (String node : neighbors) {
                queue.add(node);
            }
        }
    }
}
