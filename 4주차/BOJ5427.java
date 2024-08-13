import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[][] dir = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static Queue<Integer> player;
    static Queue<Integer> fire;
    static int w, h;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++){
            st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            player = new LinkedList<>();
            fire = new LinkedList<>();
            map = new int[h][w];

            for(int y = 0; y < h; y++){
                String str = br.readLine();
                for(int x = 0; x < str.length(); x++){
                    switch(str.charAt(x)){
                        case '#':
                            map[y][x] = -1;
                            break;
                        case '*':
                            fire.add(y * w + x);
                            map[y][x] = 1;
                            break;
                        case '@':
                            player.add(y * w + x);
                            map[y][x] = 2;
                            break;
                    }
                }
            }

            int result = 0;
            boolean escaped = false;
            while(!escaped && !player.isEmpty()){
                result++;
                int fireSize = fire.size();
                for(int i = 0; i < fireSize; i++){
                    int fireIndex = fire.remove();
                    int x = fireIndex % w;
                    int y = fireIndex / w;
                    int nx, ny;

                    for(int j = 0; j < 4; j++){
                        nx = x + dir[j][1];
                        ny = y + dir[j][0];
                        if(IsOutOfMap(nx, ny)) continue;
                        if(map[ny][nx] == 0 || map[ny][nx] == 2) {
                            map[ny][nx] = 1;
                            fire.add(ny * w + nx);
                        }
                    }
                }

                int qSize = player.size();
                for(int i = 0; i < qSize; i++){
                    int playerIndex = player.remove();
                    int x = playerIndex % w;
                    int y = playerIndex / w;
                    int nx, ny;

                    for(int j = 0; j < 4; j++){
                        nx = x + dir[j][1];
                        ny = y + dir[j][0];

                        if(IsOutOfMap(nx, ny)) {
                            escaped = true;
                            break;
                        }
                        if(map[ny][nx] == 0) {

                            map[ny][nx] = 2;
                            player.add(ny * w + nx);
                        }
                    }
                    if(escaped) break;
                }
            }
            if(escaped) System.out.println(result);
            else System.out.println("IMPOSSIBLE");
        }
    }

    public static boolean IsOutOfMap(int x, int y){
        return x < 0 || x >= w || y < 0 || y >= h;
    }
}
