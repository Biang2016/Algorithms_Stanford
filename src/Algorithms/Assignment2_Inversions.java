package Algorithms;

import static Algorithms.tool_readTXTdata.readNumsArrayInt;

/**
 * Created by biang on 2017/11/15.
 */
public class Assignment2_Inversions {



    public static void main(String[] args) {

        String path = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment2_data.txt";
        int[] nums = readNumsArrayInt(path);
        mergeSort(nums);
//        mergeSort(new int[]{5,4,3,2,1});//test
        System.out.println(InversionCount);
    }


    static long InversionCount = 0;

    public static int[] mergeSort(int[] nums) {
        if (nums.length == 1) return nums;
        int[] nums_left = new int[nums.length / 2];
        int[] nums_right = new int[nums.length - nums_left.length];

        for (int i = 0; i < nums.length; i++) {
            if (i < nums_left.length) nums_left[i] = nums[i];
            else nums_right[i - nums_left.length] = nums[i];
        }

        nums_left = mergeSort(nums_left);
        nums_right = mergeSort(nums_right);
        int[] nums_sorted = mergeSortSplit(nums_left, nums_right);

        return nums_sorted;
    }

    private static int[] mergeSortSplit(int[] nums_left, int[] nums_right) {
        int[] nums_sorted = new int[nums_left.length + nums_right.length];
        int a = 0;
        int b = 0;
        for (int i = 0; i < nums_sorted.length; i++) {
            if (b >= nums_right.length || (a < nums_left.length && nums_left[a] < nums_right[b])) {
                nums_sorted[i] = nums_left[a];
                a++;
            } else {
                InversionCount += nums_left.length - a;
                nums_sorted[i] = nums_right[b];
                b++;
            }
        }
        return nums_sorted;
    }
}