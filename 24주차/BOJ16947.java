import java.io.*;
import java.util.*;

public class BOJ16947 {
    static int N;
    static ArrayList<Integer>[] graph;
    static int[] degree;
    static boolean[] isCycle;
    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        degree = new int[N + 1];
        isCycle = new boolean[N + 1];
        dist = new int[N + 1];
        Arrays.fill(dist, -1);
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
            degree[u]++;
            degree[v]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 1) { 
                queue.offer(i);
            }
        }
        boolean[] removed = new boolean[N + 1];
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            removed[cur] = true;
            for (int next : graph[cur]) {
                if (!removed[next]) {
                    degree[next]--;
                    if (degree[next] == 1) {
                        queue.offer(next);
                    }
                }
            }
        }
        
        for (int i = 1; i <= N; i++) {
            if (!removed[i]) {
                isCycle[i] = true;
            }
        }
        
        queue.clear();
        for (int i = 1; i <= N; i++) {
            if (isCycle[i]) {
                dist[i] = 0; 
                queue.offer(i);
            }
        }
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph[cur]) {
                if (dist[next] == -1) { 
                    dist[next] = dist[cur] + 1;
                    queue.offer(next);
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(dist[i]).append(" ");
        }
        System.out.println(sb.toString());
    }
}
