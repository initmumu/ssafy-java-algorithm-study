package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<double[]> chocolate = new ArrayList<>();
        ArrayList<double[]> coffee = new ArrayList<>();
        ArrayList<Integer> query = new ArrayList<>();

        String line;
        while ((line = br.readLine()) != null && !line.isBlank()) {
        	
            StringTokenizer st = new StringTokenizer(line);
            String type = st.nextToken();
            
            if (type.equals("Query")) {
                int T = Integer.parseInt(st.nextToken());
                query.add(T); 
            } else if (type.equals("Chocolate")) {
                int T = Integer.parseInt(st.nextToken());
                double N = Double.parseDouble(st.nextToken());
                double[] temp = {T, N};
                chocolate.add(temp);
            } else if (type.equals("Coffee")) {
                int T = Integer.parseInt(st.nextToken());
                double N = Double.parseDouble(st.nextToken());
                double[] temp = {T, N};
                coffee.add(temp);
            }
        }

        // 입력받은 query마다 정답 계산
        Collections.sort(query);
        Collections.sort(chocolate, (a, b) -> Double.compare(a[0], b[0]));
        Collections.sort(coffee, (a, b) -> Double.compare(a[0], b[0]));
        for (int T : query) {
            double dist = 0.0;

            for (double[] c : chocolate) {
            	if (T >= c[0]) {
            		double radius = 8 * c[1] - (T - c[0]) / 12.0;
                    if (radius > 0) dist += radius;
            	}  
            }

            for (double[] c : coffee) {
            	if (T >= c[0]) {
            		double radius = 2 * c[1] - Math.pow(T - c[0], 2) / 79.0;
                    if (radius > 0) dist += radius;
            	}  
            }

            // 결과 출력
            if (dist < 1.0) {
                dist = 1.0;
            }
            System.out.println(T + " " + (Math.round(dist * 10) / 10.0));

        }
    }
}