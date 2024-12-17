import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ1674 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        List<String[]> data = new ArrayList<>();    // Chocolate, Coffee 데이터 저장
        List<Integer> queries = new ArrayList<>();  // Query 시간 저장

        String input;
        while ((input = br.readLine()) != null && !input.isEmpty()) {
            String[] command = input.split(" ");
            if (command[0].equals("Query")) {
                queries.add(Integer.parseInt(command[1]));
            }
            else {
                data.add(command);
            }
        }

        // 쿼리 시간 오름차순으로 정렬
        Collections.sort(queries);

        for (int queryTime : queries) {
            double safetyDistance = getSafetyDistance(queryTime, data);
            result.append(queryTime).append(" ").append(Math.round(safetyDistance * 10) / 10.0).append("\n");
        }

        System.out.println(result);
    }

    static double getSafetyDistance(int queryTime, List<String[]> data) {
        double safetyDistance = 0.0;  // 안전거리

        for (String[] arr : data) {
            String type = arr[0];   // Chocolate 또는 Coffee
            int T = Integer.parseInt(arr[1]);   // 시간 T에 섭취
            double N = Double.parseDouble(arr[2]);   // N 만큼을 섭취
            double timeDiff = queryTime - T;

            if (timeDiff < 0) {
                continue;
            }

            double effect = 0.0;  // 효과 반경

            if (type.equals("Chocolate")) {
                effect = 8.0 * N - timeDiff / 12.0;
            }
            else if (type.equals("Coffee")) {
                effect = 2.0 * N - (timeDiff * timeDiff / 79.0);
            }

            if (effect > 0) {
                safetyDistance += effect;
            }
        }

        return Math.max(safetyDistance, 1.0);  // 안전거리가 1보다 작으면 1로 처리
    }
}
