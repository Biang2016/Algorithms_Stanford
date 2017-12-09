package Algorithms;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import static Algorithms.tool_readTXTdata.readEdgesWithCost3;

/**
 * Created by biang on 2017/12/3.
 */
public class Assignment13_1_APSP {


    public static void main(String args[]) {
//        String testPath = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment13_data2.txt";
//        String path1 = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment13-1_data.txt";
//        String path2 = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment13-2_data.txt";
//        String path3 = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment13-3_data.txt";
        String path_l = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment13_data_large.txt";
//        ArrayList<Integer[]> graph = readEdgesWithCost3(testPath);
//        ArrayList<Integer[]> graph1 = readEdgesWithCost3(path1);
//        ArrayList<Integer[]> graph2 = readEdgesWithCost3(path2);
//        ArrayList<Integer[]> graph3 = readEdgesWithCost3(path3);
        ArrayList<Integer[]> graph_l = readEdgesWithCost3(path_l);

//        System.out.println(FloydWarshall(graph));
//        System.out.println(FloydWarshall(graph1));
//        System.out.println(FloydWarshall(graph2));
//        System.out.println(FloydWarshall(graph3));
        System.out.println(FloydWarshall(graph_l));
    }

    private static int FloydWarshall(ArrayList<Integer[]> graph) {
        Integer[] last = graph.get(graph.size() - 1);
        int numV = last[0];
        int numE = last[1];
        graph.remove(graph.size() - 1);

        //read edges
        int A[][] = new int[numV][numV];
        for (int i = 0; i < numV; i++) {
            Arrays.fill(A[i], Integer.MAX_VALUE);
            A[i][i] = 0;
        }
        for (int i = 0; i < graph.size(); i++) {
            A[graph.get(i)[0]][graph.get(i)[1]] = graph.get(i)[2];
        }

        //Core code
        for (int k = 0; k < numV; k++)
            for (int i = 0; i < numV; i++)
                for (int j = 0; j < numV; j++)
                    if (A[i][k] != Integer.MAX_VALUE && A[k][j] != Integer.MAX_VALUE) {
                        if (A[i][k] + A[k][j] < A[i][j]) {
                            A[i][j] = A[i][k] + A[k][j];
//                            System.out.println("k=" + k + ", j=" + j + ", i=" + i);
//                            printA(A);
                        }
                    }

        //Check negative cycle
        for (int i = 0; i < numV; i++) {
            if (A[i][i] < 0) {
                System.out.println(i);
                System.out.println("neg!");
                return Integer.MIN_VALUE;
            }
        }

        //Pick shortest shortest path
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < numV; i++)
            for (int j = 0; j < numV; j++)
                minLength = Math.min(minLength, A[i][j]);

        return minLength;

    }

    private static void printA(int[][] A) {
        for (int ii = 0; ii < A.length; ii++) {
            for (int jj = 0; jj < A[0].length; jj++) {
                int len = 0;
                if (A[ii][jj] == Integer.MAX_VALUE) {
                    System.out.print("f");
                    len = 1;
                } else {
                    System.out.print(A[ii][jj]);
                    len = Integer.toString(A[ii][jj]).length();
                }
                len = 8 - len;
                for (int pr = 0; pr < len; pr++)
                    System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
