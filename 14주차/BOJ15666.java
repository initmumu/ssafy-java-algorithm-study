import java.io.*;
import java.util.*;

public class BOJ15666 {

    static int N, M;
    static int[] seq, arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        seq = new int[N];
        arr = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(seq);

        DFS(0, 0);
        System.out.println(sb);
    }

    static void DFS(int start, int depth) {
        if (depth == M) {
            for (int a: arr) {
                sb.append(a).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < N; i++) {
            if (i > start && seq[i] == seq[i - 1]) {
                continue;
            }

            arr[depth] = seq[i];
            DFS(i, depth + 1);
        }
    }
}
