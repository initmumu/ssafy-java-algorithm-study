import java.io.*;
import java.util.StringTokenizer;

public class BOJ18258 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] queue = new int[2_5000_000];

        int front = 0, back = 0;
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String inst = st.nextToken();

            switch(inst) {
                case "push":
                    int n = Integer.parseInt(st.nextToken());
                    queue[back++] = n;
                    break;
                case "pop":
                    if(back - front == 0) sb.append(-1 + "\n");
                    else sb.append(queue[front++] + "\n");
                    break;
                case "size":
                    sb.append(back - front + "\n");
                    break;
                case "empty":
                    if(back - front == 0) sb.append(1 + "\n");
                    else sb.append(0 + "\n");
                    break;
                case "front":
                    if(back - front == 0) sb.append(-1 + "\n");
                    else sb.append(queue[front] + "\n");
                    break;
                case "back":
                    if(back - front == 0) sb.append(-1 + "\n");
                    else sb.append(queue[back - 1] + "\n");
                    break;
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
