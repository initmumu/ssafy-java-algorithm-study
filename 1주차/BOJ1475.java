import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();

        double[] cnt = new double[9];

        int maxNum = 0;
        for (char c : n.toCharArray()) {
            int curNum = (int)c - 48;
            if (curNum == 9) curNum = 6;
            switch (curNum) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 7:
                case 8: cnt[curNum]++; break;
                case 6: cnt[6] += 0.5; break;
            }
            maxNum = (cnt[maxNum] < cnt[curNum])? curNum : maxNum;
        }
        System.out.println((int)Math.ceil(cnt[maxNum]));

    }
}
