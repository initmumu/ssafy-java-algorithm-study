import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        int temp = N;
        int count = 0;
        for(int i = N - 1; i >= 0; i--){
            if(arr[i] == temp){
                temp--;
                count++;
            }
        }

        System.out.println(N - count);
    }
}