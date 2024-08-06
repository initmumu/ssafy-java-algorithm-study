import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ5567 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        boolean[] f1 = new boolean[n + 1];
        boolean[] f2 = new boolean[n + 1];

        Stack<int[]> stack = new Stack<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == 1) f1[b] = true;
            else if (b == 1) f1[a] = true;
            else stack.push(new int[] {a, b});
        }

        while (!stack.isEmpty()) {
            int a = stack.peek()[0];
            int b = stack.pop()[1];

            if (f1[a]) f2[b] = true;
            else if (f1[b]) f2[a] = true;
        }

        int count = 0;
        for (int i = 0; i < n+1; i++) {
            if (f1[i] || f2[i]) count++;
        }

        System.out.println(count);
    }
}
