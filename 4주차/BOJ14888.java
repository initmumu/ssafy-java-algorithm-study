import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14888 {
    static int N;
    static int[] num;
    static int[] op = new int[4];
    static int min = 1_000_000_000;
    static int max = -1_000_000_000;

    public static void DFS(int n, int depth) {
        if (depth == N) {
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        else {
            for (int i = 0; i < 4; i++) {
                if (op[i] > 0) {
                    op[i]--;
                    switch (i) {
                        case 0:
                            DFS(n + num[depth], depth + 1);
                            break;
                        case 1:
                            DFS(n - num[depth], depth + 1);
                            break;
                        case 2:
                            DFS(n * num[depth], depth + 1);
                            break;
                        case 3:
                            DFS(n / num[depth], depth + 1);
                            break;
                    }
                    op[i]++;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        num = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }

        DFS(num[0], 1);
        System.out.println(max);
        System.out.println(min);
    }
}
