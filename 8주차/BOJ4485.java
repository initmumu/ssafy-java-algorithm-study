import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int[][] dist;
    static int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static boolean IsOutOfMap(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = 1;
        while(true) {
            N = Integer.parseInt(br.readLine());
            if(N == 0) break;
            map = new int[N][N];
            dist = new int[N][N];
            dist[0][0] = 0;

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }

            PriorityQueue<Integer> pq = new PriorityQueue<>();
            dist[0][0] = map[0][0];
            pq.offer(0);

            while(!pq.isEmpty()) {
                int cur = pq.poll();
                int curY = cur / N;
                int curX = cur % N;
                int nY, nX;
                for(int[] dir : dirs) {
                    nY = curY + dir[0];
                    nX = curX + dir[1];
                    if(IsOutOfMap(nY, nX)) continue;
                    if(dist[curY][curX] + map[nY][nX] < dist[nY][nX]) {
                        dist[nY][nX] = dist[curY][curX] + map[nY][nX];
                        pq.offer(nY * N + nX);
                    }
                }
            }

            sb.append("Problem ").append(t).append(": ").append(dist[N - 1][N - 1]).append('\n');
            t++;
        }
        System.out.println(sb);
    }

}
