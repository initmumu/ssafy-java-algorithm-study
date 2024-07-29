import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        Stack<int[]> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int current_top_height = Integer.parseInt(st.nextToken());

            while (!stack.isEmpty()) {
                if (stack.peek()[1] < current_top_height)
                    stack.pop();
                else {
                    sb.append(stack.peek()[0])
                            .append(' ');
                    break;
                }
            }

            if (stack.empty())
                sb.append("0 ");
            stack.push(new int[] { i + 1, current_top_height });
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
