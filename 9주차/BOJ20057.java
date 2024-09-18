import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[][] dirs = new int[][] {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    static int result;

    static int[][][] nogada = new int[][][]{
            {{0, 0, 2, 0, 0},
                    {0, 10, 7, 1, 0},
                    {5, 0, 0, 0, 0},
                    {0, 10, 7, 1, 0},
                    {0, 0, 2, 0, 0}},
            {{0, 0, 0, 0, 0},
                    {0, 1, 0, 1, 0},
                    {2, 7, 0, 7, 2},
                    {0, 10, 0, 10, 0},
                    {0, 0, 5, 0, 0}},
            {{0, 0, 2, 0, 0},
                    {0, 1, 7, 10, 0},
                    {0, 0, 0, 0, 5},
                    {0, 1, 7, 10, 0},
                    {0, 0, 2, 0, 0}},
            {{0, 0, 5, 0, 0},
                    {0, 10, 0, 10, 0},
                    {2, 7, 0, 7, 2},
                    {0, 1, 0, 1, 0},
                    {0, 0, 0, 0, 0}}};

    public static void Move(int dir, int curY, int curX){
        int newY = curY + dirs[dir][0];
        int newX = curX + dirs[dir][1];
        int flySands = 0;
        if(IsOutOfMap(newY, newX)) return;

        for(int i = -2; i <= 2; i++){
            for(int j = -2; j <= 2; j++){
                int flySand = (int)(map[newY][newX] * (nogada[dir][i + 2][j + 2] * 0.01));
                if(flySand == 0) continue;

                flySands += flySand;
                if(IsOutOfMap(newY + i, newX + j)){
                    result += flySand;
                    continue;
                }
                map[newY + i][newX + j] += flySand;
            }
        }

        int nnewY = newY + dirs[dir][0];
        int nnewX = newX + dirs[dir][1];
        if(IsOutOfMap(nnewY, nnewX)){
            result += map[newY][newX] - flySands;
            map[newY][newX] = 0;
            return;
        }

        map[nnewY][nnewX] += map[newY][newX] - flySands;
        map[newY][newX] = 0;
    }

    public static boolean IsOutOfMap(int y, int x){
        return y < 0 || y >= N || x < 0 || x >= N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        result = 0;

        for(int y = 0; y < N; y++){
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x < N; x++){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int curY = N / 2;
        int curX = N / 2;

        int time = 0;
        int movDist = 1;
        int dir = 0;
        while(curY != 0 || curX != 0){
            for(int i = 0; i < movDist; i++){
                Move(dir, curY, curX);
                curY += dirs[dir][0];
                curX += dirs[dir][1];
                if(curY == 0 && curX == 0) break;
            }
            dir = (dir + 1) % 4;
            time++;
            if(time % 2 == 0) movDist++;
        }

        System.out.print(result);
    }
}