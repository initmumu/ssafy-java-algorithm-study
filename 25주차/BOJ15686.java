import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int min;
    static int[][] distance;
    static ArrayList<Node> chickenList = new ArrayList<>();
    static ArrayList<Node> houseList = new ArrayList<>();
    static int[] selectedChicken;

    public static class Node {
        public int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void dfs(int depth, int start) {
        if (depth == M) {
            int curMin = 0;

            for (int i = 0; i < houseList.size(); i++) {
                int hMin = Integer.MAX_VALUE;
                for (int j = 0; j < M; j++) {
                    hMin = Math.min(hMin, distance[i][selectedChicken[j]]);
                }
                curMin += hMin;
                if (curMin >= min) return;
            }

            min = Math.min(min, curMin);
            return;
        }

        for (int i = start; i < chickenList.size(); i++) {
            selectedChicken[depth] = i;
            dfs(depth + 1, i + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int value = Integer.parseInt(st.nextToken());
                if (value == 1) {
                    houseList.add(new Node(i, j));
                } else if (value == 2) {
                    chickenList.add(new Node(i, j));
                }
            }
        }

        int houseSize = houseList.size();
        int chickenSize = chickenList.size();
        selectedChicken = new int[M];
        distance = new int[houseSize][chickenSize];

        for (int i = 0; i < houseSize; i++) {
            Node h = houseList.get(i);
            for (int j = 0; j < chickenSize; j++) {
                Node c = chickenList.get(j);
                distance[i][j] = Math.abs(h.x - c.x) + Math.abs(h.y - c.y);
            }
        }

        dfs(0, 0);
        System.out.print(min);
    }
}
