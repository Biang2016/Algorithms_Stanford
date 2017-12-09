package Algorithms;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;

import static Algorithms.tool_readTXTdata.readNumsArrayLong;

/**
 * Created by biang on 2017/11/22.
 */
public class Assignment8_The2Sum {

    //题目：给定一系列正负数，给出两数之和在[-1000000，1000000]之间的pair数

    public static void main(String args[]) {
        String path = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment8_data.txt";
        long[] nums = readNumsArrayLong(path);
        long time1=System.nanoTime();
        System.out.println(the2Sum(nums));
        long time2=System.nanoTime();
        System.out.println((time2-time1)/1000000+"ms");
    }

    //思路：
    public static int the2Sum(long[] nums) {
        HashSet hash = new HashSet();
        HashSet<Long> tHash = new HashSet();
        int count = 0;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (!hash.contains(nums[i])) {
                hash.add(nums[i]);
            }
        }

        int theLo = 0;
        int theHi = nums.length - 1;
        int T1 = -10000;
        int T2 = 10000;
        while (theHi > theLo) {
            long t = nums[theLo] + nums[theHi];
            if (t > T2) {
                theHi--;
                continue;
            } else if (t < T1) {
                theLo++;
                continue;
            } else {
                int theInlo = theLo;
                while (theHi > theInlo) {
                    long thisSum = nums[theInlo] + nums[theHi];
                    if (thisSum > T2) break;
                    else {
                        if (!tHash.contains(thisSum)) {
                            tHash.add(thisSum);
                            count++;
                        }
                    }
                    theInlo++;
                    if (nums[theInlo] == nums[theHi]) break;
                }
                theHi--;
            }
        }

        return count;
    }
}
