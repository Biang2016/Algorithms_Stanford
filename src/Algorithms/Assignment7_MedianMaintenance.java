package Algorithms;

import static Algorithms.tool_readTXTdata.readNumsArray;

/**
 * Created by biang on 2017/11/21.
 */
public class Assignment7_MedianMaintenance {
    public static void main(String args[]) {
        String path = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment7_data.txt";
        int[] data = readNumsArray(path);

        BinaryHeapMAX HL = new BinaryHeapMAX();//extract MAX
        BinaryHeapMIN HH = new BinaryHeapMIN();//extract MIN

        int res = 0;

        for (int i = 0; i < data.length; i++) {
            if (HL.isEmpty() || HL.Root() > data[i])
                HL.insert(data[i]);
            else if (HH.isEmpty() || HH.Root() < data[i]) {
                HH.insert(data[i]);
            } else if (HL.Root() < data[i] && HH.Root() > data[i]) {
                HL.insert(data[i]);
            }

            if (HL.size() - HH.size() == 2) {
                HH.insert(HL.deleteRoot());
            } else if (HH.size() - HL.size() == 1) {
                HL.insert(HH.deleteRoot());
            }
            res += HL.Root();
            res %= 10000;
        }
        System.out.println(res);
    }


}
