import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] data = new int [N];

        int min = 0, max = 0;
        for(int i = 0; i < N; i++){
            data[i] = Integer.parseInt(br.readLine());
            min = Math.max(min, data[i]);
            max += data[i];
        }

        while(min <= max){
            int mid = (min + max) / 2;
            int sum = 0;
            int count = 1;
            for(int i = 0; i < N; i++){
                if(sum + data[i] > mid){
                    count++;
                    sum = 0;
                }
                sum += data[i];
            }
            if(count > M){
                min = mid + 1;
            }else{
                max = mid - 1;
            }
        }
        System.out.print(min);
    }
}
