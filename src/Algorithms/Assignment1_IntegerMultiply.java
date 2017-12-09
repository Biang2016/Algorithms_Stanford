package Algorithms;

/**
 * Created by biang on 2017/11/15.
 */
public class Assignment1_IntegerMultiply {

    public static void main(String args[]) {
        String intA = "3141592653589793238462643383279502884197169399375105820974944592";
        String intB = "2718281828459045235360287471352662497757247093699959574966967627";
        String product = multiply(intA, intB);
//        String product = multiply("3141592653589793238462643383279502884197169399375105820974944592", "2718281828459045235360287471352662497757247093699959574966967627");
        System.out.println(product);
    }

    public static String multiply(String intA, String intB) {
        if (intA.length() == 1 || intB.length() == 1) {
            return String.valueOf(Integer.parseInt(intA) * Integer.parseInt(intB));
        }

        String a = intA.substring(0, intA.length() / 2);
        String b = intA.substring(intA.length() / 2);
        String c = intB.substring(0, intB.length() / 2);
        String d = intB.substring(intB.length() / 2);
        String ac = multiply(a, c);
        String bd = multiply(b, d);
        String bc = multiply(b, c);
        String ad = multiply(a, d);
        String ac_zeros = ac + zeros(b.length() + d.length());
        String adbc_zeros = addString(ad, bc) + zeros(d.length());
        String product = addString(addString(ac_zeros, bd), adbc_zeros);
        return product;
    }

    private static String addString(String a, String b) {
        if (a.length() > b.length()) {
            String temp = a;
            a = b;
            b = temp;
        }
        int[] res = new int[b.length() + 1];
        for (int i = 0; i < a.length(); i++) {
            int m = Character.getNumericValue(a.charAt(a.length() - 1 - i)) + Character.getNumericValue(b.charAt(b.length() - 1 - i)) + res[res.length - 1 - i];
            res[res.length - 1 - i] = m % 10;
            res[res.length - 2 - i] = m / 10;
        }
        for (int i = a.length(); i < b.length(); i++) {
            int m = Character.getNumericValue(b.charAt(b.length() - 1 - i)) + res[res.length - 1 - i];
            res[res.length - 1 - i] = m % 10;
            res[res.length - 2 - i] = m / 10;
        }
        StringBuilder sb = new StringBuilder();
        if (res[0] != 0) sb.append(res[0]);
        for (int i = 1; i < res.length; i++) {
            sb.append(res[i]);
        }
        return sb.toString();
    }

    //默认a>b
    private static String substractString(String a, String b) {
        if (a.length() <= 1 || b.length() <= 1) {
            return String.valueOf(Integer.parseInt(a) - Integer.parseInt(b));
        }
        int[] res = new int[a.length()];
        for (int i = 0; i < b.length(); i++) {
            int m = Character.getNumericValue(a.charAt(a.length() - 1 - i)) - Character.getNumericValue(b.charAt(b.length() - 1 - i)) + res[res.length - 1 - i];
            if (m < 0) {
                res[res.length - 2 - i] = -1;
                res[res.length - 1 - i] = m + 10;
            } else res[res.length - 1 - i] = m;
        }
        for (int i = b.length(); i < a.length(); i++) {
            int m = Character.getNumericValue(a.charAt(a.length() - 1 - i)) + res[res.length - 1 - i];
            if (m < 0) {
                res[res.length - 2 - i] = -1;
                res[res.length - 1 - i] = m + 10;
            } else res[res.length - 1 - i] = m;
        }
        StringBuilder sb = new StringBuilder();
        if (res[0] != 0) sb.append(res[0]);
        for (int i = 1; i < res.length; i++) {
            sb.append(res[i]);
        }
        return sb.toString();
    }

    public static String zeros(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append('0');
        }
        return sb.toString();
    }

}
