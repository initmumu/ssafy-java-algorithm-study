import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        Queue<int[]> q = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        boolean[][][] visitedMap = new boolean[H][N][M];

        int tomatoCount = 0;
        int date = 0;

        for(int z = 0; z < H; z++) {
            for (int y = 0; y < N; y++) {
                String s = bf.readLine();
                st = new StringTokenizer(s);

                for (int x = 0; x < M; x++) {
                    int a = Integer.parseInt(st.nextToken());

                    if (a == 1) {
                        q.add(new int[]{x, y, z});
                    }

                    if (a != 0) {
                        visitedMap[z][y][x] = true;
                        tomatoCount++;
                    }
                }
            }
        }

        int[] dirX = {1, -1, 0, 0};
        int[] dirY = {0, 0, 1, -1};
        int goalCount = N * M * H;
        while(!q.isEmpty()){
            if(tomatoCount == goalCount) break;
            int qSize = q.size();
            date++;
            for(int i = 0; i < qSize; i++){
                int[] t= q.remove();
                int x = t[0];
                int y = t[1];
                int z = t[2];

                int newX, newY;
                for(int d = 0; d < 4; d++){
                    newX = x + dirX[d];
                    newY = y + dirY[d];

                    if(!(newX < 0 || newX > M - 1 || newY < 0 || newY > N - 1) && !visitedMap[z][newY][newX]) {
                        q.add(new int[]{newX, newY, z});
                        visitedMap[z][newY][newX] = true;
                        tomatoCount++;
                    }
                }

                int newZ = z + 1;

                if(!(newZ < 0 || newZ >= H) && !visitedMap[newZ][y][x]) {
                    q.add(new int[]{x, y, newZ});
                    visitedMap[newZ][y][x] = true;
                    tomatoCount++;
                }

                newZ = z - 1;
                if(!(newZ < 0 || newZ >= H) && !visitedMap[newZ][y][x]) {
                    q.add(new int[]{x, y, newZ});
                    visitedMap[newZ][y][x] = true;
                    tomatoCount++;
                }

            }
        }

        if(tomatoCount != goalCount)System.out.print(-1);
        else System.out.print(date);
    }
}