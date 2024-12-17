import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2110 {

    public static int[] houses;
    public static int N, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 집의 개수
        C = Integer.parseInt(st.nextToken()); // 공유기의 개수

        houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        // 집의 좌표를 오름차순으로 정렬
        Arrays.sort(houses);

        int left = 1; // 최소 거리
        int right = houses[N - 1] - houses[0]; // 최대 거리
        int result = 0; // 최종 결과값

        while (left <= right) {
            int mid = (left + right) / 2; // 가장 인접한 두 공유기 사이의 거리
            if (canInstall(mid)) { // mid 거리로 공유기 설치 가능 여부 검사
                result = Math.max(result, mid); // 가능하면 결과값 갱신
                left = mid + 1; // 더 큰 거리 시도
            } else {
                right = mid - 1; // 더 작은 거리 시도
            }
        }

        System.out.println(result);
    }

    // 현재 거리(dist)를 기준으로 C개의 공유기를 설치할 수 있는지 확인하는 함수
    private static boolean canInstall(int dist) {
        int count = 1; // 첫 번째 집에 공유기 설치
        int lastInstalled = houses[0]; // 마지막으로 공유기를 설치한 위치

        for (int i = 1; i < houses.length; i++) {
            if (houses[i] - lastInstalled >= dist) {
                count++;
                lastInstalled = houses[i];
            }
        }
        return count >= C; // 공유기를 C개 이상 설치할 수 있는 경우 true
    }
}
