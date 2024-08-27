import java.io.*;
import java.util.*;


public class Main {
    static class Fish implements Comparable<Fish> {
        public int x;
        public int y;

        public Fish(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Fish o) {
            if (this.x == o.x) return this.y - o.y;
            return this.x - o.x;
        }
    }

    public static int N = 0;
    public static int[][] seaMap;
    public static int[] startPos = new int[2];
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};
    public static int[] myStatus = {2, 0};
    public static int dist = 0;

    public static boolean bfs() {
        Queue<int[]> pq = new LinkedList<>();
        pq.add(new int[] {startPos[0], startPos[1], 0});
        PriorityQueue<Fish> pqFish = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];
        visited[startPos[0]][startPos[1]] = true;
        boolean flag = false;
        int depth = 0;
        while (!pq.isEmpty()) {
            int[] f = pq.poll();
            if (flag && f[2] >= depth) {
                Fish fish = pqFish.poll();
                startPos[0] = fish.x;
                startPos[1] = fish.y;
                if(++myStatus[1] == myStatus[0]) {
                    myStatus[0]++;
                    myStatus[1] = 0;
                }
                seaMap[fish.x][fish.y] = 0;
                dist += depth;
                return true;
            }

            for (int idx = 0; idx < 4; idx++) {
                int nx = f[0] + dx[idx];
                int ny = f[1] + dy[idx];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (visited[nx][ny]) continue;

                if (seaMap[nx][ny] <= myStatus[0]) {
                    if (seaMap[nx][ny] != 0 && seaMap[nx][ny] != myStatus[0]) {
                        flag = true;
                        pqFish.add(new Fish(nx, ny));
                        depth = f[2] + 1;
                    }
                    visited[nx][ny] = true;
                    pq.add(new int[] {nx, ny, f[2]+1});
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        N = readInt();
        seaMap = new int[N][N];

        boolean f = false;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                seaMap[i][j] = readInt();
                if (!f && seaMap[i][j] == 9) {
                    f = true;
                    startPos[0] = i;
                    startPos[1] = j;
                    seaMap[i][j] = 0;
                }
            }
        }

        while(bfs()) {};
        System.out.println(dist);
    }

    public static int readInt() throws IOException {
        int c, t = 0;
        while((c = System.in.read()) > 32) t = (t<<3) + (t<<1) + (c&15);
        if (c == 13) System.in.read();
        return t;
    }
}