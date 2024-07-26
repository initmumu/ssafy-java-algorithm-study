import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<int[]> stack = new Stack<>();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int height = Integer.parseInt(st.nextToken());

            if (stack.isEmpty()) {
                System.out.print("0 ");
                stack.push(new int[] {i, height});
            } else {
                while (true) {
                    if (stack.isEmpty()) {
                        System.out.print("0 ");
                        stack.push(new int[]{i, height});
                        break;
                    }

                    int[] top = stack.peek();

                    if (top[1] > height) {
                        System.out.print(top[0] + " ");
                        stack.push(new int[]{i, height});
                        break;
                    } else stack.pop();
                }
            }
        }
    }
}
