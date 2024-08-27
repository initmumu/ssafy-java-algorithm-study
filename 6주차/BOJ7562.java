import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int l;
    static int[][] map;
    static int[][] dirs = new int[][] {{-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}};
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int curX, curY, endX, endY;

        for(int t = 1; t <= T; t++) {
            l = Integer.parseInt(br.readLine());
            map = new int[l][l];
            q = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine());
            curX = Integer.parseInt(st.nextToken());
            curY = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            endX = Integer.parseInt(st.nextToken());
            endY = Integer.parseInt(st.nextToken());


            if(curX == endX && curY == endY) {
                sb.append('0').append('\n');
                continue;
            }

            map[endY][endX] = 1;

            q.add(curY * l + curX);
            int result = BFS();

            sb.append(result).append('\n');
        }
        System.out.println(sb);
    }

    public static int BFS() {
        int qSize;
        int result = 0;
        int temp, tempY, tempX;
        int curY, curX;
        while(!q.isEmpty()) {
            qSize = q.size();
            result++;
            for(int i = 0; i < qSize; i++) {
                temp = q.remove();
                curY = temp / l;
                curX = temp % l;

                for(int[] dir : dirs) {
                    tempY = curY + dir[0];
                    tempX = curX + dir[1];
                    if(IsOutOfMap(tempY, tempX)) continue;
                    if(map[tempY][tempX] == -1)continue;
                    if(map[tempY][tempX] == 1) {
                        return result;
                    }

                    q.add(tempY * l + tempX);
                    map[tempY][tempX] = -1;
                }
            }
        }

        return 0;
    }

    public static boolean IsOutOfMap(int y, int x) {
        return y < 0 || y >= l || x < 0 || x >= l;
    }

}
