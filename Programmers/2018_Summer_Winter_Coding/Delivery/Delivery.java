package summer_winter_coding_2018;

public class Delivery {
	
	static int solution(int N, int[][] road, int K) {
        int answer = 0;
        int infinite = Integer.MAX_VALUE/3;	
        int[][] map = new int[N+1][N+1];
        for(int i=1;i<map.length;i++) {
        	for(int j=1;j<map.length;j++) {
        		if(i==j) map[i][j] = 0;
        		else map[i][j] = infinite;
        	}
        }
        
        for(int i=0;i<road.length;i++) {
        	int from = road[i][0];
        	int to = road[i][1];
        	int cost = road[i][2];
        	if(map[from][to]!= 0 || map[from][to]!= infinite) {
        		if(map[from][to]<cost) continue;
        		else {
        			map[from][to] = cost;
                	map[to][from] = cost;
        		}
        	}
        }
        
        for(int k=1;k<=N;k++){	// 중간 경유 
            for(int i=1;i<=N;i++) {	// 시작 
            	for(int j=1;j<=N;j++) {	// 도착 
            		if(map[i][j] > map[i][k]+map[k][j]) {
            			map[i][j] = map[i][k]+map[k][j];
            		}
            	}
            }
        }
        
        for(int i=1;i<=N;i++) {
        	if(map[1][i]<=K) answer++;
        }

        return answer;
    }

	public static void main(String[] args) {
		int N = 6;
		int[][] road = {{1,2,1},{1,3,2},{2,3,2},{3,4,3},{3,5,2},{3,5,3},{5,6,1}};
		int K = 4;
		int ans = solution(N,road,K);
		System.out.println(ans);

	}

}
