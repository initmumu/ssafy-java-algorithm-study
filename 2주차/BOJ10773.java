import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        int[] stack = new int[100000];
        int curI = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        int result = 0;

        for(int i = 0; i < K; i++) {
            int n = Integer.parseInt(br.readLine());
            if(n == 0) {
                result -= stack[curI - 1];
                curI--;
                continue;
            }
            result += n;
            stack[curI++] = n;
        }
        System.out.println(result);
    }
}