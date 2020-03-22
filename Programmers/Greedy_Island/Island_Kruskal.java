package greedy;
import java.util.Arrays;
import java.util.Comparator;
public class Island_Kruskal {
	
	static int[] parent;
	
	static int find(int x) {
		if(x == parent[x]) return x;
		else {
			int y = find(parent[x]);
			parent[x] = y;
			return y;
		}
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x!=y) {
			if(x<y) {
				parent[y] = x;
			}else {
				parent[x] = y;
			}
		}
	}
	
	static boolean isSame(int x, int y) {
		return find(x) == find(y);
	}
	
	
	
	static int solution(int n, int[][] costs) {
		int answer = 0;
		
		parent = new int[n];
		for(int i=0;i<n;i++) parent[i] = i;
		
		Arrays.sort(costs,new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				if(a[2]<=b[2]) return -1;
				else return 1;
			}
		});
		
		boolean[] visit = new boolean[n];
		int cnt = 0;
		int i = 0;
		while(cnt<n-1) {
//			for(int i=0;i<n;i++) {
//				if(!visit[i] && !isSame(costs[i][0],costs[i][1])) {
//					visit[i] = true;
//					union(costs[i][0],costs[i][1]);
//					cnt++;
//					answer += costs[i][2];
//					break;
//				}
//			}
			
			if(!isSame(costs[i][0],costs[i][1])) {
				//visit[i] = true; 실수.
				union(costs[i][0],costs[i][1]);
				cnt++;
				answer += costs[i][2];
			}
			
			i++;
			
		}
		
		return answer;
	}


	public static void main(String[] args) {
		int n = 4;
		int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
		
		
		
		int ans = solution(n,costs);
		System.out.println(ans);
	}

}
