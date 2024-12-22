import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if(N == K){
            System.out.print(0);
            return;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[200000];

        queue.add(N);
        int count = 0;
        while(!queue.isEmpty()){
            int n = queue.size();
            for(int i = 0; i < n; i++){
                int cur = queue.poll();

                if(cur + 1 < 200000 && !visited[cur + 1]){
                    queue.add(cur + 1);
                    visited[cur + 1] = true;
                    if(cur + 1 == K){
                        System.out.print(count + 1);
                        return;
                    }
                }

                if(cur - 1 >= 0 && !visited[cur - 1]){
                    queue.add(cur - 1);
                    visited[cur - 1] = true;
                    if(cur - 1 == K){
                        System.out.print(count + 1);
                        return;
                    }
                }

                if(cur * 2 < 200000 && !visited[cur * 2]){
                    queue.add(cur * 2);
                    visited[cur * 2] = true;
                    if(cur * 2 == K){
                        System.out.print(count + 1);
                        return;
                    }
                }
            }
            count++;
        }
    }

}
