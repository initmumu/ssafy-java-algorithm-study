import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Boolean[][] arr = new Boolean[N][M];
        int[][] weight = new int[N][M];
        for(int i = 0; i < N; i++) {
            Arrays.fill(weight[i], Integer.MAX_VALUE);
        }

        for(int i = 0; i < N; i++) {
            arr[i] = br.readLine().chars()
                    .mapToObj(c -> c == '1')
                    .toArray(Boolean[]::new);
        }

        Queue<Integer> q = new ArrayDeque<>();
        q.add(0);
        weight[0][0] = arr[0][0] ? 1 : 0;

        while(!q.isEmpty()) {
            int qSize = q.size();

            for(int i = 0; i < qSize; i++) {
                int cur = q.poll();
                int y = cur / M;
                int x = cur % M;
                int curWeight = weight[y][x];

                for(int[] dir : dirs) {
                    int ny = y + dir[0];
                    int nx = x + dir[1];
                    if(IsOutOfMap(ny, nx)) continue;
                    int nWeight = curWeight;
                    if(arr[ny][nx]) nWeight++;

                    if(weight[ny][nx] > nWeight) {
                        weight[ny][nx] = nWeight;
                        q.add(ny * M + nx);
                    }
                }
            }
        }
        System.out.println(weight[N - 1][M - 1]);
    }

    public static boolean IsOutOfMap(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= M;
    }
}