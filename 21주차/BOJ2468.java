import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] isDrowned;
    static int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static class Node{
        public int x, y;
        public Node(int y, int x){
            this.x = x;
            this.y = y;
        }
    }

    static int countIslands(){
        boolean[][] isVisited = new boolean[N][N];
        for(int i = 0; i < N; i++){
            isVisited[i] = isDrowned[i].clone();
        }
        int count = 0;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(isVisited[i][j]) continue;
                count++;
                Queue<Node> q = new ArrayDeque<>();
                q.add(new Node(i, j));
                while(!q.isEmpty()){
                    int n = q.size();
                    for(int k = 0; k < n; k++){
                        Node cur = q.poll();
                        int x = cur.x;
                        int y = cur.y;
                        if(isVisited[y][x]) continue;
                        isVisited[y][x] = true;

                        for(int[] dir : dirs){
                            int newX = x + dir[0];
                            int newY = y + dir[1];
                            if(isOutOfMap(newY, newX)) continue;

                            q.add(new Node(newY, newX));
                        }
                    }
                }
            }
        }
        return count;
    }

    static boolean isOutOfMap(int y, int x){
        return y < 0 || y >= N || x < 0 || x >= N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        isDrowned = new boolean[N][N];

        int min = Integer.MAX_VALUE, max = -1;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }

        int result = 1;
        for(int h = min; h <= max; h++){
            for(int y = 0; y < N; y++){
                for(int x = 0; x < N; x++){
                    if(isDrowned[y][x]) continue;
                    if(map[y][x] <= h) isDrowned[y][x] = true;
                }
            }

            result = Math.max(result, countIslands());
        }
        System.out.println(result);
    }
}
