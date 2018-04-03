package Algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by biang on 2017/11/16.
 */
public class tool_readTXTdata {

    //将文件中有内容的行提取成list
    private static List readUsefulRows(String path) {
        File file = new File(path);
        List list = new ArrayList();
        try {
            BufferedReader bw = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = bw.readLine()) != null) {
                list.add(line);
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < list.size(); i++) {
            String s = (String) list.get(i);
            if (s.equals("")) {
                list.remove(i);
                i--;
            }
        }
        return list;
    }

    //读取int数字
    //原文件格式为：一行一个int数字
    public static int[] readNumsArrayInt(String path) {
        List list = readUsefulRows(path);

        int[] nums = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            String s = (String) list.get(i);
            nums[i] = Integer.parseInt(s);
        }
        return nums;
    }

    //读取long数字
    //原文件格式为：一行一个long数字
    public static long[] readNumsArrayLong(String path) {
        List list = readUsefulRows(path);

        long[] nums = new long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            String s = (String) list.get(i);
            nums[i] = Long.parseLong(s);
        }
        return nums;
    }

    //读取邻接表
    //源文件格式为，顶点号    邻接顶点1   邻接顶点2   ...
    //            顶点号    邻接顶点1   邻接顶点2   ...
    //            ...
    public static ArrayList<LinkedList<Integer>> readAdjacentList(String path) {
        List list = readUsefulRows(path);

        int[] nums = new int[list.size()];
        ArrayList<LinkedList<Integer>> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            LinkedList<Integer> row = new LinkedList<>();
            String s = (String) list.get(i);
            if (s.equals("")) continue;
            String[] arr = s.split("\\s+");
            for (String sss : arr) {
                row.add(Integer.parseInt(sss));
            }
            res.add(row);
        }
        return res;
    }

    //读取加权邻接表
    //源文件格式为：顶点号
    //            邻接顶点1，权重
    //            邻接顶点2，权重
    //            ...
    public static LinkedList<Integer[]>[] readWeightedAdjacentList(String path) {
        List list = readUsefulRows(path);

        LinkedList<Integer[]>[] res = new LinkedList[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = new LinkedList<>();
            String s = (String) list.get(i);
            String[] arr = s.split("\\s+");
            for (int j = 1; j < arr.length; j++) {
                Integer[] edge = new Integer[2];
                String e = arr[j];
                String[] ew_pair = e.split(",");
                edge[0] = Integer.parseInt(ew_pair[0]) - 1;
                edge[1] = Integer.parseInt(ew_pair[1]);
                res[i].add(edge);
            }
        }

        return res;
    }

    //读取边表
    //源文件格式为：起点 终点
    //            起点 终点
    //            ...
    public static int[][] readEdges(String path) {
        List list = readUsefulRows(path);

        int[][] res = new int[list.size()][2];
        int countRow = 0;
        for (int i = 0; i < list.size(); i++) {
            String s = (String) list.get(i);
            String[] arr = s.split("\\s+");
            for (int j = 0; j < arr.length; j++) {
                res[countRow][j] = Integer.parseInt(arr[j]);
            }
            countRow++;
        }

        return res;
    }

    //读取加权边表
    //源文件格式为：顶点数  边数
    //            起点  终点  cost
    //            起点  终点  cost
    //            ...
    public static LinkedList<Integer[]>[] readWeightedEdges(String path) {
        List list = readUsefulRows(path);

        String s = (String) list.get(0);
        String[] arr = s.split("\\s+");
        int nodeNum = Integer.parseInt(arr[0]);

        //由边表创建顶点邻接表
        LinkedList<Integer[]>[] res = new LinkedList[nodeNum];
        for (int i = 0; i < res.length; i++) res[i] = new LinkedList<Integer[]>();
        for (int i = 1; i < list.size(); i++) {
            String ss = (String) list.get(i);
            String[] array = ss.split("\\s+");

            Integer[] startPointWithCost = new Integer[2];
            Integer[] endPointWithCost = new Integer[2];
            startPointWithCost[0] = Integer.parseInt(array[0]) - 1;
            startPointWithCost[1] = Integer.parseInt(array[2]);
            endPointWithCost[0] = Integer.parseInt(array[1]) - 1;
            endPointWithCost[1] = Integer.parseInt(array[2]);
            res[startPointWithCost[0]].add(endPointWithCost);
            res[endPointWithCost[0]].add(startPointWithCost);
        }

        return res;
    }

    //读取加权边表，且将顶点数附在数组最后
    //源文件格式为：顶点数
    //            起点  终点  cost
    //            起点  终点  cost
    //            ...
    public static ArrayList<Integer[]> readWeightedEdges2(String path) {
        List list = readUsefulRows(path);

        String s = (String) list.get(0);
        ArrayList<Integer[]> res = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            String ss = (String) list.get(i);
            String[] array = ss.split("\\s+");
            res.add(new Integer[]{Integer.parseInt(array[0]) - 1, Integer.parseInt(array[1]) - 1, Integer.parseInt(array[2])});
        }

        res.add(new Integer[]{Integer.parseInt(s)});
        return res;
    }

    //读取加权边表，且将顶点数及边数附在数组最后
    //源文件格式为：顶点数  边数
    //            起点  终点  cost
    public static ArrayList<Integer[]> readEdgesWithCost3(String path) {
        List list = readUsefulRows(path);

        String s = (String) list.get(0);
        String[] arr = s.split("\\s+");
        ArrayList<Integer[]> res = new ArrayList<>();

        for (int i = 1; i < list.size(); i++) {
            String ss = (String) list.get(i);
            String[] array = ss.split("\\s+");
            res.add(new Integer[]{Integer.parseInt(array[0]) - 1, Integer.parseInt(array[1]) - 1, Integer.parseInt(array[2])});
        }

        res.add(new Integer[]{Integer.parseInt(arr[0]), Integer.parseInt(arr[1])});
        return res;
    }

    //读取任务表
    //源文件格式为：任务数
    //            任务权重  任务耗时
    //            任务权重  任务耗时
    //            ...
    public static ArrayList<int[]> readTaskList(String path) {
        List list = readUsefulRows(path);

        ArrayList<int[]> res = new ArrayList<int[]>();
        for (int i = 1; i < list.size(); i++) {
            int[] task = new int[3];
            String s = (String) list.get(i);
            String[] arr = s.split("\\s+");
            for (int j = 1; j < arr.length; j++) {
                task[0] = Integer.parseInt(arr[0]);
                task[1] = Integer.parseInt(arr[1]);
                res.add(task);
            }
        }

        return res;
    }

    //源文件格式为：顶点数  bit数
    //            bits
    public static LinkedList<Integer> readBitsOfNodes(String path) {
        List list = readUsefulRows(path);

        LinkedList<Integer> res = new LinkedList<>();
        for (int i = 1; i < list.size(); i++) {
            String ss = (String) list.get(i);
            ss = ss.replaceAll(" ", "");
            res.add(Integer.parseInt(ss, 2));
        }

        return res;
    }

    //源文件格式为：顶点数  bit数
    //            bits
    public static ArrayList<Integer> readBitsOfNodes2(String path) {
        List list = readUsefulRows(path);

        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            String ss = (String) list.get(i);
            ss = ss.replaceAll(" ", "");
            res.add(Integer.parseInt(ss, 2));
        }

        return res;
    }

    //原文件格式为：个数
    //            权值1
    //            权值2
    //            ...
    public static int[] readWeights(String path) {
        List list = readUsefulRows(path);

        list.remove(0);
        int[] nums = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            String s = (String) list.get(i);
            nums[i] = Integer.parseInt(s);
        }
        return nums;
    }

    //原文件格式为：背包大小  物品个数
    //            物品价值  物品大小
    //            物品价值  物品大小
    //            ...
    public static int[][] readItems(String path) {
        List list = readUsefulRows(path);

        String s0 = (String) list.get(0);
        String[] arr = s0.split("\\s+");
        int[][] items = new int[list.size()][2];

        for (int i = 1; i < list.size(); i++) {
            String s = (String) list.get(i);
            String[] array = s.split("\\s+");
            items[i - 1][0] = Integer.parseInt(array[0]);
            items[i - 1][1] = Integer.parseInt(array[1]);
        }
        items[list.size() - 1][0] = Integer.parseInt(arr[0]);
        items[list.size() - 1][1] = Integer.parseInt(arr[1]);
        return items;
    }

    //原文件格式为：地点个数
    //            地点x坐标   地点y坐标
    //            地点x坐标   地点y坐标
    //            ...
    public static double[][] readPlaces(String path) {
        List list = readUsefulRows(path);

        String s0 = (String) list.get(0);
        int nums = Integer.parseInt(s0);

        double[][] places = new double[nums][2];
        for (int i = 1; i < list.size(); i++) {
            String s = (String) list.get(i);
            String[] array = s.split("\\s+");
            places[i - 1][0] = Double.parseDouble(array[0]);
            places[i - 1][1] = Double.parseDouble(array[1]);
        }
        return places;
    }

    //原文件格式为：地点个数
    //            地点编号   地点x坐标   地点y坐标
    //            地点编号   地点x坐标   地点y坐标
    //            ...
    public static double[][] readPlacesWithIndex(String path) {
        List list = readUsefulRows(path);

        String s0 = (String) list.get(0);
        int nums = Integer.parseInt(s0);

        double[][] places = new double[nums][2];
        for (int i = 1; i < list.size(); i++) {
            String s = (String) list.get(i);
            String[] array = s.split("\\s+");
            places[i - 1][0] = Double.parseDouble(array[1]);
            places[i - 1][1] = Double.parseDouble(array[2]);
        }
        return places;
    }

    //读取2SAT变量表
    //原文件格式为：变量个数(同时也等于Clause个数)
    //            变量1   变量2
    //            变量1   变量2
    //            ...
    public static int[][] read2SAT(String path) {
        List list = readUsefulRows(path);

        String s0 = (String) list.get(0);
        int nums = Integer.parseInt(s0);
        int[][] vars = new int[nums][2];

        for (int i = 1; i < list.size(); i++) {
            String s = (String) list.get(i);
            String[] array = s.split("\\s+");
            Integer[] var = new Integer[2];
            vars[i - 1][0] = Integer.parseInt(array[0]);
            vars[i - 1][1] = Integer.parseInt(array[1]);
        }
        return vars;
    }
}
