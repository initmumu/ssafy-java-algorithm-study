import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1068 {

    static List<Integer>[] tree;
    static int removeNode;
    static int count = 0;

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        int root = -1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
            }
            else {
                tree[parent].add(i);
            }
        }

        removeNode = Integer.parseInt(br.readLine());

        if (root == removeNode) {
            System.out.println(0);
        }
        else {
            countLeafNodes(root);
            System.out.println(count);
        }
    }

    static void countLeafNodes(int currentNode) {
        // 현재 노드가 제거 대상인 경우
        if (currentNode == removeNode) {
            return;
        }

        // 리프 노드인지 확인
        // 리프 노드의 조건 1: 자식 노드가 없는 경우
        // 리프 노드의 조건 2: 자식이 제거할 노드뿐인 경우
        if (tree[currentNode].isEmpty() || (tree[currentNode].size() == 1 && tree[currentNode].contains(removeNode))) {
            count++;
            return;
        }

        // 자식 노드 탐색
        for (int childNode : tree[currentNode]) {
            countLeafNodes(childNode);
        }
    }
}
