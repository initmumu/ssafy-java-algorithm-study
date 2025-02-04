import java.io.*;
import java.util.*;

public class BOJ5052 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            String[] numbers = new String[n];
            
            for (int i = 0; i < n; i++) {
                numbers[i] = br.readLine();
            }
            
            Arrays.sort(numbers);
            
            boolean flag = true;
            for (int i = 0; i < n - 1; i++) {
                if (numbers[i+1].startsWith(numbers[i])) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }
            
        }
        System.out.print(sb);
    }
}
