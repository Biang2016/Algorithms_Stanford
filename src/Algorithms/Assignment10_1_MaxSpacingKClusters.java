package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;

import static Algorithms.tool_readTXTdata.readWeightedEdges2;

/**
 * Created by biang on 2017/11/30.
 */
public class Assignment10_1_MaxSpacingKClusters {
    public static void main(String args[]) {
        String path = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment10-1_data.txt";
        ArrayList<Integer[]> edges = readWeightedEdges2(path);
        int n = edges.get(edges.size() - 1)[0];
        edges.remove(edges.size() - 1);
        int k = 4;
        int[] father = KClusters(edges, n, k);
        System.out.println(minSpace(edges, father, k));
    }

    private static int[] KClusters(ArrayList<Integer[]> edges, int n, int k) {
        BinaryHeapMAX_nColumns_Integer heap = new BinaryHeapMAX_nColumns_Integer(edges.size(), 3, 2);

        for (int i = 0; i < edges.size(); i++) {
            heap.insert(edges.get(i));
        }

        int[] father = new int[n];
        int[] counts = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
            counts[i] = 1;
        }

        while (true) {
            Integer[] shortestE = heap.deleteRoot();
            if (shortestE == null)
                break;
            while (father[shortestE[0]] != shortestE[0]) {
                shortestE[0] = father[shortestE[0]];
            }
            while (father[shortestE[1]] != shortestE[1]) {
                shortestE[1] = father[shortestE[1]];
            }

            if (shortestE[0].equals(shortestE[1]))
                continue;
            else if (counts[shortestE[0]] > counts[shortestE[1]]) {
                father[shortestE[1]] = shortestE[0];
                counts[shortestE[0]] += counts[shortestE[1]];
                counts[shortestE[1]] = 0;
            } else {
                father[shortestE[0]] = shortestE[1];
                counts[shortestE[1]] += counts[shortestE[0]];
                counts[shortestE[0]] = 0;
            }

            int kk = 0;
            for (int i = 0; i < n; i++) {
                if (counts[i] > 0) kk++;
            }
            if (kk == k)
                break;
        }
        return father;
    }

    private static int minSpace(ArrayList<Integer[]> edges, int[] father, int k) {
        int[] clusterIndex = new int[k];
        Arrays.fill(clusterIndex, -1);
        int[][] kSpace = new int[k][k];
        for (int[] row : kSpace)
            Arrays.fill(row, Integer.MAX_VALUE);
        for (Integer[] edge : edges) {
            while (father[edge[0]] != edge[0]) {
                edge[0] = father[edge[0]];
            }
            while (father[edge[1]] != edge[1]) {
                edge[1] = father[edge[1]];
            }

            int startCluster = -1;
            for (int i = 0; i < clusterIndex.length; i++) {
                if (clusterIndex[i] == -1) {
                    clusterIndex[i] = edge[0];
                    startCluster = i;
                    break;
                } else if (edge[0] == clusterIndex[i]) {
                    startCluster = i;
                    break;
                }
            }

            int endCluster = -1;
            for (int i = 0; i < clusterIndex.length; i++) {
                if (clusterIndex[i] == -1) {
                    clusterIndex[i] = edge[1];
                    endCluster = i;
                    break;
                } else if (edge[1] == clusterIndex[i]) {
                    endCluster = i;
                    break;
                }
            }

            kSpace[startCluster][endCluster] = Math.min(kSpace[startCluster][endCluster], edge[2]);
            kSpace[endCluster][startCluster] = kSpace[startCluster][endCluster];
        }

        int minSpace = Integer.MAX_VALUE;
        for (int i = 0; i < k - 1; i++) {
            for (int j = i + 1; j < k; j++) {
                minSpace = Math.min(minSpace, kSpace[i][j]);
            }
        }
        return minSpace;
    }

}
