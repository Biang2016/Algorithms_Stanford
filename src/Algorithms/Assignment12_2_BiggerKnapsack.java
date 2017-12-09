package Algorithms;

import static Algorithms.tool_readTXTdata.readItems;

/**
 * Created by biang on 2017/12/2.
 */
public class Assignment12_2_BiggerKnapsack {


    public static void main(String args[]) {
        String path = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment12-2_data2.txt";
        int[][] tasks = readItems(path);
        System.out.println(maxValueBiggerKnapsack(tasks));

    }

    private static int maxValueBiggerKnapsack(int[][] tasks) {
        int numItems = tasks.length - 1;
        int s = tasks[tasks.length - 1][0];

        int[] dp_pre = new int[s];
        int[] dp = new int[s];
        for (int i = 1; i <= s; i++) {
            int value = tasks[0][0];
            int itemSize = tasks[0][1];
            if (i < itemSize) {
                dp_pre[i - 1] = 0;
            } else {
                dp_pre[i - 1] = value;
            }
        }

        for (int j = 2; j <= numItems; j++) {
            dp = new int[s];
            for (int i = 1; i <= s; i++) {
                int value = tasks[j - 1][0];
                int itemSize = tasks[j - 1][1];
                if (i < itemSize) {
                    dp[i - 1] = dp_pre[i - 1];
                } else if (i == itemSize) {
                    dp[i - 1] = Math.max(value, dp_pre[i - 1]);
                } else {
                    int subSize = i - itemSize;
                    dp[i - 1] = Math.max(dp_pre[subSize - 1] + value, dp_pre[i - 1]);
                }
            }
            dp_pre = dp;
        }

        return dp[s - 1];
    }
    //Answer=4243395

}
