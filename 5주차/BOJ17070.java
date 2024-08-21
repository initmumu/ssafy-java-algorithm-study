import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
    public int x, y, direction;
    Node(int x, int y, int direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
}

public class Main {
    static int[][] map;
    static int N;
    static Queue<Node> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // BFS
        int result = 0;
        q.add(new Node(1, 0, 1));
        int qSize;
        Node node;

        while(!q.isEmpty()) {
            qSize = q.size();

            for(int i = 0; i < qSize; i++) {
                node = q.remove();

                // 목적지에 도착하면 result 증가
                if(node.y == N - 1 && node.x == N - 1) {
                    result++;
                    continue;
                }

                switch(node.direction) {
                    // 노드의 방향이 우측일 때
                    case 1:
                        // 우측으로 밀거나
                        if(node.x + 1 < N && map[node.y][node.x + 1] != 1) q.add(new Node(node.x + 1, node.y, 1));
                        // 대각선으로 돌리기
                        if(node.x + 1 < N
                                && node.y + 1 < N
                                && map[node.y + 1][node.x] != 1
                                && map[node.y][node.x + 1] != 1
                                && map[node.y + 1][node.x + 1] != 1) q.add(new Node(node.x + 1, node.y + 1, 0));
                        break;
                    // 노드의 방향이 대각선일 때
                    case 0:
                        // 우측으로 돌리거나
                        if(node.x + 1 < N && map[node.y][node.x + 1] != 1) q.add(new Node(node.x + 1, node.y, 1));
                        // 대각선으로 밀거나
                        if(node.x + 1 < N
                                && node.y + 1 < N
                                && map[node.y + 1][node.x] != 1
                                && map[node.y][node.x + 1] != 1
                                && map[node.y + 1][node.x + 1] != 1) q.add(new Node(node.x + 1, node.y + 1, 0));
                        // 아래로 돌리거나
                        if(node.y + 1 < N && map[node.y + 1][node.x] != 1) q.add(new Node(node.x, node.y + 1, -1));
                        break;
                    // 노드의 방향이 아래일 때
                    case -1:
                        // 아래로 밀거나
                        if(node.y + 1 < N && map[node.y + 1][node.x] != 1) q.add(new Node(node.x, node.y + 1, -1));
                        // 대각선으로 돌리기
                        if(node.x + 1 < N
                                && node.y + 1 < N
                                && map[node.y + 1][node.x] != 1
                                && map[node.y][node.x + 1] != 1
                                && map[node.y + 1][node.x + 1] != 1) q.add(new Node(node.x + 1, node.y + 1, 0));
                        break;
                }
            }
        }

        System.out.println(result);
    }
}
