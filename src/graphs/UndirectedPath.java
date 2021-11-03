package graphs;

import java.security.KeyPair;
import java.util.*;

public class UndirectedPath {

    public static void main(String[] args) {
        String[][] edges = {
                {"i", "j"},
                {"k", "i"},
                {"m", "k"},
                {"k", "l"},
                {"o", "n"},
        };
        String[][] edgesTwo = {
                {"1", "2"},
                {"4", "6"},
                {"6", "8"},
                {"5", "6"},
                {"6", "7"},
                {"3", "9"},
        };
        String[][] edgesThree = {
                {"0", "8"},
                {"0", "5"},
                {"0", "1"},
                {"5", "8"},
                {"2", "3"},
                {"2", "4"},
                {"3", "4"}
        };
        System.out.println(hasUndirectedPath(edges, "j", "m")); // true
        System.out.println(hasUndirectedPath(edges, "j", "n")); // false

        System.out.println(countConnectedComponents(edges)); // 2
        System.out.println(countConnectedComponents(edgesTwo)); // 3

        System.out.println("Largest component iteratively:");
        System.out.println(largestComponentIteratively(edges)); // 5
        System.out.println(largestComponentIteratively(edgesTwo)); // 5
        System.out.println(largestComponentIteratively(edgesThree)); // 4

        System.out.println("Largest component recursively:");
        System.out.println(largestComponentRecursively(edges)); // 5
        System.out.println(largestComponentRecursively(edgesTwo)); // 5
        System.out.println(largestComponentRecursively(edgesThree)); // 4

        System.out.println("Shortest path:");
        Map<String, List<String>> graph = buildGraphFromEdges(edges);
        System.out.println(shortestPath(graph, "i", "n")); // -1
        System.out.println(shortestPath(graph, "i", "i")); // 0
        System.out.println(shortestPath(graph, "i", "j")); // 1
        System.out.println(shortestPath(graph, "i", "l")); // 2

        System.out.println("Island count:");
        String[][] islandMap = {
                {"w", "l", "w", "w", "w"},
                {"w", "l", "w", "w", "w"},
                {"w", "w", "w", "l", "w"},
                {"w", "w", "l", "l", "w"},
                {"l", "w", "w", "l", "l"},
                {"l", "l", "w", "w", "w"},
        };
        System.out.println(islandCount(islandMap)); // 3

        System.out.println("Minimum island:");
        System.out.println(minimumIsland(islandMap)); // 2
    }

    public static boolean hasUndirectedPath(String[][] edges, String nodeA, String nodeB) {
        Map<String, List<String>> graph = buildGraphFromEdges(edges);

        // set up visited set
        Set<String> visited = new HashSet<String>();

        // depth first search for a path
        Stack<String> stack = new Stack<String>();
        stack.push(nodeA);

        while (!stack.empty()) {
            String curr = stack.pop();
            if (curr.equals(nodeB)) {
                return true;
            }
            visited.add(curr);
            List<String> neighbors = graph.get(curr);
            for (String n : neighbors) {
                if (!visited.contains(n)) {
                    stack.push(n);
                }
            }
        }

        return false;
    }

    public static int countConnectedComponents(String[][] edges) {
        int total = 0;
        Map<String, List<String>> graph = buildGraphFromEdges(edges);
        String[] nodes = graph.keySet().toArray(new String[0]);
        Set<String> visited = new HashSet<String>();
        Queue<String> queue = new LinkedList<String>();

        // breadth first search
        for (int i = 0; i < nodes.length; i++) {
            if (!visited.contains(nodes[i])) {
                total++;
                queue.add(nodes[i]);
                while (queue.peek() != null) {
                    String curr = queue.poll();
                    visited.add(curr);
                    List<String> neighbors = graph.get(curr);
                    for (String neighbor : neighbors) {
                        if (!visited.contains(neighbor)) {
                            queue.add(neighbor);
                        }
                    }
                }
            }
        }

        return total;
    }

    public static int largestComponentIteratively(String[][] edges) {
        int largestTotal = 0;
        Stack<String> stack = new Stack<String>();
        Set<String> visited = new HashSet<String>();
        Map<String, List<String>> graph = buildGraphFromEdges(edges);
        String[] nodes = graph.keySet().toArray(new String[0]);

        for (int i = 0; i < nodes.length; i++) {
            if (!visited.contains(nodes[i])) {
                int componentTotal = 0;
                // begin a depth first search
                stack.push(nodes[i]);
                while (!stack.empty()) {
                    String currNode = stack.pop();
                    if (!visited.contains(currNode)) {
                        componentTotal++;
                        visited.add(currNode);
                        List<String> neighbors = graph.get(currNode);
                        for (String neighbor : neighbors) {
                            stack.push(neighbor);
                        }
                    }
                }
                if (componentTotal > largestTotal) largestTotal = componentTotal;
            }
        }
        return largestTotal;
    }

