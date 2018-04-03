package Algorithms;

import static Algorithms.tool_readTXTdata.readPlacesWithIndex;

/**
 * Created by biang on 2017/12/8.
 */
public class Assignment15_TSP_Greedy {

    static long time1;

    public static void main(String args[]) {
        String path = "/Users/biang/Documents/在线课程/Algorithms Stanford/assignment15_data.txt";
        places = readPlacesWithIndex(path);
        double a = TSP_Greedy(places);
        System.out.println(a);
    }

    static double[][] places;

    private static double TSP_Greedy(double[][] places) {
        int nplaces = places.length;
        boolean[] isVisited = new boolean[nplaces];
        double pathLength = 0;
        int lastVisit = 0;
        for (int i = 1; i < nplaces; i++) {
            isVisited[lastVisit]=true;
            int curVisit = 0;
            double nearstDis = Double.MAX_VALUE;
            for (int j = 0; j < nplaces; j++) {
                if (j != lastVisit && !isVisited[j]) {
                    if (nearstDis > distance(j, lastVisit)) {
                        nearstDis = distance(j, lastVisit);
                        curVisit = j;
                    }
                } else continue;
            }
            pathLength += distance(curVisit, lastVisit);
            lastVisit = curVisit;
        }
        pathLength+=distance(lastVisit,0);

        return pathLength;
    }

    private static double distance(int place_A, int place_B) {
        double dis = Math.sqrt(Math.pow(places[place_A][0] - places[place_B][0], 2) + Math.pow(places[place_A][1] - places[place_B][1], 2));
        return dis;
    }

    //Answer=1203406

}
