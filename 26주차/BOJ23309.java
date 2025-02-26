import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ23309 {

    // 역의 최대 개수
    static int MAX_STATION_NUM = 1_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 해당 역의 다음/이전 인덱스를 저장하는 배열
        int[] nextStation = new int[MAX_STATION_NUM];
        int[] prevStation = new int[MAX_STATION_NUM];

        st = new StringTokenizer(br.readLine());
        int first = Integer.parseInt(st.nextToken());
        int current = first;

        for (int i = 1; i < N; i++) {
            int next = Integer.parseInt(st.nextToken());
            nextStation[current] = next;
            prevStation[next] = current;
            current = next;
        }

        // 처음역과 마지막역 연결
        nextStation[current] = first;
        prevStation[first] = current;

        for (int c = 0; c < M; c++) {
            st = new StringTokenizer(br.readLine());
            String type = st.nextToken();
            int i = Integer.parseInt(st.nextToken());

            switch (type) {
                // 고유 번호 i를 가진 역의 다음 역의 고유 번호를 출력하고,
                // 그 사이에 고유 번호 j인 역을 설립한다.
                case "BN": {
                    int j = Integer.parseInt(st.nextToken());
                    int next = nextStation[i];

                    // 기존 i -> next 에서
                    // i -> j -> next 으로 변경
                    nextStation[i] = j;
                    prevStation[j] = i;
                    nextStation[j] = next;
                    prevStation[next] = j;

                    sb.append(next).append("\n");
                    break;
                }
                // 고유 번호 i를 가진 이전 역의 고유 번호를 출력하고,
                // 그 사이에 고유 번호 j인 역을 설립한다.
                case "BP": {
                    int j = Integer.parseInt(st.nextToken());
                    int prev = prevStation[i];

                    // 기존 prev -> i 에서
                    // prev -> j -> i 로 변경
                    nextStation[prev] = j;
                    prevStation[j] = prev;
                    nextStation[j] = i;
                    prevStation[i] = j;

                    sb.append(prev).append("\n");
                    break;
                }
                // 고유 번호 i를 가진 역의 다음 역을 폐쇄하고
                // 그 역의 고유 번호를 출력한다.
                case "CN": {
                    // 기존 i -> next -> nextNext
                    // i -> nextNext 로 변경
                    int next = nextStation[i];
                    int nextNext = nextStation[next];

                    nextStation[i] = nextNext;
                    prevStation[nextNext] = i;

                    nextStation[next] = 0;
                    prevStation[next] = 0;

                    sb.append(next).append("\n");
                    break;
                }
                // 고유 번호 i를 가진 역의 이전 역을 폐쇄하고
                // 그 역의 고유 번호를 출력한다.
                case "CP": {
                    // 기존 prev_prev_station -> prev_station -> i
                    // prev_prev_station -> 로 변경
                    int prev = prevStation[i];
                    int prevPrev = prevStation[prev];

                    nextStation[prevPrev] = i;
                    prevStation[i] = prevPrev;

                    nextStation[prev] = 0;
                    prevStation[prev] = 0;

                    sb.append(prev).append("\n");
                    break;
                }
            }
        }

        System.out.println(sb);
    }
}
