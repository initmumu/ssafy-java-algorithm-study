import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ16947 {

    static int N;
    static int[] distance;
    static List<Integer>[] subwayLine; // 지하철 노선 그래프
    static Set<Integer> circularLine = new HashSet<>(); // 순환선에 속하는 역
    static boolean foundCycle = false;
    static int lastSearchStation = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        // 거리 배열 초기화
        distance = new int[N + 1];
        Arrays.fill(distance, -1);

        subwayLine = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            subwayLine[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            subwayLine[a].add(b);
            subwayLine[b].add(a);
        }

        // 순환선 찾기
        findCircularLine(1, 0);

        // 1번 역이 순환선에 속하지 않는 경우
        // 순환선에 확실히 포함되는 역부터 재탐색
        if (lastSearchStation != 1) {
            circularLine = new HashSet<>();
            findCircularLine(lastSearchStation, 0);
        }

        // 순환선에 속하는 역 사이의 거리 계산
        calcDistance();

        for (int i = 1; i <= N; i++) {
            sb.append(distance[i] + " ");
        }

        System.out.println(sb);
    }

    // 순환선 찾는 함수
    static boolean findCircularLine(int current, int prev) {
        circularLine.add(current); // 일단 현재역을 순환선에 포함시킴

        // 현재 역과 연결된 모든 역을 탐색
        for (int next : subwayLine[current]) {
            // 이전역은 건너뜀
            if (next == prev) {
                continue;
            }

            // 순환선을 발견한 경우
            if (circularLine.contains(next)) {
                foundCycle = true;
                lastSearchStation = next;
                return true;
            }

            // 다음역 탐색
            if (findCircularLine(next, current)) {
                if (foundCycle) {
                    return true;
                }

                circularLine.remove(current);
                return false;
            }
        }

        circularLine.remove(current);
        return false;
    }

    static void calcDistance() {
        Queue<Integer> queue = new LinkedList<>();

        // 순환선에 포함된 역을 큐에 삽입
        for (int i = 1; i <= N; i++) {
            if (circularLine.contains(i)) {
                distance[i] = 0;
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : subwayLine[current]) {
                if (distance[next] == -1) {
                    distance[next] = distance[current] + 1;
                    queue.add(next);
                }
            }
        }
    }
}
