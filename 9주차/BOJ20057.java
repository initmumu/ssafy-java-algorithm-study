package algo_study.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20057 {
    public static int N;
    public static int[][] graph;
    public static int totalSandOut = 0;

    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {-1, 0, 1, 0};

    public static int[][] dxOfSand = {
        {-2, -1, -1, -1, 0, 1, 1, 1, 2},
        {0, 1, 0, -1, 2, 1, 0, -1, 0},
        {2, 1, 1, 1, 0, -1, -1, -1, -2},
        {0, -1, 0, 1, -2, -1, 0, 1, 0}
    };
    public static int[][] dyOfSand = {
        {0, -1, 0, 1, -2, -1, 0, 1, 0},
        {-2, -1, -1, -1, 0, 1, 1, 1, 2},
        {0, 1, 0, -1, 2, 1, 0, -1, 0},
        {2, 1, 1, 1, 0, -1, -1, -1, -2}
    };
    public static int[] ratioOfSand = {2, 10, 7, 1, 5, 10, 7, 1, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
            	graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int x = N / 2;
        int y = N / 2;

        int moveLength = 1;
        int direction = 0;

        while (x != 0 || y != 0) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < moveLength; j++) {
                    x += dx[direction];
                    y += dy[direction];
                    moveTornado(x, y, direction);
                    
                    if (x == 0 && y == 0) break;
                }
                direction = (direction + 1) % 4;
                if (x == 0 && y == 0) break;
            }
            moveLength++;
        }

        System.out.println(totalSandOut);
    }

    public static void moveTornado(int x, int y, int direction) {
        int sand = graph[x][y];
        int scatteredSand = 0;

        for (int i = 0; i < 9; i++) {
            int nx = x + dxOfSand[direction][i];
            int ny = y + dyOfSand[direction][i];
            int amount = (sand * ratioOfSand[i]) / 100;
            scatteredSand += amount;

            if (checkBoundary(nx, ny)) {
            	graph[nx][ny] += amount;
            } else {
                totalSandOut += amount;
            }
        }

        int alphaX = x + dx[direction];
        int alphaY = y + dy[direction];
        int remainingSand = sand - scatteredSand;

        if (checkBoundary(alphaX, alphaY)) {
        	graph[alphaX][alphaY] += remainingSand;
        } else {
            totalSandOut += remainingSand; 
        }

        graph[x][y] = 0;
    }

    public static boolean checkBoundary(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
