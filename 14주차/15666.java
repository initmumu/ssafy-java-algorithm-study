import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[] nums;
    static Deque<int[]> queueng;
    static Deque<Integer> temp;

    public static void DFS(int n, int depth) {
        if (depth == M) {
            if (!queueng.isEmpty()) {
                int[] a = new int[M];
                int idx = 0;
                boolean isMushigi = false;

                for (int i : temp) {
                    a[idx++] = i;
                }

                for (int[] b : queueng) {
                    idx = 0;
                    isMushigi = false;
                    for (int d : a) {
                        if (d != b[idx++])
                            isMushigi = true;
                    }
                    if (!isMushigi)
                        return;
                }
                queueng.add(a);
            } else {
                int[] a = new int[M];
                int idx = 0;

                for (int i : temp) {
                    a[idx++] = i;
                }
                queueng.add(a);
            }

            return;
        }

        for (int i = n; i < N; i++) {
            temp.add(nums[i]);
            DFS(i, depth + 1);
            temp.removeLast();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        queueng = new LinkedList<>();
        nums = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        temp = new LinkedList<>();
        DFS(0, 0);

        StringBuilder sb = new StringBuilder();
        for (int[] a : queueng) {
            for (int i = 0; i < M; i++) {
                sb.append(a[i]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}