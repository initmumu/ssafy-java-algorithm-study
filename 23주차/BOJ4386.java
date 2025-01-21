import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static Star[] stars;
    static HashSet<Integer> nonVisited;
    static HashSet<Integer> visited;

    public static class Star{
        public double x;
        public double y;

        public Star(double x, double y){
            this.x = x;
            this.y = y;
        }
    }

    public static double getDistance(Star a, Star b){
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        stars = new Star[N];
        nonVisited = new HashSet<>();
        visited = new HashSet<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            stars[i] = new Star(x, y);
            nonVisited.add(i);
        }

        visited.add(0);
        double result = 0;

        while(!nonVisited.isEmpty()){
            int minIdx = -1;
            double minDist = Double.MAX_VALUE;

            for(int i : visited){
                for(int j : nonVisited){
                    double dist = getDistance(stars[i], stars[j]);

                    if(dist < minDist){
                        minDist = dist;
                        minIdx = j;
                    }
                }
            }

            nonVisited.remove(minIdx);
            visited.add(minIdx);
            result += minDist;
        }

        System.out.print(Math.floor(result * 100) / 100.0);
    }
}
