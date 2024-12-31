import java.io.*;
import java.util.*;

public class BOJ3190 {

    static int N;
    static int[][] board;
    static Map<Integer, Character> directions = new HashMap<>();

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            String[] apple = br.readLine().split(" ");
            int x = Integer.parseInt(apple[0]) - 1;
            int y = Integer.parseInt(apple[1]) - 1;
            board[x][y] = 1;
        }

        int L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            String[] dir = br.readLine().split(" ");
            directions.put(Integer.parseInt(dir[0]), dir[1].charAt(0));
        }

        System.out.println(simulate());
    }

    static int simulate() {
        Deque<int[]> snake = new LinkedList<>();
        snake.add(new int[]{0, 0});
        board[0][0] = 2;

        int time = 0;
        int direction = 0;

        while (true) {
            time++;

            int[] head = snake.peekFirst();
            int nx = head[0] + dx[direction];
            int ny = head[1] + dy[direction];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N || board[nx][ny] == 2) {
                break;
            }

            if (board[nx][ny] == 1) {
                board[nx][ny] = 2;
                snake.addFirst(new int[]{nx, ny});
            }
            else {
                board[nx][ny] = 2;
                snake.addFirst(new int[]{nx, ny});
                int[] tail = snake.pollLast();
                board[tail[0]][tail[1]] = 0;
            }

            if (directions.containsKey(time)) {
                char directionChar = directions.get(time);
                if (directionChar == 'L') {
                    direction = (direction + 3) % 4;
                }
                else if (directionChar == 'D') {
                    direction = (direction + 1) % 4;
                }
            }
        }

        return time;
    }
}
