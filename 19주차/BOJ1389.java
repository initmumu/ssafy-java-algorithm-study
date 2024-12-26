import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(i == j) continue;
                arr[i][j] = 1000000;
            }
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            arr[a][b] = 1;
            arr[b][a] = 1;
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(int k = 0; k < N; k++){
                    if(arr[j][i] + arr[i][k] < arr[j][k]){
                        arr[j][k] = arr[j][i] + arr[i][k];
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        int result = 0;

        for(int i = 0; i < N; i++){
            int n = 0;
            for(int j = 0; j < N; j++){
                n += arr[i][j];
            }
            if(min > n){
                min = n;
                result = i;
            }
        }

        System.out.print(result + 1);
    }

}
