import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ16954 {
	
    public static class Node {
        int y;
        int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
	
    public static char[][] graph = new char[8][8];

    public static int[] dy = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
    public static int[] dx = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    
    public static boolean checkBound(int ny, int nx) {
        return ny < 0 || ny >= 8 || nx < 0 || nx >= 8;
    }

    public static boolean bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visited;

        queue.add(new Node(7, 0));

        while (!queue.isEmpty()) {
            int size = queue.size();

            visited = new boolean[8][8];

            for (int i = 0; i < size; i++) {
                Node poll = queue.poll();
                int y = poll.y;
                int x = poll.x;

                if (graph[y][x] == '#') continue;

                if (y == 0 && x == 7) {
                    return true;
                }

                for (int k = 0; k < 9; k++) {
                    int ny = y + dy[k];
                    int nx = x + dx[k];

                    if (checkBound(ny, nx)) continue;
                    if (visited[ny][nx] || graph[ny][nx] == '#') continue;

                    queue.add(new Node(ny, nx));
                    visited[ny][nx] = true;
                }
            }

            changeGraph();
        }

        return false;
    }

    public static void changeGraph() {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if (graph[i][j] == '#') {
                	graph[i][j] = '.';
                    if (i != 7) {
                    	graph[i + 1][j] = '#';
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	for (int i = 0; i < 8; i++) {
    		graph[i] = br.readLine().toCharArray();
    	}
    	if (bfs()) System.out.println(1);
    	else System.out.println(0);
    }

}
