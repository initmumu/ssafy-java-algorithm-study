import java.io.*;
import java.util.*;

public class BOJ15686 {
    static int N, M;
    static List<int[]> homes = new ArrayList<>();
    static List<int[]> chickens = new ArrayList<>();
    static boolean[] selected;
    static int minDistance = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int value = Integer.parseInt(st.nextToken());
                if (value == 1) {
                    homes.add(new int[]{i, j});
                } else if (value == 2) {
                    chickens.add(new int[]{i, j});
                }
            }
        }
        
        selected = new boolean[chickens.size()];
        dfs(0, 0);
        System.out.println(minDistance);
    }

    static void dfs(int start, int count) {
        if (count == M) {
            minDistance = Math.min(minDistance, getChickenDistance());
            return;
        }
        
        for (int i = start; i < chickens.size(); i++) {
            if (!selected[i]) {
                selected[i] = true;
                dfs(i + 1, count + 1);
                selected[i] = false;
            }
        }
    }

    static int getChickenDistance() {
        int total = 0;
        for (int[] home : homes) {
            int minDist = Integer.MAX_VALUE;
            for (int i = 0; i < chickens.size(); i++) {
                if (selected[i]) {
                    int[] chicken = chickens.get(i);
                    int dist = Math.abs(home[0] - chicken[0]) + Math.abs(home[1] - chicken[1]);
                    minDist = Math.min(minDist, dist);
                }
            }
            total += minDist;
        }
        return total;
    }
}
