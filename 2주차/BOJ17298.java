import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] result = new int[N];
        int[] stack = new int[N];
        int index = 0;
        for(int i = N - 1; i >= 0; i--){
            while(stack[index] <= input[i]){
                index--;
                if(index == -1){
                    result[i] = -1;
                    break;
                }
            }

            if(index >= 0){
                result[i] = stack[index];
            }
            stack[++index] = input[i];
        }

        StringBuilder sb = new StringBuilder();
        for(int n : result){
            sb.append(n).append(' ');
        }

        System.out.print(sb);
    }
}
