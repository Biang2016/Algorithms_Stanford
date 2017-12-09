package Algorithms;

import java.util.ArrayList;
import java.util.LinkedList;

import static Algorithms.tool_readTXTdata.readAdjacentList;

/**
 * Created by biang on 2017/11/16.
 */
public class Assignment4_MinCut {
    public static void main(String args[]) {
        String path = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment4_data.txt";

        int minCutCount = Integer.MAX_VALUE;
        for (int i = 0; i < 2000; i++) {
            ArrayList<LinkedList<Integer>> graphData = readAdjacentList(path);
            int newNum = minCut(graphData);
            minCutCount = Math.min(minCutCount, newNum);
        }
        System.out.println("min" + minCutCount);
    }

    //arraylist的索引号+1后表示第i个顶点，每行第一个元素表示当前从属的节点团，如果不等于自身i则表示已经经过合并，自动跳转到合并该节点团的程序
    public static int minCut(ArrayList<LinkedList<Integer>> graphData) {
        //取随机数部分
        LinkedList<Integer> remainNodeIndex = new LinkedList<>();
        int size = graphData.size();
        for (int i = 1; i <= size; i++) {
            remainNodeIndex.add(i);//该链表存储还未合并的节点编号1~200，用于随机采样
        }

        while (remainNodeIndex.size() > 2) {
            //将第一个挑到的合并到第二个
            int firstPickIndex = (int) (Math.floor(Math.random() * remainNodeIndex.size()));
            int firstPick = remainNodeIndex.remove(firstPickIndex);

            int secondPickIndex = (int) (Math.floor(Math.random() * (graphData.get(firstPick - 1).size() - 1)));
            if (graphData.get(firstPick - 1).size() > 1) {
                int secondPick = graphData.get(firstPick - 1).get(secondPickIndex + 1);
                mergeToVertex(graphData, firstPick, secondPick);
            }
        }

        int count = 0;
        int firstGroup = graphData.get(0).get(0);
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            LinkedList<Integer> Row = graphData.get(rowIndex);
            if (Row.get(0) == firstGroup) {
                for (int i = 1; i < Row.size(); i++) {
                    count++;
                }
            }
        }

        return count;

    }

    private static void mergeToVertex(ArrayList<LinkedList<Integer>> graphData, int firstPick, int secondPick) {
        //merge(delete other referrence)
        int size = graphData.size();
        for (int rowIndex = 0; rowIndex < graphData.size(); rowIndex++) {
            LinkedList<Integer> Row = graphData.get(rowIndex);
            if (Row.get(0) == firstPick) {
                Row.set(0, secondPick);
                for (int i = 1; i < Row.size(); i++) {
                    if (Row.get(i) == secondPick) Row.remove(i--);//set self loop to 0
                }
            } else if (Row.get(0) == secondPick) {
                for (int i = 1; i < Row.size(); i++) {
                    if (Row.get(i) == firstPick) Row.remove(i--);//set self loop to 0
                }
            }

            for (int i = 1; i < Row.size(); i++) {
                if (Row.get(i) == firstPick) {
                    Row.set(i, secondPick);
                }
            }
        }
    }
}
