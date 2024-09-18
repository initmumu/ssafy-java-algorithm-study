import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {

        int n = read();
        int[] s = new int[n + 1], d = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int max = 0, min = 0x7f7f7f7f;
            s[i] = read();

            for (int j = i; j > 0; j--) {
                max = Math.max(max, s[j]);
                min = Math.min(min, s[j]);
                d[i] = Math.max(d[i], max - min + d[j - 1]);
            }
        }

        System.out.println(d[n]);
    }

    public static int read() throws IOException {
        int c, t = 0;
        while((c=System.in.read()) > 32) t = (t << 3) + (t << 1) + (c & 15);
        return t;
    }
}
