package Algorithms;

import java.util.LinkedList;

import static Algorithms.tool_readTXTdata.readAdjacentListWithWeight;

/**
 * Created by biang on 2017/11/21.
 */
public class Assignment6_DijkstraShortestPathHeap {
    public static void main(String args[]) {
        String path = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment6_data.txt";
        LinkedList<Integer[]>[] graph = readAdjacentListWithWeight(path);
        X = new boolean[graph.length];
        for (int i = 0; i < X.length; i++) X[i] = false;
        A = new int[graph.length];
        for (int i = 0; i < X.length; i++) A[i] = 1000000;

        DijkstraShortestPath(graph, 0);
        int[] destNodes = new int[]{7, 37, 59, 82, 99, 115, 133, 165, 188, 197};
        for (int node : destNodes) System.out.println(A[node - 1]);

//        for (int i = 0; i < X.length; i++) System.out.println(i + "," + A[i]);
    }

    static boolean[] X;//Explored nodes bookkeeping
    static int[] A;//Shortest distance array
    static BinaryHeap heap = new BinaryHeap();

    public static void DijkstraShortestPath(LinkedList<Integer[]>[] graph, int startNode) {
        A[startNode] = 0;
        X[startNode] = true;
        heap.insert(new Compare(startNode, A[startNode]));
        renewAdjPath(graph, startNode);//先计算第一个点周围点的A[i]

        while (!heap.isEmpty()) {
            Compare min = heap.deleteRoot();
            X[min.key] = true;
            renewAdjPath(graph, min.key);
        }
    }

    //通过自身A[i]值刷新相邻点（不在X中的）的A[i]值
    private static void renewAdjPath(LinkedList<Integer[]>[] graph, int sourceNode) {
        for (int i = 0; i < graph[sourceNode].size(); i++) {
            int nextNode = graph[sourceNode].get(i)[0];
            if (!X[nextNode]) {
                int AdjPath = A[sourceNode] + graph[sourceNode].get(i)[1];
                if (A[nextNode] > AdjPath) {
                    A[nextNode] = AdjPath;
                    heap.insert(new Compare(nextNode, A[nextNode]));
                }
            }
        }
    }

}

