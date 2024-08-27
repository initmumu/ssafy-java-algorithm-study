import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int W, H;
    static int[][] map;
    static Queue<Integer> q = new ArrayDeque<>();
    static int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        while(W != 0 && H != 0) {
            map = new int[H + 2][W + 2];
            for(int h = 1; h <= H; h++) {
                st = new StringTokenizer(br.readLine());
                for(int w = 1; w <= W; w++) {
                    map[h][w] = Integer.parseInt(st.nextToken());
                }
            }


            int islandCount = 0;
            for(int h = 1; h <= H; h++) {
                for(int w = 1; w <= W; w++) {
                    if(map[h][w] == 1) {
                        islandCount++;
                        FoundIsland(h, w);
                    }
                }
            }

            System.out.println(islandCount);

            st = new StringTokenizer(br.readLine());

            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
        }

    }

    public static void FoundIsland(int h, int w) {
        q.add(h*(W + 2) + w);
        map[h][w] = 0;
        int qSize;
        int temp, tempX, tempY;
        while(!q.isEmpty()) {
            qSize = q.size();

            for(int i = 0; i < qSize; i++) {
                temp = q.remove();
                tempY = temp / (W + 2);
                tempX = temp % (W + 2);

                for(int[] dir : dirs) {
                    if(map[tempY + dir[0]][tempX + dir[1]] == 1) {
                        q.add((tempY + dir[0]) * (W + 2) + tempX + dir[1]);
                        map[tempY + dir[0]][tempX + dir[1]] = 0;
                    }
                }
            }
        }
    }
}
