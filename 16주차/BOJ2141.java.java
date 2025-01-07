import java.io.*;
import java.util.*;

class Town implements Comparable<Town>{
	int place;
	int people;
	
	public Town(int place, int people) {
		super();
		this.place = place;
		this.people = people;
	}

	@Override
	public int compareTo(Town o) {
		return Integer.compare(this.place, o.place);
	}	
}

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 마을 개수
		int N = Integer.parseInt(br.readLine());
		
		// 마을 정보
		Town[] towns = new Town[N];
		
		// 총 사람 수
		long totalPeople = 0;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int pl = Integer.parseInt(st.nextToken());
			int pe = Integer.parseInt(st.nextToken());
			towns[i] = new Town(pl, pe);
			totalPeople += pe;
		}
		
		// 마을 위치 기준 정렬
		Arrays.sort(towns);
		
		int target = 0;
		long tempPeopleCnt = 0;
		
		while (true) {			
			tempPeopleCnt += towns[target].people;
			
			if (tempPeopleCnt >= (totalPeople + 1) / 2) {
				break;
			}
			
			target++;
		}
		
		System.out.println(towns[target].place);
	}
}