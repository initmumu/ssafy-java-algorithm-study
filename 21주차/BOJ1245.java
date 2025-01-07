import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[][] map;
    public static boolean[][] visited;
    public static int result;

    public static int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

    public static class Node{
        public int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static boolean BFS(int x, int y){
        if(visited[y][x]) return false;
        visited[y][x] = true;
        boolean isBongWooRi = true;
        int curHeight = map[y][x];

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(x, y));

        while(!q.isEmpty()){
            int qSize = q.size();
            for(int i = 0; i < qSize; i++){
                Node node = q.poll();
                for(int j = 0; j < 8; j++){
                    int newX = node.x + dirs[j][0];
                    int newY = node.y + dirs[j][1];
                    if(isOutOfMap(newX, newY)) continue;

                    if(map[newY][newX] > curHeight) isBongWooRi = false;

                    if(!visited[newY][newX] && map[newY][newX] == curHeight){
                        q.add(new Node(newX, newY));
                        visited[newY][newX] = true;
                    }
                }
            }
        }

        return isBongWooRi;
    }

    public static boolean isOutOfMap(int x, int y){
        return x < 0 || x >= M || y < 0 || y >= N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        result = 0;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(BFS(j, i)) result++;
            }
        }
        System.out.println(result);
    }
}
