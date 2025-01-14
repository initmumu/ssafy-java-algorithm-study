import java.io.*;
import java.util.*;

public class Main {
    static int N, M, result;
    static int[][] map;
    static List<Node> virus = new ArrayList<>();
    static int[][] dirs = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};

    public static class Node {
        int x, y;
        public Node(int y, int x) {
            this.x = x;
            this.y = y;
        }
    }

    public static void DFS(int cur, int wallCount) {
        if (wallCount == 3) {
            result = Math.max(result, BFS());
            return;
        }

        for (int i = cur; i < N * M; i++) {
            int x = i % M;
            int y = i / M;
            if (map[y][x] != 0) continue;

            map[y][x] = 1;
            DFS(i + 1, wallCount + 1);
            map[y][x] = 0;
        }
    }

    public static boolean isOutOfMap(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= M;
    }

    public static int BFS() {
        Queue<Node> q = new ArrayDeque<>(virus);
        boolean[][] isVisited = new boolean[N][M];
        for (Node v : virus) {
            isVisited[v.y][v.x] = true;
        }

        int count = 0;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            int x = cur.x;
            int y = cur.y;

            for (int[] dir : dirs) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if (isOutOfMap(newY, newX) || isVisited[newY][newX] || map[newY][newX] != 0) continue;
                isVisited[newY][newX] = true;
                q.add(new Node(newY, newX));
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 0 && !isVisited[i][j]){
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        result = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virus.add(new Node(i, j));
                }
            }
        }

        DFS(0, 0);
        System.out.println(result);
    }
}
