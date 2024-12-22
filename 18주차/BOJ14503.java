import java.io.*;
import java.util.*;

public class Main {
    static int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int curDir = Integer.parseInt(st.nextToken());

        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;

        while(true){

            if(map[r][c] == 0){
                map[r][c] = 2;
                count++;
            }

            boolean isAllClean = true;
            for(int d = 0; d < 4; d++){
                curDir -= 1;
                if(curDir < 0) curDir = 3;

                int newR = r + dirs[curDir][0];
                int newC = c + dirs[curDir][1];

                if(isOutOfMap(newR, newC)) continue;

                if(map[newR][newC] == 0){
                    r = newR;
                    c = newC;
                    isAllClean = false;
                    break;
                }
            }

            if(isAllClean){
                int backDir = (curDir + 2) % 4;
                int backR = r + dirs[backDir][0];
                int backC = c + dirs[backDir][1];

                if(isOutOfMap(backR, backC) || map[backR][backC] == 1){
                    break;
                } else {
                    r = backR;
                    c = backC;
                }
            }
        }

        System.out.print(count);

    }

    public static boolean isOutOfMap(int r, int c){
        return r < 0 || c < 0 || r >= map.length || c >= map[0].length;
    }

}
