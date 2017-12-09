package Algorithms;

import static Algorithms.tool_readTXTdata.readWeights;

/**
 * Created by biang on 2017/12/1.
 */
public class Assignment11_1_HuffmanCoding {

    public static void main(String args[]) {
        String path = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment11-1_data.txt";
        int[] weights = readWeights(path);
        Integer[] root=LengthOfHuffmanCoding(weights);
        System.out.println(root[1]-1);
        System.out.println(root[2]-1);
    }

    private static Integer[] LengthOfHuffmanCoding(int[] weights) {
        BinaryHeapMAX_nColumns_Integer heap = new BinaryHeapMAX_nColumns_Integer(weights.length, 3, 0);
        for (int i = 0; i < weights.length; i++) {
            heap.insert(new Integer[]{weights[i], 1, 1});
        }

        while (heap.size() > 1) {
            Integer[] min1 = heap.deleteRoot();
            Integer[] min2 = heap.deleteRoot();
            Integer[] sum = new Integer[]{min1[0] + min2[0], Math.max(min1[1], min2[1]) + 1, Math.min(min1[2], min2[2]) + 1};
            heap.insert(sum);
        }
        Integer[] root = heap.deleteRoot();
        return root;
    }

    //Answer1=19
    //Answer2=9
}
