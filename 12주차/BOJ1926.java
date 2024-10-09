import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static class Coord{
        public int y;
        public int x;
        public Coord() {};
        public Coord(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<Coord> queue = new ArrayDeque<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 2][M + 2];

        for(int y = 1; y <= N; y++) {
            st = new StringTokenizer(br.readLine());
            for(int x = 1; x <= M; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;
        int result = 0;
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= M; j++){
                if(map[i][j] == 0) continue;
                result++;
                Coord start = new Coord(i, j);
                queue.add(start);
                map[i][j] = 0;
                int count = 1;
                while(!queue.isEmpty()) {
                    int qSize = queue.size();

                    for(int t = 0; t < qSize; t++) {
                        Coord n = queue.poll();
                        for(int[] dir : dirs) {
                            int ny = n.y + dir[0];
                            int nx = n.x + dir[1];

                            if(map[ny][nx] == 0) continue;
                            map[ny][nx] = 0;
                            count++;
                            Coord nCoord = new Coord(ny, nx);
                            queue.add(nCoord);
                        }
                    }
                }

                max = Math.max(max, count);
            }
        }

        System.out.println(result);
        System.out.print(max);
    }

}
