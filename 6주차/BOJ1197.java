import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    static int V, E;
    static int[][] map;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parent = new int[V + 1];
        map = new int[E][3];
        for(int i = 0; i <= V; i++){
            parent[i] = i;
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
            map[i][2] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(map, (o1, o2) -> o1[2] - o2[2]);

        int a, b, result = 0;
        for(int[] node : map){
            if(GetParent(node[0]) != GetParent(node[1])){
                a = GetParent(node[0]);
                b = GetParent(node[1]);

                parent[a] = b;
                result += node[2];
            }
        }

        System.out.print(result);
    }

    public static int GetParent(int x) {
        if(parent[x] != x){
            parent[x] = GetParent(parent[x]);
        }
        return parent[x];
    }
}