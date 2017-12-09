package Algorithms;

import com.sun.tools.internal.jxc.ap.Const;

import javax.xml.parsers.FactoryConfigurationError;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import static Algorithms.tool_readTXTdata.readFromTXT_edges;

/**
 * Created by biang on 2017/11/19.
 */
public class Assignment5_FindSCCs {

    public static void main(String args[]) {
        String path = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment5_data.txt";
        int numNode = 875714;
        int[][] graphData = readFromTXT_edges(path);
        int[] f = new int[numNode];
        int[] label = new int[numNode];
        e = new Boolean[numNode];
        countSize = new int[5];

        for (int i = 0; i < numNode; i++) {
            label[i] = numNode - 1 - i;
            e[i] = false;
        }

        ArrayList<Integer>[] graph = new ArrayList[numNode];
        for (int i = 0; i < numNode; i++) {
            ArrayList<Integer> vertex = new ArrayList<>();
            graph[i] = vertex;
        }

        for (int[] edge : graphData) {
            graph[edge[1] - 1].add(edge[0] - 1);
        }

        ArrayList<Integer>[] graph_rev = new ArrayList[numNode];
        for (int i = 0; i < numNode; i++) {
            ArrayList<Integer> vertex = new ArrayList<>();
            graph_rev[i] = vertex;
        }

        for (int[] edge : graphData) {
            graph_rev[edge[0] - 1].add(edge[1] - 1);
        }

        DFS_Loop1(numNode, graph_rev, label, f);//read label, and write to f
        DFS_Loop2(numNode, graph, f);//read f, and do not write


        for (int i = 0; i < 5; i++) {
            System.out.println(countSize[i]);
        }
    }

    static int t = 0;
    static Boolean[] e;

    private static void DFS_Loop1(int num_Node, ArrayList<Integer>[] graph, int[] label, int[] f) {
        for (int i = 0; i < label.length; i++) {
            if (!e[label[i]]) {
                DFS(graph, label[i], f);
            }
        }
    }

    private static void DFS_Loop2(int num_Node, ArrayList<Integer>[] graph, int[] f) {
        for (int i = 0; i < e.length; i++) e[i] = false;
        for (int i = f.length - 1; i >= 0; i--) {
            if (!e[f[i]]) {
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
        e[startNode] = true;
        for (int i = 0; i < graph[startNode].size(); i++) {
            int child = graph[startNode].get(i);
            if (!e[child]) DFS(graph, child, f);
        }
        if (f != null) {
            f[t] = startNode;
            t++;
        } else currentCount++;
    }
}
