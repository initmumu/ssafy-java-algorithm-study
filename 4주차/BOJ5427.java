import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ5427 {
    static int w, h;
    static char map[][];

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static Queue<int[]> sg;
    static Queue<int[]> fire;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            sg = new LinkedList<>();
            fire = new LinkedList<>();

            map = new char[h][w];

            for (int j = 0; j < h; j++) {
                String line = br.readLine();
                for (int k = 0; k < w; k++) {
                    map[j][k] = line.charAt(k);
                    if (map[j][k] == '@')
                        sg.add(new int[]{j, k});
                    else if (map[j][k] == '*')
                        fire.add(new int[]{j, k});
                }
            }

            int time = BFS();
            if (time == -1) {
                System.out.println("IMPOSSIBLE");
            } else {
                System.out.println(time);
            }
        }
    }

    public static int BFS() {
        int time = 0;
        while (!sg.isEmpty()) {
            time++;

            int size = fire.size();
            for (int f = 0; f < size; f++) {
                int[] pos = fire.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = pos[0] + dx[i];
                    int ny = pos[1] + dy[i];
                    if (nx >= 0 && ny >= 0 && nx < h && ny < w && map[nx][ny] == '.') {
                        fire.add(new int[]{nx, ny});
                        map[nx][ny] = '*';
                    }
                }
            }

            size = sg.size();
            for (int s = 0; s < size; s++) {
                int[] pos = sg.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = pos[0] + dx[i];
                    int ny = pos[1] + dy[i];

                    if (nx < 0 || ny < 0 || nx >= h || ny >= w)
                        return time;

                    if (map[nx][ny] == '.') {
                        sg.add(new int[]{nx, ny});
                        map[nx][ny] = '@';
                    }
                }
            }
        }
        return -1;
    }
}
