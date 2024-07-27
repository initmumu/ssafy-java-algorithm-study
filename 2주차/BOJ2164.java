import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if(N == 1){
            System.out.print(1);
            return;
        }

        if(N < 5){
            System.out.print(2);
            return;
        }

        int i = 2;
        while(Math.pow(2, i) < N) i++;
        System.out.print((int)((N - Math.pow(2, i - 1)) * 2));
    }
}
