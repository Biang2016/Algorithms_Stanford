package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;

import static Algorithms.tool_readTXTdata.readTaskList;

/**
 * Created by biang on 2017/11/30.
 */
public class Assignment9_1_MinimizeCompletionTime {
    public static void main(String args[]) {
        String path = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment9-1_data.txt";
        ArrayList<int[]> tasks = readTaskList(path);
        System.out.println(MinimizeCompletionTime(tasks));
        System.out.println(MinimizeCompletionTime2(tasks));
    }

    public static long MinimizeCompletionTime(ArrayList<int[]> tasks) {
        heap = new BinaryHeapMAX_nColumns(tasks.size(), 3, 2);
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i)[2] = tasks.get(i)[0] - tasks.get(i)[1];
            heap.insert(tasks.get(i));
        }

        long cc = 0;
        long cs = 0;
        while (!heap.isEmpty()) {
            int[] t = heap.deleteRoot();
            cc += t[1];
            cs += t[0] * cc;
        }

        return cs;
    }

    static BinaryHeapMAX_nColumns heap;
    //Answer1=69119434752
    //Answer1=67311398912

    public static long MinimizeCompletionTime2(ArrayList<int[]> tasks) {
        heap = new BinaryHeapMAX_nColumns(tasks.size(), 3, 2);
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i)[2] = tasks.get(i)[0] * 10000 / tasks.get(i)[1];
            heap.insert(tasks.get(i));
        }

        long cc = 0;
        long cs = 0;
        while (!heap.isEmpty()) {
            int[] t = heap.deleteRoot();
            cc += t[1];
            cs += t[0] * cc;
        }

        return cs;
    }
}