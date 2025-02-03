import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15686 {

    static int N, M;
    static List<int[]> houses = new ArrayList<>();
    static List<int[]> chickens = new ArrayList<>();
    static boolean[] selected;
    static int minChickenDistance = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int info = Integer.parseInt(st.nextToken());
                if (info == 1) {
                    houses.add(new int[]{i, j});
                }
                else if (info == 2) {
                    chickens.add(new int[]{i, j});
                }
            }
        }

        selected = new boolean[chickens.size()];

        selectChickens(0, 0);
        System.out.println(minChickenDistance);
    }

    static void selectChickens(int start, int count) {
        if (count == M) {
            minChickenDistance = Math.min(minChickenDistance, getChickenDistance());
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            selected[i] = true;
            selectChickens(i + 1, count + 1);
            selected[i] = false;
        }
    }

    static int getChickenDistance() {
        int result = 0;

        for (int[] house: houses) {
            int minDistance = Integer.MAX_VALUE;
            for (int i = 0; i < chickens.size(); i++) {
                if (selected[i]) {
                    int[] chicken = chickens.get(i);
                    minDistance = Math.min(minDistance, calcDistance(house, chicken));
                }
            }

            result += minDistance;
        }

        return result;
    }

    static int calcDistance(int[] house, int[] chicken) {
        return Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]);
    }
}
