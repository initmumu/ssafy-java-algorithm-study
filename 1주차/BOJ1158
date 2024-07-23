
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 String[] i1 = br.readLine().split(" ");
		 int N = Integer.parseInt(i1[0]), K = Integer.parseInt(i1[1]);
		 
		 List<Integer> p = new LinkedList<>();
		 for(int i = 1; i <= N; i++) p.add(i);
		 int[] peopleArr = new int[N];
		 
		 int j = 0;
		 ListIterator<Integer> li = p.listIterator();
		 while(!p.isEmpty()) {
			 for(int i = 0; i < K - 1; i++) {
				 if (li.hasNext()) {
					 li.next();
				 }
				 else {
					 li = p.listIterator(0);
					 li.next();
				 }
			 }
			 if(!li.hasNext()) li = p.listIterator(0);
			 peopleArr[j++] = li.next();
			
			 li.remove();
		 }
		 
		 StringBuilder sb = new StringBuilder();
		 sb.append('<');
		 for(int i = 0; i < N; i++) {
			 if (i != N-1)
				 sb.append(peopleArr[i]+", ");
			 else sb.append(peopleArr[i]);
		 }
		 sb.append('>');
		 
		 System.out.println(sb);
	}

}
