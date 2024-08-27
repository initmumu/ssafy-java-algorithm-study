import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dirs = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new ArrayDeque<>();
        map = new int[N + 2][N + 2];
        visited = new boolean[N + 2][N + 2];

        for(int i = 0; i < N + 2; i++) {
            map[0][i] = 1000;
            map[N + 1][i] = 1000;
            map[i][0] = 1000;
            map[i][N + 1] = 1000;
        }

        int sharkPos = 0;
        int sharkSize = 2;

        for(int y = 1; y <= N; y++) {
            st = new StringTokenizer(br.readLine());

            for(int x = 1; x <= N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                if(map[y][x] == 9) {
                    sharkPos = (y - 1) * N + (x - 1);
                    map[y][x] = 0;
                }
            }
        }

        queue.add(sharkPos);

        int qSize, x, y;
        int n;
        int size;
        int[] max = new int[2];
        int eatCount = 0;
        int result = 0;
        int temp = 0;
        int tempX, tempY;

        while(!queue.isEmpty()) {
            qSize = queue.size();
            max[0] = Integer.MAX_VALUE;
            max[1] = Integer.MAX_VALUE;
            temp++;
            for(int i = 0; i < qSize; i++) {
                n = queue.remove();
                x = n % N + 1;
                y = n / N + 1;
                for(int[] dir : dirs) {
                    tempX = x + dir[1];
                    tempY = y + dir[0];
                    if(visited[tempY][tempX]) continue;
                    size = map[tempY][tempX];

                    if(size > sharkSize) continue;
                    if(size != 0 && size < sharkSize) {
                        if(max[0] > tempY) {
                            max[0] = tempY;
                            max[1] = tempX;
                        }
                        if(max[0] == tempY && max[1] > tempX) {
                            max[0] = tempY;
                            max[1] = tempX;
                        }

                        continue;
                    }
                    visited[tempY][tempX] = true;
                    queue.add((tempY - 1) * N + (tempX - 1));
                }
            }

            if(max[0] != Integer.MAX_VALUE) {
                result += temp;
                temp = 0;
                eatCount++;
                if(eatCount == sharkSize) {
                    eatCount = 0;
                    sharkSize++;
                }
                map[max[0]][max[1]] = 0;
                queue.clear();

                for(int z = 0; z < N + 2; z++) {
                    for(int t = 0; t < N + 2; t++) {
                        visited[z][t] = false;
                    }
                }
                visited[max[0]][max[1]] = true;
                queue.add((max[0] - 1) * N + (max[1] - 1));
            }
        }
        System.out.print(result);
    }
}
