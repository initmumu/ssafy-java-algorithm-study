import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            SortedMap<Integer, Integer> doublePriorityQueue = new TreeMap<>();
            int N = Integer.parseInt(br.readLine());

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());

                String command = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                switch(command){
                    case "I":
                        if(doublePriorityQueue.containsKey(num)){
                            doublePriorityQueue.put(num, doublePriorityQueue.get(num) + 1);
                        } else {
                            doublePriorityQueue.put(num, 1);
                        }
                        break;
                    case "D":
                        if(doublePriorityQueue.isEmpty()){
                            break;
                        } else {
                            if(num == 1){
                                int max = doublePriorityQueue.lastKey();
                                if(doublePriorityQueue.get(max) == 1){
                                    doublePriorityQueue.remove(max);
                                } else {
                                    doublePriorityQueue.put(max, doublePriorityQueue.get(max) - 1);
                                }
                            } else {
                                int min = doublePriorityQueue.firstKey();
                                if(doublePriorityQueue.get(min) == 1){
                                    doublePriorityQueue.remove(min);
                                } else {
                                    doublePriorityQueue.put(min, doublePriorityQueue.get(min) - 1);
                                }
                            }
                        }
                        break;
                }
            }
            sb.append(doublePriorityQueue.isEmpty() ? "EMPTY" : doublePriorityQueue.lastKey() + " " + doublePriorityQueue.firstKey()).append("\n");
        }

        System.out.print(sb);
    }

}
