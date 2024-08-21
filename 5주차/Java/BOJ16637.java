import java.io.*;

public class Main {
    static int maxValue = Integer.MIN_VALUE;
    static int length;
    static char[] formula;

    public static int calc(int idx, int curValue) {
        int num = (int) formula[idx+2] - 48;
        return calc(idx, curValue, num);
    }

    public static int calc(int idx) {
        int num1 = (int) formula[idx] - 48;
        return calc(idx, num1);
    }

    public static int calc(int idx, int curValue, int gwalho) {
        char operator = formula[idx+1];

        switch (operator) {
            case '+': return curValue + gwalho;
            case '-': return curValue - gwalho;
            default : return curValue * gwalho;
        }
    }

    public static void dfs(int idx, int curValue) {
        if (length-1 <= idx) {
            maxValue = Math.max(maxValue, curValue);
            return;
        }

        if (length-3 == idx) {
            maxValue = Math.max(maxValue, calc(idx, curValue));
            return;
        }

        dfs(idx+2, calc(idx, curValue));
        dfs(idx+4, calc(idx, curValue, calc(idx+2)));
    }

    public static void solution() {
        dfs(0, (int) formula[0] - 48);
        System.out.println(maxValue);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        length = Integer.parseInt(br.readLine());
        formula = br.readLine().toCharArray();
        solution();
    }
}