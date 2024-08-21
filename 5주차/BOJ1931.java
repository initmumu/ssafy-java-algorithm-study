import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] time = new int[N][2];

        int min = Integer.MAX_VALUE;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            time[i][0] = Integer.parseInt(st.nextToken());
            time[i][1] = Integer.parseInt(st.nextToken());
        }
        int result = 1;

        // 회의 끝나는 시간 기준으로 오름차순 정렬 ( 끝나는 시간 겹칠 경우 시작하는 시간 기준 오름차순 정렬 )
        Arrays.sort(time, (o1, o2) -> {
            if(o1[1] == o2[1]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        });

        // 가장 빨리 끝나는 회의 -> 이후 가능한 회의 중 가장 빨리 끝나는 회의
        min = time[0][1];
        for(int i = 1; i < N; i++){
            if(time[i][0] >= min){
                min = time[i][1];
                result++;
            }
        }


        System.out.println(result);
    }

}
