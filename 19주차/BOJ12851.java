import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if(N == K){
            System.out.println(0);
            System.out.println(1);
            return;
        }

        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(N);

        int min = 0;
        int count = 0;
        boolean end = false;

        boolean[] visited = new boolean[200000];
        HashSet<Integer> set;
        while(!queue.isEmpty()){
            int n = queue.size();
            set = new HashSet<>();
            for(int i = 0; i < n; i++){
                int cur = queue.poll();

                if(cur + 1 < 200000 && !visited[cur + 1]){
                    queue.add(cur + 1);
                    set.add(cur + 1);
                    if(cur + 1 == K){
                        count++;
                        end = true;
                    }
                }

                if(cur - 1 >= 0 && !visited[cur - 1]){
                    queue.add(cur - 1);
                    set.add(cur - 1);
                    if(cur - 1 == K){
                        count++;
                        end = true;
                    }
                }

                if(cur * 2 < 200000 && !visited[cur * 2]){
                    queue.add(cur * 2);
                    set.add(cur * 2);
                    if(cur * 2 == K){
                        count++;
                        end = true;
                    }
                }
            }
            if(end){
                System.out.println(min + 1);
                System.out.println(count);
                return;
            }

            for(int num : set){
                visited[num] = true;
            }
            min++;
        }
    }

}
