package algorithm;

import java.io.*;
import java.util.*;

	public class Main {
		
		static int N, M;
		static int[] nums, arr;
		static StringBuilder sb;
		
		static void dfs(int depth) {
			
			if (depth == M) {
				for (int a : arr) {
					sb.append(a).append(" ");
				}
				sb.append("\n");
				return;
			}
			
			for (int i = 0; i < N; i++) {
				arr[depth] = nums[i];
				dfs(depth + 1);
			}
		}
		
	    public static void main(String[] args) throws Exception{
	         
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        N = Integer.parseInt(st.nextToken());
	        M = Integer.parseInt(st.nextToken());
	        
	        nums = new int[N];
	        st = new StringTokenizer(br.readLine());
	        for (int i = 0; i < N; i++) {
	        	nums[i] = Integer.parseInt(st.nextToken());
	        }
	        
	        Arrays.sort(nums);
	        arr = new int[M];
	        
			sb = new StringBuilder();
	        
	        dfs(0);
	        
	        System.out.println(sb);
	        
	    }
	}