import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static boolean[][] isVisited;
    static int[][] dirs = new int[][] {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};

    static class Node{
        public int x, y;
        public Node(int y, int x){
            this.x = x;
            this.y = y;
        }
    }

    static int BFS(){
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0));
        int count = 0;
        while(!q.isEmpty()){
            count++;
            int n = q.size();
            for(int k = 0; k < n; k++){
                Node cur = q.poll();
                int x = cur.x;
                int y = cur.y;

                if(x == M - 1 && y == N - 1) return count;
                for(int[] dir : dirs){
                    int newX = x + dir[0];
                    int newY = y + dir[1];
                    if(isOutOfMap(newY, newX) || isVisited[newY][newX]) continue;
                    isVisited[newY][newX] = true;
                    q.add(new Node(newY, newX));
                }
            }
        }
        return -1;
    }

    static boolean isOutOfMap(int y, int x){
        return y < 0 || y >= N || x < 0 || x >= M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        isVisited = new boolean[N][M];

        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                isVisited[i][j] = s.charAt(j) != '1';
            }
        }

        isVisited[0][0] = true;

        System.out.println(BFS());
    }
}
