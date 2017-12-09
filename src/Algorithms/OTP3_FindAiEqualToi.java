package Algorithms;

/**
 * Created by biang on 2017/11/15.
 */
public class OTP3_FindAiEqualToi {

    public static void main(String args[]) {
        System.out.println(findI(new int[]{-10, -8, -6, -5, -3, -1, 1, 3, 4, 6, 7, 9, 12}));
    }

    public static boolean findI(int[] data) {
        return findI(data, 0, data.length - 1);
    }

    static int complexity = 0;

    public static boolean findI(int[] data, int leftBorder, int rightBorder) {
        complexity++;
        int a = leftBorder;
        int b = rightBorder;
        int c = (a + b) / 2;
        if (data[a] == a || data[b] == b) return true;
        else if (data[a] > a || data[b] < b) return false;
        else {
            if (data[c] > c) return findI(data, a, c);
            else if (data[c] > c) return findI(data, c, b);
            else return true;
        }
    }
}
