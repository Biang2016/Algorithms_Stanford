package Algorithms;

import java.util.*;

import static Algorithms.tool_readTXTdata.readBitsOfNodes;
import static Algorithms.tool_readTXTdata.readBitsOfNodes2;

/**
 * Created by biang on 2017/11/30.
 */
public class Assignment10_2_MaxHammingKClusters {

    public static void main(String args[]) {
        String path = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment10-2_data.txt";
        ArrayList<Integer> bitsOfNodes = readBitsOfNodes2(path);
        System.out.println(maxKClusters2(bitsOfNodes));
    }

    private static int maxKClusters2(ArrayList<Integer> bitsOfNodes) {
        for (Integer i : bitsOfNodes) {
            if (!hashMap.containsKey(i))
                hashMap.put(i, i);
        }

        int n = bitsOfNodes.size();
        int count = 0;
        for (int i = 0; i < n; i++) {
            int node = bitsOfNodes.get(i);
            if (hashMap.containsKey(node)) {
                hashMap.remove(node);
                abc(node);
                count++;
            }

            while (!q.isEmpty()) {
                int next_node = q.poll();
                System.out.println(q.size());
                abc(next_node);
            }
        }

        return count;
    }

    private static void abc(int sourceNode) {
        int[] friend1D = Friends_1D(sourceNode);
        for (int node : friend1D) {
            if (hashMap.containsKey(node)){
                hashMap.remove(node);
                q.add(node);
            }
        }
        int[] friend2D = Friends_2D(sourceNode);
        for (int node : friend2D) {
            if (hashMap.containsKey(node)){
                hashMap.remove(node);
                q.add(node);
            }
        }
    }

    static HashMap<Integer, Integer> hashMap = new HashMap<>();
    static Queue<Integer> q = new LinkedList<>();
    static int bitNum = 24;

    private static int[] Friends_1D(int node) {
        int friend[] = new int[bitNum];
        for (int i = 0; i < bitNum; i++) {
            friend[i] = 1 << i;
            friend[i] = friend[i] ^ node;
        }
        return friend;
    }

    private static int[] Friends_2D(int node) {
        int friend[] = new int[bitNum * (bitNum - 1) / 2];
        int count = 0;
        for (int i = 0; i < bitNum; i++) {
            for (int j = i + 1; j < bitNum; j++) {
                friend[count] = (1 << i) ^ node ^ (1 << j);
                count++;
            }
        }
        return friend;
    }

    //answer=6118
}
