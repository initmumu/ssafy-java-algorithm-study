import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] map;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int DFS(int depth, int x, int y, int prevX, int prevY){
        if(IsOutOfMap(x, y)) return -5000;
        depth++;
        if(depth == 4) return map[y][x];
        int result = 0;

        for(int i = 0; i < 4; i++){
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];
            if(dx == prevX && dy == prevY) continue;
            result = Math.max(DFS(depth, dx, dy, x, y), result);
        }

        return result + map[y][x];
    }

    public static int Bolock(int x, int y){
        int max = 0;
        int temp;
        for(int i = 0; i < 4; i++){
            temp = 0;
            for(int j = 0; j < 4; j++){
                if(j == i) continue;
                int dx = x + dir[j][1];
                int dy = y + dir[j][0];
                if(IsOutOfMap(dx, dy)) temp -= 5000;
                else temp += map[dy][dx];
            }
            max = Math.max(max, temp);
        }
        return max + map[y][x];
    }

    public static boolean IsOutOfMap(int x, int y){
        return x < 0 || x >= M || y < 0 || y >= N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int max = 0;

        map = new int[N][M];

        for(int y = 0; y < N; y++){
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x < M; x++){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for(int y = 0; y < N; y++){
            for(int x = 0; x < M; x++){
                max = Math.max(Math.max(DFS(0, x, y, -10, -10), Bolock(x, y)), max);
            }
        }

        System.out.print(max);
    }
}