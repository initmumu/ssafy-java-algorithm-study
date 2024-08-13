import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] num;
    static int[] ex;

    public static int[] DFS(int depth, int n){
        int[] result = new int[]{n, n};
        if(depth == N) return result;

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int i = 0; i < 4; i++){
            if(ex[i] == 0) continue;

            ex[i]--;
            switch (i) {
                case 0:
                    result = DFS(depth + 1, n + num[depth]);
                    break;
                case 1:
                    result = DFS(depth + 1, n - num[depth]);
                    break;
                case 2:
                    result = DFS(depth + 1, n * num[depth]);
                    break;
                case 3:
                    result = DFS(depth + 1, n / num[depth]);
                    break;
            };
            max = Math.max(max, result[0]);
            min = Math.min(min, result[1]);
            ex[i]++;
        }

        result[0] = max;
        result[1] = min;

        return new int[]{max, min};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        num = new int[N];
        ex = new int[4];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++){
            ex[i] = Integer.parseInt(st.nextToken());
        }

        int[] result = DFS(1, num[0]);

        System.out.println(result[0]);
        System.out.println(result[1]);
    }
}
