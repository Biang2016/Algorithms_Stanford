package Algorithms;

import java.util.ArrayList;

import static Algorithms.tool_readTXTdata.readEdges;

/**
 * Created by biang on 2017/11/19.
 */
public class Assignment5_FindSCCs {

    public static void main(String args[]) {
        String path = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment5_data.txt";
        int[][] graphData = readEdges(path);
        countSCCs(graphData);
        for (int i = 0; i < 5; i++) {
            System.out.println(countSize[i]);
        }
    }

    static int t = 0;//给节点标明序号的时间参数，越根部的节点，序号越大
    static Boolean[] isVisited;

    private static void countSCCs(int[][] graphData) {
        int numNode = 875714;
        int[] f = new int[numNode];
        int[] label = new int[numNode];
        isVisited = new Boolean[numNode];
        countSize = new int[5];

        //将label初始化为降序整数
        //将访问布尔表初始化为false
        for (int i = 0; i < numNode; i++) {
            label[i] = numNode - 1 - i;
            isVisited[i] = false;
        }

        //根据边表创建邻接表
        ArrayList<Integer>[] graph = new ArrayList[numNode];
        for (int i = 0; i < numNode; i++) {
            ArrayList<Integer> vertex = new ArrayList<>();
            graph[i] = vertex;
        }

        for (int[] edge : graphData) {
            graph[edge[1] - 1].add(edge[0] - 1);
        }

        //根据边表创建邻接表（反表）
        ArrayList<Integer>[] graph_rev = new ArrayList[numNode];
        for (int i = 0; i < numNode; i++) {
            ArrayList<Integer> vertex = new ArrayList<>();
            graph_rev[i] = vertex;
        }

        for (int[] edge : graphData) {
            graph_rev[edge[0] - 1].add(edge[1] - 1);
        }

        DFS_Loop1(graph_rev, label, f);//read label, and write to f
        DFS_Loop2(graph, f);//read f, and do not write
    }


    private static void DFS_Loop1(ArrayList<Integer>[] graph, int[] label, int[] f) {
        for (int i = 0; i < label.length; i++) {
            if (!isVisited[label[i]]) {
                DFS(graph, label[i], f);
            }
        }
    }

    private static void DFS_Loop2(ArrayList<Integer>[] graph, int[] f) {
        for (int i = 0; i < isVisited.length; i++) isVisited[i] = false;
        for (int i = f.length - 1; i >= 0; i--) {
            if (!isVisited[f[i]]) {
                DFS(graph, f[i], null);
                for (int k = 0; k < 5; k++) {
                    if (currentCount > countSize[k]) {
                        for (int kk = 4; kk > k; kk--) {
                            countSize[kk] = countSize[kk - 1];
                        }
                        countSize[k] = currentCount;
                        break;
                    }
                }
                currentCount = 0;
            }
        }
    }

    static int[] countSize;
    static int currentCount = 0;

    private static void DFS(ArrayList<Integer>[] graph, int startNode, int[] f) {
        isVisited[startNode] = true;
        for (int i = 0; i < graph[startNode].size(); i++) {
            int child = graph[startNode].get(i);
            if (!isVisited[child]) DFS(graph, child, f);
        }
        if (f != null) {
            f[t] = startNode;
            t++;
        } else currentCount++;
    }
}
