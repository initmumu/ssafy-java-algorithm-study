import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

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
        int[][] map = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        while(true) {
            Coord start = new Coord(0, 0);
            Queue<Coord> q = new ArrayDeque<>();
            Queue<Coord> deleteQ = new ArrayDeque<>();
            boolean[][] visited = new boolean[N][M];

            q.add(start);
            visited[0][0] = true;

            while(!q.isEmpty()){
                int qSize = q.size();

                for(int i = 0; i < qSize; i++){
                    Coord coord = q.poll();
                    int y = coord.y;
                    int x = coord.x;

                    for(int[] dir : dirs){
                        int ny = y + dir[0];
                        int nx = x + dir[1];
                        if(isOutOfMap(ny, nx)) continue;

                        if(map[ny][nx] == 1){
                            if(visited[ny][nx]){
                                deleteQ.add(new Coord(ny, nx));
                            } else {
                                visited[ny][nx] = true;
                            }
                        } else {
                            if(visited[ny][nx]) continue;
                            visited[ny][nx] = true;
                            q.add(new Coord(ny, nx));
                        }
                    }
                }
            }

            for(Coord coord : deleteQ){
                map[coord.y][coord.x] = 0;
            }

            result++;
            if(deleteQ.isEmpty()) {
                result--;
                break;
            }
        }

        System.out.print(result);
    }

    public static boolean isOutOfMap(int y, int x){
        return y < 0 || y >= N || x < 0 || x >= M;
    }
}
