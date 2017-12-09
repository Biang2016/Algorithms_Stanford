package Algorithms;

import static Algorithms.tool_readTXTdata.readWeights;

/**
 * Created by biang on 2017/12/1.
 */
public class Assignment11_1_MaxWeightIndependentWeight {

    public static void main(String args[]) {
        String path = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment11-3_data.txt";
        int[] weights = readWeights(path);
        int[] checkNodes = new int[]{1, 2, 3, 4, 17, 117, 517, 997};
        String isExist = MaxWeightIndependentWeight(weights, checkNodes);
        System.out.println(isExist);
    }

    private static String MaxWeightIndependentWeight(int[] weights, int[] checkNodes) {
        int[] A = new int[weights.length];
        A[0] = weights[0];
        A[1] = Math.max(weights[0], weights[1]);
        for (int i = 2; i < weights.length; i++) {
            A[i] = Math.max(A[i - 1], A[i - 2] + weights[i]);
        }

        int Sum = A[weights.length - 1];
        int index = weights.length - 1;
        boolean[] exist = new boolean[weights.length];
        while (Sum != 0) {
            if(index==0){
                exist[index]=true;
                break;
            }
            if (A[index] == A[index - 1]) {
                exist[index] = false;
                index--;
            } else {
                Sum -= weights[index];
                exist[index] = true;
                for (int i = index; ; i--) {
                    if (Sum == A[i]) {
                        index = i;
                        break;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int checkNode : checkNodes) {
            if (exist.length > checkNode && exist[checkNode - 1]) {
                sb.append(1);
            } else sb.append(0);
        }
        return sb.toString();
    }

    //answer=10100110

}