    public static int largestComponentRecursively(String[][] edges) {
        int largestTotal = 0;
        Map<String, List<String>> graph = buildGraphFromEdges(edges);
        String[] nodes = graph.keySet().toArray(new String[0]);
        Set<String> visited = new HashSet<String>();

        for (int i = 0; i < nodes.length; i++) {
            int componentTotal = countNodes(graph, nodes[i], visited);
            if (componentTotal > largestTotal) largestTotal = componentTotal;
        }


        return largestTotal;
    }

    public static int countNodes(Map<String, List<String>> graph, String node, Set<String> visited) {
        if (visited.contains(node)) {
            return 0;
        }
        visited.add(node);
        List<String> neighbors = graph.get(node);
        int total = 1;

        for (String neighbor : neighbors) {
            total += countNodes(graph, neighbor, visited);
        }
        return total;
    }

    public static int shortestPath(Map<String, List<String>> graph, String start, String end) {
        Queue<List<Object>> queue = new LinkedList<List<Object>>();
        Set<String> visited = new HashSet<String>();
        List<Object> firstPair = new ArrayList<Object>();
        firstPair.add(start);
        firstPair.add(0);
        queue.add(firstPair);

        // execute breadth first search
        while (queue.peek() != null) {
            List<Object> currPair = queue.poll();
            String currNode = (String) currPair.get(0);
            int currDistance = (int) currPair.get(1);
            if (currNode.equals(end)) {
                return currDistance;
            }
            if (!visited.contains(currNode)) {
                visited.add(currNode);
                List<String> neighbors = graph.get(currNode);
                for (String neighbor : neighbors) {
                    List<Object> neighborPair = new ArrayList<Object>();
                    neighborPair.add(neighbor);
                    neighborPair.add(currDistance + 1);
                    queue.add(neighborPair);
                }
            }
        }

        return -1;
    }

    public static int islandCount(String[][] graph) {
        int totalIslands = 0;
        Set<List<Integer>> visited = new HashSet<List<Integer>>();

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if (depthFirstTraverseIsland(graph, i, j, visited) > 0) {
                    totalIslands++;
                }
            }
        }

        return totalIslands;
    }

    public static int depthFirstTraverseIsland(String[][] graph, int r, int c, Set<List<Integer>> visited) {
        List<Integer> coordinate = new ArrayList<Integer>();
        coordinate.add(r);
        coordinate.add(c);
        if (outOfBounds(graph, r, c) || visited.contains(coordinate) || graph[r][c].equals("w")) {
            return 0;
        }
        visited.add(coordinate);
        // add neighbors to stack
        int size = 1;
        size += depthFirstTraverseIsland(graph, r - 1, c, visited);
        size += depthFirstTraverseIsland(graph, r + 1, c, visited);
        size += depthFirstTraverseIsland(graph, r, c - 1, visited);
        size += depthFirstTraverseIsland(graph, r, c + 1, visited);

        return size;
    }

    public static int minimumIsland(String[][] graph) {
        Set<List<Integer>> visited = new HashSet<List<Integer>>();
        int smallestIslandSize = 0;

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                int currIslandSize = depthFirstTraverseIsland(graph, i, j, visited);
                if (smallestIslandSize == 0 || (currIslandSize < smallestIslandSize && currIslandSize != 0)) {
                    smallestIslandSize = currIslandSize;
                }
            }
        }

        return smallestIslandSize;
    }

    private static boolean outOfBounds(String[][] graph, int r, int c) {
        int rowMax = graph.length;
        int columnMax = graph[0].length;
        return r < 0 || c < 0 || r >= rowMax || c >= columnMax;
    }

    public static Map<String, List<String>> buildGraphFromEdges(String[][] edges) {
        Map<String, List<String>> graph = new HashMap<String, List<String>>();
        for (String[] edge : edges) {
            String n1 = edge[0];
            String n2 = edge[1];
            if (!graph.containsKey(n1)) {
                graph.put(n1, new ArrayList<String>());
            }
            if (!graph.containsKey(n2)) {
                graph.put(n2, new ArrayList<String>());
            }
            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }
        return graph;
    }
}
