import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] data = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                data[i] = Integer.parseInt(st.nextToken());
            }
            int max = 0;
            long result = 0;

            // 뒤에서부터 진행하며 max 값을 갱신하고 다음 max 가 나올때까지 max 와의 차를 result 에 저장
            for(int i = N - 1; i >= 0; i--){
                if(max <= data[i]) {
                    max = data[i];
                    continue;
                }
                result += max - data[i];
            }
            System.out.println(result);
        }
    }

}
