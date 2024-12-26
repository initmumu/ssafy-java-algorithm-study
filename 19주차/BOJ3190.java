import java.io.*;
import java.util.*;

public class Main {
    static int N, K, L;
    static int[][] map;
    static Deque<coord> snake = new ArrayDeque<>();
    static int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static class coord{
        public int x, y;

        public coord(int x, int y){
            this.x = x;
            this.y = y;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N + 2][N + 2];
        for(int i = 0; i < N; i++){
            map[i][0] = 1;
            map[0][i] = 1;
            map[i][N + 1] = 1;
            map[N + 1][i] = 1;
        }

        K = Integer.parseInt(br.readLine());

        int curDir = 0;
        snake.add(new coord(1, 1));
        map[1][1] = 1;

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 2;
        }

        L = Integer.parseInt(br.readLine());

        int time = 0;

        for(int i = 0; i < L; i++){
            st = new StringTokenizer(br.readLine());
            int nextTime = Integer.parseInt(st.nextToken());
            boolean dir = st.nextToken().equals("D");

            while(time < nextTime){
                coord head = snake.peekFirst();
                int nx = head.x + direction[curDir][1];
                int ny = head.y + direction[curDir][0];

                if(map[ny][nx] == 1){
                    System.out.println(time + 1);
                    return;
                }

                if(map[ny][nx] == 2) {
                    map[ny][nx] = 1;
                    snake.addFirst(new coord(nx, ny));
                } else {
                    map[ny][nx] = 1;
                    snake.addFirst(new coord(nx, ny));
                    coord tail = snake.pollLast();
                    map[tail.y][tail.x] = 0;
                }
                time++;
            }

            if(dir){
                curDir = (curDir + 1) % 4;
            } else {
                curDir = (curDir + 3) % 4;
            }
        }

        coord head = snake.peekFirst();
        int nx = head.x;
        int ny = head.y;

        while(true){
            nx += direction[curDir][1];
            ny += direction[curDir][0];

            if(map[ny][nx] == 1){
                System.out.println(time + 1);
                return;
            }
            time++;
        }
    }

}
