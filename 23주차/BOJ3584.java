import java.io.*;
import java.util.*;

public class Main {
    static int T, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++){
            N = Integer.parseInt(br.readLine());

            int[] arr = new int[N + 1];
            for(int i = 1; i < N + 1; i++){
                arr[i] = i;
            }

            for(int i = 1; i < N; i++){
                st = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                arr[child] = parent;
            }

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            HashSet<Integer> set = new HashSet<>();


            set.add(a);

            while(arr[a] != a){
                a = arr[a];
                set.add(a);
            }
            set.add(a);

            while(!set.contains(b)){
                b = arr[b];
            }

            sb.append(b).append('\n');

        }

        System.out.print(sb);
    }
}
