package bd;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


class Result {

    /*
     * Complete the 'getMinCost' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY val
     *  2. UNWEIGHTED_INTEGER_GRAPH t
     */

    /*
     * For the unweighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] and <name>To[i].
     *
     */
    public static int getMinCost(List<Integer> val, int tNodes, List<Integer> tFrom, List<Integer> tTo) {
        // For all the val, if the val is odd then replace it with 1 else 0
        for (int i = 0; i < val.size(); i++) {
            if (val.get(i) % 2 == 0) {
                val.set(i, 0);
            } else {
                val.set(i, 1);
            }
        }

        int mincost = 0;
        int lastNode = 0;

        // Do a BFS from any node found after lastNode with value 1 to find the first node with value 1
        // Let the cost be the number of edges from one node to another traversed in the BFS, add this cost to mincost and set both the nodes to 0
        // Keep doing this until lastNode is equal to tNodes
        while (lastNode < tNodes) {
            int cost = 0;
            int node = lastNode + 1;
            while (node <= tNodes && val.get(node - 1) == 0) {
                node++;
            }
            if (node > tNodes) {
                break;
            }
            Queue<Integer> queue = new LinkedList<>();
            queue.add(node);
            boolean visited[] = new boolean[tNodes + 1];

            while (!queue.isEmpty()) {
                int currentNode = queue.poll();
                visited[currentNode] = true;
                // Find all the nodes connected to currentNode
                for (int i = 0; i < tFrom.size(); i++) {

                }

            }
        }

        return mincost;
    }
}

public class Solution {
    public static void Qmain(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int valCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> val = IntStream.range(0, valCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        String[] tNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int tNodes = Integer.parseInt(tNodesEdges[0]);
        int tEdges = Integer.parseInt(tNodesEdges[1]);

        List<Integer> tFrom = new ArrayList<>();
        List<Integer> tTo = new ArrayList<>();

        IntStream.range(0, tEdges).forEach(i -> {
            try {
                String[] tFromTo = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                tFrom.add(Integer.parseInt(tFromTo[0]));
                tTo.add(Integer.parseInt(tFromTo[1]));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.getMinCost(val, tNodes, tFrom, tTo);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

