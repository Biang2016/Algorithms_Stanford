package Algorithms;

import java.util.ArrayList;

import static Algorithms.tool_readTXTdata.readItems;

/**
 * Created by biang on 2017/12/2.
 */
public class Assignment12_1_Knapsack {


    public static void main(String args[]) {
        String path = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment12-1_data.txt";
        int[][] tasks = readItems(path);
        System.out.println(maxValueKnapsack(tasks));

    }

    private static int maxValueKnapsack(int[][] tasks) {
        int numItems = tasks.length - 1;
        int s = tasks[tasks.length - 1][0];

        int[][] dp = new int[s][numItems];
        for (int i = 1; i <= s; i++) {
            int value = tasks[0][0];
            int itemSize = tasks[0][1];
            if (i < itemSize) {
                dp[i - 1][0] = 0;
            } else {
                dp[i - 1][0] = value;
            }
        }

        for (int j = 2; j <= numItems; j++) {
            for (int i = 1; i <= s; i++) {
                int value = tasks[j - 1][0];
                int itemSize = tasks[j - 1][1];
                if (i < itemSize) {
                    dp[i - 1][j - 1] = dp[i - 1][j - 2];
                } else if (i == itemSize) {
                    dp[i - 1][j - 1] = Math.max(value, dp[i - 1][j - 2]);
                } else {
                    int subSize = i - itemSize;
                    dp[i - 1][j - 1] = Math.max(dp[subSize-1][j - 2] + value, dp[i - 1][j - 2]);
                }
            }
        }

        return dp[s - 1][numItems - 1];
    }

    //Answer=2493893
}
