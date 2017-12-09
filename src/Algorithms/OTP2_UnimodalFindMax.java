package Algorithms;

/**
 * Created by biang on 2017/11/15.
 */
public class OTP2_UnimodalFindMax {

    //You are a given a unimodal array of n distinct elements,
    // meaning that its entries are in increasing order up until
    // its maximum element, after which its elements are in decreasing
    // order. Give an algorithm to compute the maximum element that runs in O(log n) time.


    public static void main(String args[]) {
        for (int inputSize = 3; inputSize < 1000000; inputSize += 100) {
            int[] data = new int[inputSize];
            int maxNum = inputSize * 100;
            int maxNumIndex = (int) Math.floor(Math.random() * (inputSize - 2) + 1);
            data[0] = 0;
            data[data.length - 1] = 0;

            for (int i = 1; i < inputSize - 1; i++) {
                if (i < maxNumIndex) {
                    data[i] = (int) (Math.random() * (maxNum - data[i - 1] - (maxNumIndex - i))) + data[i - 1] + 1;
                } else if (i == maxNumIndex) data[i] = maxNum;
                else {
                    data[i] = (int) (Math.random() * (data[i - 1] - (inputSize - 1 - i))) + (inputSize - 1 - i);
                }
            }
//            System.out.print("data:");
//            for (int num : data) System.out.print(num + ",");
//            System.out.println();
//            System.out.println("max:"+findMax(data));
            findMax(data);
//            System.out.print("Size:" + inputSize + ", ");
//            System.out.println("cplex=" + complexity);
            System.out.println(Math.log(inputSize)/complexity);
            complexity=0;
        }

    }

    public static int findMax(int[] data) {
        return findMax(data, 0, data.length - 1);
    }


    static int complexity = 0;

    public static int findMax(int[] data, int leftBorder, int rightBorder) {
//        System.out.println(leftBorder + "," + rightBorder);
        if (leftBorder == rightBorder) return data[leftBorder];
        int a = leftBorder;
        int b = rightBorder;
        int c = (a + b) / 2;
        int new_a = (a + c) / 2;
        int new_b = (b + c) / 2;
        complexity += 1;
        if (data[c] <= data[new_a])
            return findMax(data, a, c);
        else if (data[c] <= data[new_b])
            return findMax(data, c, b);
        else
            return findMax(data, new_a, new_b);
    }

}
