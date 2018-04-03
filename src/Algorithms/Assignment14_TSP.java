package Algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import static Algorithms.tool_readTXTdata.readPlaces;

/**
 * Created by biang on 2017/12/8.
 */
public class Assignment14_TSP {

    static long time1;

    public static void main(String args[]) {
        String path = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment14_data.txt";
        places = readPlaces(path);
        disMatrix = new double[places.length][places.length];
        precountDisMatrix();
        time1 = System.nanoTime();

        double a = TSP(places);
        System.out.println(a);
    }

    static double[][] places;

    private static double TSP(double[][] places) {
        int nplaces = places.length;
        int sizeS = (int) Math.pow(2, nplaces - 1);

        //减少内存占用的核心思想：每次m++的时候换一张哈希表，如果一直使用一张哈希表的话，即使remove了一些元素，实质上其内部的array是不会remove的，也就是说其size并不会变小
        //但如果换一张哈希表的话，在每次循环结束后，自动回收不用的A_Last这张不用的表，还不用写回收代码
        HashMap<Integer, double[]> A_Last = new HashMap<>();
        HashMap<Integer, double[]> A_New;

        double[] column = new double[nplaces];
        column[0] = 0d;
        A_Last.put(0, column);


        for (int m = 1; m <= nplaces - 1; m++) {
            A_New = new HashMap<>();

            System.out.println("m= " + m);
            long time2 = System.nanoTime();
            System.out.println((time2 - time1) / 1000000 + "ms");

            LinkedList<Integer> mSize_S_Set = nbitOnesInts(m, sizeS);//具有m-1个1的int数，总位数nplaces - 1
            System.out.println("mSize_S_Set: " + mSize_S_Set.size());
            for (Integer s : mSize_S_Set) {
                LinkedList<Integer> onesPlaces = bitOnePlace(s, nplaces - 1);
                for (int jj = 0; jj < onesPlaces.size(); jj++) {
                    int j = onesPlaces.get(jj) + 1;
                    double shortestHop = Double.MAX_VALUE;
                    for (int kk = 0; kk < onesPlaces.size(); kk++) {
                        if (kk == jj) continue;
                        int k = onesPlaces.get(kk) + 1;
                        shortestHop = Math.min(shortestHop, A_Last.get(s - (1 << (nplaces - 1 - j)))[k] + disMatrix[k][j]);
                    }
                    shortestHop = Math.min(shortestHop, A_Last.get(s - (1 << (nplaces - 1 - j)))[0] + disMatrix[0][j]);

                    if (A_New.containsKey(s)) {
                        double[] cc = A_New.get(s);
                        cc[j] = shortestHop;
                        A_New.remove(s);
                        A_New.put(s, cc);
                    } else {
                        double[] cc = new double[nplaces];
                        Arrays.fill(cc, Double.MAX_VALUE);
                        cc[j] = shortestHop;
                        A_New.put(s, cc);
                    }
                }
            }

            A_Last = A_New;
            System.out.println("HashSize: " + A_Last.size());
        }

        double shortestPath = Double.MAX_VALUE;
        for (int j = 1; j < nplaces; j++) {
            shortestPath = Math.min(shortestPath, A_Last.get(sizeS - 1)[j] + disMatrix[j][0]);
        }

        return shortestPath;
    }

    //返回具有n个1的int集
    private static LinkedList<Integer> nbitOnesInts(int n, int sizeS) {
        LinkedList<Integer> res = new LinkedList<>();
        for (int i = 0; i < sizeS; i++) {
            if (Integer.bitCount(i) == n) res.add(i);
        }
        return res;
    }

    //一个int的哪几位为1
    private static LinkedList<Integer> bitOnePlace(int num, int nplaces) {
        LinkedList<Integer> res = new LinkedList<>();
        while (Integer.highestOneBit(num) != 0) {//当num中还有1
            int placeInt = Integer.highestOneBit(num);//每次取出num的第一个1
            num = num - placeInt;//从num中删除这个1
            int place = (nplaces - 1) - Integer.numberOfTrailingZeros(placeInt);//place=1~nplaces-1
            res.add(place);
        }
        return res;
    }

    static double[][] disMatrix;

    private static void precountDisMatrix() {
        int num = disMatrix.length;
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (i == j) continue;
                disMatrix[i][j] = distance(i, j);
            }
        }
    }

    private static double distance(int place_A, int place_B) {
        double dis = Math.sqrt(Math.pow(places[place_A][0] - places[place_B][0], 2) + Math.pow(places[place_A][1] - places[place_B][1], 2));
        return dis;
    }



}
