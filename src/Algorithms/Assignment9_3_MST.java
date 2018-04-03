package Algorithms;

import java.util.LinkedList;

import static Algorithms.tool_readTXTdata.readWeightedEdges;

/**
 * Created by biang on 2017/11/30.
 */
public class Assignment9_3_MST {
    public static void main(String args[]) {
        String path = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment9-3_data.txt";
        LinkedList<Integer[]>[] graph = readWeightedEdges(path);
        X = new boolean[graph.length];
        for (int i = 0; i < X.length; i++) X[i] = false;
        System.out.println(PrimsMST(graph, 0));
    }

    private static int PrimsMST(LinkedList<Integer[]>[] graph, int startNode) {
        int totalCost = 0;
        if (X[startNode]) return 0;
        X[startNode] = true;
        for (Integer[] vertex : graph[startNode]) {
            if (!X[vertex[0]])
                heap.insert(new Compare(vertex[1], vertex[0]));
        }
        while (true) {
            Compare newNode = heap.deleteRoot();
            if (newNode != null) {
                if (X[newNode.nodeIndex]) continue;
                else {
                    totalCost += PrimsMST(graph, newNode.nodeIndex) + newNode.key;
                }
            }
            else break;
        }

        return totalCost;
    }

    static boolean[] X;//Explored nodes bookkeeping
    static BinaryHeap heap = new BinaryHeap();


}
//answer=-3612829
