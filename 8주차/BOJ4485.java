import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BOJ4485 {

    public static class Node implements Comparable<Node> {

        int y, x, weight;

        public Node(int y, int x, int weight) {
            super();
            this.y = y;
            this.x = x;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }

    }

    public static int N;
    public static int[][] graph;
    public static int[][] dist;
    public static int[] dy = {-1, 0, 1, 0};
    public static int[] dx = {0, 1, 0, -1};
    public static final int INF = Integer.MAX_VALUE;

    public static boolean checkBound(int ny, int nx) {
        return ny < 0 || ny >= N || nx < 0 || nx >= N;
    }

    public static int dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.offer(new Node(0, 0, graph[0][0]));
        dist[0][0] = graph[0][0];

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int y = node.y;
            int x = node.x;
            int weight = node.weight;

			if (weight > dist[y][x]) {
				continue;
			}

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
				if (checkBound(ny, nx)) {
					continue;
				}
                int newDist = weight + graph[ny][nx];
                if (newDist < dist[ny][nx]) {
                    dist[ny][nx] = newDist;
                    pq.offer(new Node(ny, nx, newDist));
                }
            }
        }

        return dist[N - 1][N - 1];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int n = 0;
        while (true) {
            n++;
            N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break;
			}
            graph = new int[N][N];
            dist = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = INF;
                }
            }
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            sb.append("Problem ").append(n).append(": ").append(dijkstra()).append("\n");
        }
        System.out.println(sb);
    }

}
