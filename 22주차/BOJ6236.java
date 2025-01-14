import java.io.*;
import java.util.*;

public class BOJ6236 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[] days = new int[N];
        int maxExpense = 0;
        int totalExpense = 0;
        
        for (int i = 0; i < N; i++) {
            days[i] = Integer.parseInt(br.readLine());
            maxExpense = Math.max(maxExpense, days[i]);
            totalExpense += days[i];
        }
        
        int low = maxExpense;
        int high = totalExpense;
        
        while (low < high) {
            int mid = (low + high) / 2;
            if (isPossible(mid, days, M)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        
        System.out.println(low);
    }
    

    static boolean isPossible(int withdraw, int[] days, int maxWithdrawals) {
        int count = 1;
        int balance = withdraw;
        
        for (int day : days) {
            if (balance < day) {
                count++;
                balance = withdraw;
            }
            balance -= day;
            if (count > maxWithdrawals) return false;
        }
        
        return true;
    }
}