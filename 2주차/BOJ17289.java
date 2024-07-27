import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] strArr = br.readLine().split(" ");
        int[] numArr = new int[N];
        int[] answerArr = new int[N];

        for (int i = 0; i < N; i++) {
            numArr[i] = Integer.parseInt(strArr[i]);
            answerArr[i] = -1;
        }

        int[] stack = new int[1_000_001];
        int stackIndex = 1;

        for (int i = 1; i < N; i++) {
            while (stackIndex != 0 && numArr[stack[stackIndex - 1]] < numArr[i]) {
                answerArr[stack[--stackIndex]] = numArr[i];
            }
            stack[stackIndex++] = i;
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            sb.append(answerArr[i]).append(" ");
        }

        System.out.println(sb);
    }
}
