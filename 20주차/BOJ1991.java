import java.io.*;
import java.util.*;

public class Main {
	
	static HashMap<String, String[]> nodes;
	
	// 전위순회
	static void preorder(String node, StringBuilder sb) {
		if (!node.equals(".")) {
			sb.append(node);
			preorder(nodes.get(node)[0], sb);
			preorder(nodes.get(node)[1], sb);
		}
	}
	
	// 중위순회
	static void inorder(String node, StringBuilder sb) {
		if (!node.equals(".")) {
			inorder(nodes.get(node)[0], sb);
			sb.append(node);
			inorder(nodes.get(node)[1], sb);
		}
	}
	
	// 후위순회
	static void postorder(String node, StringBuilder sb) {
		if (!node.equals(".")) {
			postorder(nodes.get(node)[0], sb);
			postorder(nodes.get(node)[1], sb);
			sb.append(node);
		}
	}
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 노드 개수
		int N = Integer.parseInt(br.readLine());
		
		// 노드 정보 저장할 해시맵
		nodes = new HashMap<String, String[]>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String center = st.nextToken();
			String left = st.nextToken();
			String right = st.nextToken();
			
			nodes.put(center, new String[] {left, right});
		}
		
		StringBuilder sb = new StringBuilder();
		preorder("A", sb);
		System.out.println(sb);

		sb = new StringBuilder();
		inorder("A", sb);
		System.out.println(sb);
		
		sb = new StringBuilder();
		postorder("A", sb);
		System.out.println(sb);
		
	}
}