package Algorithms;

import static Algorithms.tool_readTXTdata.readNumsArray;

/**
 * Created by biang on 2017/11/16.
 */
public class Assignment3_QuickSort {

    public static void main(String[] args) {

        String path = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment3_data.txt";
        int[] nums = readNumsArray(path);
//        quickSortByFirstPivot(nums);
//        quickSortByLastPivot(nums);
        quickSortByMedianPivot(nums);
        System.out.println(comparisonCount);
    }

    public static void quickSortByFirstPivot(int[] nums) {
        quickSortByFirstPivot(nums, 0, nums.length - 1);
    }

    static int comparisonCount = 0;

    public static void quickSortByFirstPivot(int[] nums, int l, int r) {
        if (l >= r) return;
        comparisonCount += r - l;
        int p = nums[l];
        int i = l + 1;
        for (int j = l + 1; j <= r; j++) {
            if (nums[j] < p) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                i++;
            }
        }
        int temp = nums[i - 1];
        nums[i - 1] = nums[l];
        nums[l] = temp;

        quickSortByFirstPivot(nums, l, i - 2);
        quickSortByFirstPivot(nums, i, r);
    }

    public static void quickSortByLastPivot(int[] nums) {
        quickSortByLastPivot(nums, 0, nums.length - 1);
    }

    public static void quickSortByLastPivot(int[] nums, int l, int r) {
        if (l >= r) return;
        comparisonCount += r - l;

        int t = nums[l];
        nums[l] = nums[r];
        nums[r] = t;

        int p = nums[l];
        int i = l + 1;
        for (int j = l + 1; j <= r; j++) {
            if (nums[j] < p) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                i++;
            }
        }
        int temp = nums[i - 1];
        nums[i - 1] = nums[l];
        nums[l] = temp;

        quickSortByLastPivot(nums, l, i - 2);
        quickSortByLastPivot(nums, i, r);
    }

    public static void quickSortByMedianPivot(int[] nums) {
        quickSortByMedianPivot(nums, 0, nums.length - 1);
    }

    public static void quickSortByMedianPivot(int[] nums, int l, int r) {
        if (l >= r) return;
        comparisonCount += r - l;

        int a = nums[l];
        int b = nums[r];
        int m = nums[(l + r) / 2];

        if ((b - a) * (b - m) < 0) {
            int t = nums[l];
            nums[l] = b;
            nums[r] = t;
        } else if ((m - a) * (m - b) < 0) {
            int t = nums[l];
            nums[l] = m;
            nums[(l + r) / 2] = t;
        } else if ((a - b) * (a - m) < 0) {
            //it's OK now.
        }

        int p = nums[l];
        int i = l + 1;
        for (int j = l + 1; j <= r; j++) {
            if (nums[j] < p) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                i++;
            }
        }
        int temp = nums[i - 1];
        nums[i - 1] = nums[l];
        nums[l] = temp;

        quickSortByMedianPivot(nums, l, i - 2);
        quickSortByMedianPivot(nums, i, r);
    }
}
