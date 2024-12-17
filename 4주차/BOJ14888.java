import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14888 {

    public static int N;
    public static int[] graph;

    public static int max_num = -1000000000;
    public static int min_num = 1000000000;

    public static void dfs(int n, int num, int plus, int minus, int mul, int div) {
        if (n == N) {
            if (num >= max_num) {
                max_num = num;
            }
            if (num <= min_num) {
                min_num = num;
            }
            return;
        }

//		덧셈
        if (plus > 0) {
            dfs(n + 1, num + graph[n], plus - 1, minus, mul, div);
        }

//		뺄셈
        if (minus > 0) {
            dfs(n + 1, num - graph[n], plus, minus - 1, mul, div);
        }

//		곱셈
        if (mul > 0) {
            dfs(n + 1, num * graph[n], plus, minus, mul - 1, div);
        }

//		나눗셈
        if (div > 0) {
            dfs(n + 1, num / graph[n], plus, minus, mul, div - 1);

        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            graph[i] = Integer.parseInt(st.nextToken());
        }
        int[] oper = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            oper[i] = Integer.parseInt(st.nextToken());
        }
        dfs(1, graph[0], oper[0], oper[1], oper[2], oper[3]);
        System.out.println(max_num);
        System.out.println(min_num);
    }

}
