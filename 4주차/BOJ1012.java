import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M;
    static int N;

    public static boolean IsOutOfMap(int x, int y){
        return x < 0 || x >= M || y < 0 || y >= N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for(int test = 0; test < T; test++){
            st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int result = 0;

            boolean[][] map = new boolean[N][M];
            boolean[][] visited = new boolean[N][M];
            Queue<Integer> q = new LinkedList<>();

            for(int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                map[y][x] = true;
            }

            for(int y = 0; y < N; y++){
                for(int x = 0; x < M; x++){
                    if(!map[y][x] || visited[y][x]) continue;
                    visited[y][x] = true;
                    result++;
                    q.add(M * y + x);
                    while(!q.isEmpty()){
                        int n = q.remove();
                        int ny = n / M;
                        int nx = n % M;

                        for(int i = 0; i < 4; i++){
                            if(!IsOutOfMap(nx + dir[i][1], ny + dir[i][0]) && map[ny + dir[i][0]][nx + dir[i][1]] && !visited[ny + dir[i][0]][nx + dir[i][1]]){
                                visited[ny + dir[i][0]][nx + dir[i][1]] = true;
                                q.add(M * (ny + dir[i][0]) + nx + dir[i][1]);
                            }
                        }
                    }
                }
            }

            System.out.println(result);
        }
    }
}
