import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Node> nodes = new ArrayList<>();
    static int[] parent;
    static int[][] planets;

    public static class Node implements Comparable<Node> {
        public int from, to, distance;

        public Node(int from, int to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        planets = new int[N][4];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            planets[i][0] = i;
            planets[i][1] = Integer.parseInt(st.nextToken());
            planets[i][2] = Integer.parseInt(st.nextToken());
            planets[i][3] = Integer.parseInt(st.nextToken());
        }

        addEdges(1);
        addEdges(2);
        addEdges(3);

        parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;

        Collections.sort(nodes);

        int result = 0, count = 0;
        for (Node node : nodes) {
            if (merge(node.from, node.to)) {
                result += node.distance;
                count++;
                if (count == N - 1) break;
            }
        }

        System.out.println(result);
    }

    static void addEdges(int choock) {
        Arrays.sort(planets, Comparator.comparingInt(a -> a[choock]));

        for (int i = 0; i < N - 1; i++) {
            int from = planets[i][0];
            int to = planets[i + 1][0];
            int distance = Math.abs(planets[i][choock] - planets[i + 1][choock]);
            nodes.add(new Node(from, to, distance));
        }
    }

    static boolean merge(int a, int b) {
        int aP = find(a);
        int bP = find(b);
        if (aP == bP) return false;
        parent[aP] = bP;
        return true;
    }

    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
}
