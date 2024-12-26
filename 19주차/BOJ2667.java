import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static ArrayList<Integer> result = new ArrayList<>();

    public static class coord{
        public int x, y;

        public coord(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N + 2][N + 2];

        for(int i = 0; i < N; i++){
            map[0][i] = 2;
            map[i][N + 1] = 2;
            map[i][0] = 2;
            map[N + 1][i] = 2;
        }

        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < N; j++){
                map[i + 1][j + 1] = s.charAt(j) - '0';
            }
        }

        Queue<coord> queue;

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(map[i][j] != 1) continue;
                int count = 1;
                queue = new ArrayDeque<>();
                queue.add(new coord(j, i));
                map[i][j] = 2;

                while(!queue.isEmpty()){
                    int n = queue.size();

                    for(int t = 0; t < n; t++){
                        coord cur = queue.poll();

                        for(int[] dir : dirs){
                            int nx = cur.x + dir[0];
                            int ny = cur.y + dir[1];

                            if(map[ny][nx] == 1){
                                queue.add(new coord(nx, ny));
                                map[ny][nx] = 2;
                                count++;
                            }
                        }
                    }
                }

                result.add(count);
            }
        }
        Collections.sort(result);

        System.out.println(result.size());
        for(int r : result){
            System.out.println(r);
        }
    }

}
