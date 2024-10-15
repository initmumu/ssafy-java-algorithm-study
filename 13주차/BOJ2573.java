import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int[][] map;
    public static class Coord{
        public int y;
        public int x;
        public Coord(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int result = 0;
        boolean good = false;
        while(BFS1()){
            result++;
            if(BFS2()){
                good = true;
                break;
            }
        }
        result = good ? result : 0;
        System.out.print(result);
    }

    public static boolean BFS1() {
        boolean[][] visited = new boolean[N][M];
        Queue<Coord> deleteQ = new ArrayDeque<>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(visited[i][j]) continue;
                if(map[i][j] != 0) continue;

                Queue<Coord> q = new ArrayDeque<>();
                q.add(new Coord(i, j));
                visited[i][j] = true;
                while(!q.isEmpty()){
                    int qSize = q.size();

                    for(int t = 0; t < qSize; t++){
                        Coord coord = q.poll();

                        for(int[] dir : dirs){
                            int ny = coord.y + dir[0];
                            int nx = coord.x + dir[1];

                            if(isOutOfMap(ny, nx)) continue;

                            if(map[ny][nx] != 0){
                                deleteQ.add(new Coord(ny, nx));
                            } else {
                                if(visited[ny][nx]) continue;
                                visited[ny][nx] = true;
                                q.add(new Coord(ny, nx));
                            }
                        }
                    }
                }
            }
        }

        if(deleteQ.isEmpty()) return false;

        for(Coord coord : deleteQ){
            if(map[coord.y][coord.x] == 0) continue;
            map[coord.y][coord.x]--;
        }
        return true;
    }

    public static boolean BFS2(){
        boolean[][] visited = new boolean[N][M];
        int islandCount = 0;
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) continue;
                if (map[i][j] == 0) continue;
                islandCount++;
                Queue<Coord> q = new ArrayDeque<>();
                q.add(new Coord(i, j));
                visited[i][j] = true;
                while (!q.isEmpty()) {
                    int qSize = q.size();

                    for (int t = 0; t < qSize; t++) {
                        Coord coord = q.poll();

                        for (int[] dir : dirs) {
                            int ny = coord.y + dir[0];
                            int nx = coord.x + dir[1];

                            if (isOutOfMap(ny, nx) || visited[ny][nx]) continue;

                            if (map[ny][nx] == 0) {
                                continue;
                            } else {
                                visited[ny][nx] = true;
                                q.add(new Coord(ny, nx));
                            }
                        }
                    }
                }
            }
        }

        return islandCount >= 2;
    }

    public static boolean isOutOfMap(int y, int x){
        return y < 0 || y >= N || x < 0 || x >= M;
    }
}
