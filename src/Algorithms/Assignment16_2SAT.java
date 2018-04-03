package Algorithms;

import javax.xml.parsers.FactoryConfigurationError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import static Algorithms.tool_readTXTdata.read2SAT;

/**
 * Created by biang on 2017/12/10.
 */
public class Assignment16_2SAT {

    public static void main(String args[]) {
        String path1 = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment16-1_data.txt";
        String path2 = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment16-2_data.txt";
        String path3 = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment16-3_data.txt";
        String path4 = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment16-4_data.txt";
        String path5 = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment16-5_data.txt";
        String path6 = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment16-6_data.txt";
        int[][] graphData1 = read2SAT(path1);
        int[][] graphData2 = read2SAT(path2);
        int[][] graphData3 = read2SAT(path3);
        int[][] graphData4 = read2SAT(path4);
        int[][] graphData5 = read2SAT(path5);
        int[][] graphData6 = read2SAT(path6);
        System.out.println(isPossible(graphData1));
        System.out.println(isPossible(graphData2));
        System.out.println(isPossible(graphData3));
        System.out.println(isPossible(graphData4));
        System.out.println(isPossible(graphData5));
        System.out.println(isPossible(graphData6));
    }

    static int t = 0;//给节点标明序号的时间参数，越根部的节点，序号越大
    static Boolean[] isVisited;//是否来过该节点
    static HashSet<Integer> chain;//记录当前圈
    static boolean isDetectContradiction;//往圈中添加节点时是否冲突，如果冲突，则检测到矛盾

    private static boolean isPossible(int[][] graphData) {
        int numNode = graphData.length;
        int[] f = new int[2 * numNode];
        int[] label = new int[2 * numNode];
        t = 0;
        isVisited = new Boolean[2 * numNode];
        isDetectContradiction = false;

        //将label初始化为降序整数
        //将访问布尔表初始化为false
        for (int i = 0; i < 2 * numNode; i++) {
            label[i] = 2 * numNode - 1 - i;
            isVisited[i] = false;
        }

        //将变量号      -100~-1,1~100
        //规范为        199~100,0~99, (假设numNode=100)
        //如果两数相差100则表示为同一变量的否
        for (int[] edge : graphData) {
            for (int i = 0; i < edge.length; i++)
                if (edge[i] > 0) edge[i] = edge[i] - 1;
                else edge[i] = -edge[i] + numNode - 1;
        }

        ArrayList<Integer>[] graph = creatGraph(graphData, numNode);
        ArrayList<Integer>[] graph_rev = creatGraph_rev(graphData, numNode);

        DFS_Loop1(numNode, graph_rev, label, f);//read label, and write to f
        for (int i = 0; i < isVisited.length; i++) isVisited[i] = false;//重置
        DFS_Loop2(numNode, graph, f);//read f, and do not write

        return !isDetectContradiction;
    }

    private static void DFS_Loop1(int numNode, ArrayList<Integer>[] graph, int[] label, int[] f) {
        for (int i = 0; i < label.length; i++) {
            if (!isVisited[label[i]]) {
                DFS(numNode, graph, label[i], f);
            }
        }
    }

    private static void DFS_Loop2(int numNode, ArrayList<Integer>[] graph, int[] f) {
        for (int i = f.length - 1; i >= 0; i--) {
            chain = new HashSet<>();//一轮判断结束，废弃上次用的哈希表
            if (!isVisited[f[i]]) {
                DFS(numNode, graph, f[i], null);
            }
        }
    }

    private static void DFS(int numNodes, ArrayList<Integer>[] graph, int startNode, int[] f) {

        isVisited[startNode] = true;
        for (int i = 0; i < graph[startNode].size(); i++) {
            int child = graph[startNode].get(i);
            if (!isVisited[child]) DFS(numNodes, graph, child, f);
            if (isDetectContradiction) return;
        }
        if (f != null) {//f != null表示DFS_Loop1
            f[t] = startNode;
            t++;
        } else {//else表示DFS_Loop2
            //寻找其否命题对应节点号
            int neg_startNode = -1;
            if (startNode >= numNodes)
                neg_startNode = startNode - numNodes;
            else
                neg_startNode = startNode + numNodes;

            //如果有该节点的否命题，则停止递归，本题解为否
            if (chain.contains(neg_startNode)) {
                isDetectContradiction = true;
            } else chain.add(startNode);//否则正常，往哈希表里添加该节点
        }
    }

    private static ArrayList<Integer>[] creatGraph(int[][] graphData, int numNode) {
        //根据边表创建邻接表
        ArrayList<Integer>[] graph = new ArrayList[2 * numNode];
        for (int i = 0; i < graph.length; i++) {
            ArrayList<Integer> vertex = new ArrayList<>();
            graph[i] = vertex;
        }

        for (int[] edge : graphData) {
            if (edge[0] < numNode)
                graph[edge[0] + numNode].add(edge[1]);
            else
                graph[edge[0] - numNode].add(edge[1]);

            if (edge[1] < numNode)
                graph[edge[1] + numNode].add(edge[0]);
            else
                graph[edge[1] - numNode].add(edge[0]);
        }
        return graph;
    }

    private static ArrayList<Integer>[] creatGraph_rev(int[][] graphData, int numNode) {
        //根据边表创建邻接表（反表）
        ArrayList<Integer>[] graph_rev = new ArrayList[2 * numNode];
        for (int i = 0; i < graph_rev.length; i++) {
            ArrayList<Integer> vertex = new ArrayList<>();
            graph_rev[i] = vertex;
        }

        for (int[] edge : graphData) {
            if (edge[1] < numNode)
                graph_rev[edge[0]].add(edge[1] + numNode);
            else
                graph_rev[edge[0]].add(edge[1] - numNode);

            if (edge[0] < numNode)
                graph_rev[edge[1]].add(edge[0] + numNode);
            else
                graph_rev[edge[1]].add(edge[0] - numNode);
        }
        return graph_rev;
    }


}
