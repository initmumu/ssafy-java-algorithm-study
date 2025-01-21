import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ19535 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<int[]> edges = new ArrayList<>();
        List<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            edges.add(new int[]{u, v});

            graph[u].add(v);
            graph[v].add(u);
        }

        long countD = 0;
        long countG = 0;

        for (int i = 1; i <= N; i++) {
            long size = graph[i].size();
            if (size >= 3) {
                countG += (long) (size * (size - 1) * (size - 2) / 6);
            }
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            countD += (long) ((graph[u].size() - 1) * (graph[v].size() - 1));
        }

        if (countD > 3 * countG) {
            System.out.println("D");
        } else if (countD < 3 * countG) {
            System.out.println("G");
        } else {
            System.out.println("DUDUDUNGA");
        }
    }
}
