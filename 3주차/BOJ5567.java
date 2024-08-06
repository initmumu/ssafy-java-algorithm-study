import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();
        ArrayList<Integer>[] graph = new ArrayList[N + 1];

        for(int i = 0; i < N + 1; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            int a = readInt();
            int b = readInt();

            graph[a].add(b);
            graph[b].add(a);
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(1);

        int result = 0;
        boolean[] visited = new boolean[N + 1];
        visited[1] = true;

        for(int i = 0; i < 2; i++){
            int qSize = q.size();
            for(int j = 0; j < qSize; j++) {
                for (int friend : graph[q.remove()]) {
                    if (visited[friend]) continue;
                    visited[friend] = true;
                    q.add(friend);
                    result++;
                }
            }
        }

        System.out.print(result);
    }

    public static int readInt() throws IOException {
        int input;
        int result = 0;
        while((input = System.in.read()) > 47) {
            result = (result<<3) + (result<<1) + (input & 15);
        }
        return result;
    }
}
