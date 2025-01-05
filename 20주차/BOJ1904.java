import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long a = 1;
        long b = 2;
        long c = 0;

        if(N == 1){
            System.out.println(1);
            return;
        } else if(N == 2){
            System.out.println(2);
            return;
        }

        for(int i = 3; i <= N; i++){
            c = (a + b) % 15746;
            a = b;
            b = c;
        }

        System.out.println(c);
    }
}