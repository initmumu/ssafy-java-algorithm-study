import java.io.*;
import java.util.*;

public class BOJ_1043 {

    static int[] parent;
    static ArrayList<Integer>[] party;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 사람의 수
        int M = Integer.parseInt(st.nextToken());   // 파티의 수

        parent = new int[N + 1];
        party = new ArrayList[M];

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());   // 이야기의 진실을 아는 사람의 수

        int[] knowTruth = new int[K];

        // 진실을 아는 사람의 번호
        for (int k = 0; k < K; k++) {
            knowTruth[k] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            party[i] = new ArrayList<>();
            
            // 파티에 참석한 인원
            int partyPeople = Integer.parseInt(st.nextToken());
            for (int p = 0; p < partyPeople; p++) {
                party[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        
        // parent 배열 초기화
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            // 파티의 대표: 첫 번째 사람
            int rep = party[i].get(0);

            for (int j = 1; j < party[i].size(); j++) {
                // 같은 파티에 참석한 인원들은 같은 집합으로 분류
                union(rep, party[i].get(j));
            }
        }

        int count = 0;
        for (int i = 0; i < M; i++) {
            boolean possible = true;

            // 파티에 있는 사람들 중에 진실을 알고 있는 사람과 연결이 되있으면
            // 그 파티에서 거짓말을 할 수 없음
            for (int partyPerson: party[i]) {
                for (int knowPerson: knowTruth) {
                    if (find(partyPerson) == find(knowPerson)) {
                        possible = false;
                        break;
                    }
                }

                if (!possible) {
                    break;
                }
            }

            if (possible) {
                count++;
            }
        }

        System.out.println(count);
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }
}
