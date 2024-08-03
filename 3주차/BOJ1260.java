import java.io.IOException;
import java.util.ArrayList;

public class Main {
    static ArrayList<Integer>[] map;
    static boolean[] visited;
    static StringBuilder sb;
    static int N;

    public static void DFS(int n) {
        if(visited[n]) return;
        sb.append(n+1).append(' ');
        visited[n] = true;

        for(int next : map[n]) {
            DFS(next);
        }
    }

    public static void BFS(int n) {
        int[] queue = new int[N];
        int left = 0, right = 1, prevRight;
        queue[left] = n;
        visited[n] = true;
        sb.append(n+1).append(' ');

        while(right-left != 0) {
            prevRight = right;
            for(int i = left; i < prevRight; i++) {
                for(int j : map[queue[i]]) {
                    if(visited[j]) continue;

                    visited[j] = true;
                    queue[right++] = j;
                    sb.append(j+1).append(' ');
                }
            }
            left = prevRight;
        }
    }

    public static void main(String[] args) throws IOException {

        N = readInt();
        int M = readInt();
        int V = readInt() - 1;

        map = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            map[i] = new ArrayList<>();
        }

        int n, m;
        for(int i = 0; i < M; i++) {
            n = readInt() - 1;
            m = readInt() - 1;

            map[n].add(m);
            map[m].add(n);
        }

        for(int i = 0; i < N; i++) {
            map[i].sort(Integer::compareTo);
        }

        sb = new StringBuilder();
        visited = new boolean[N];
        DFS(V);
        sb.append('\n');
        visited = new boolean[N];
        BFS(V);
        System.out.println(sb);

    }

    public static int readInt() throws IOException {
        int input;
        int result = 0;
        while((input = System.in.read()) > 47) {
            result = (result<<3) + (result<<1) + (input & 15);
        }
        return result;
    }
}
