package samsung;
import java.io.*;
import java.util.*;
public class BJ_16236_BabyShark {
	
	static class Shark{
		int x;
		int y;
		int size;
		int eat;
		Shark(int x, int y, int size, int eat){
			this.x = x;
			this.y = y;
			this.size = size;
			this.eat = eat;
		}
	}
	
	static class Fish{
		int x;
		int y;
		int size;
		int cnt;
		Fish(int x, int y, int size, int cnt){
			this.x = x;
			this.y = y;
			this.size = size;
			this.cnt = cnt;
		}
	}
	
	static int n;
	static int[][] map;
	static int fish_cnt = 0;
	static Shark shark;
	static boolean flag = true;
	static int[] move_x = {-1,1,0,0};
	static int[] move_y = {0,0,-1,1};
	static PriorityQueue<Fish> pq;
	static Queue<Fish> q;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for(int i=0;i<n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if(num>0 && num<9) fish_cnt++;
				if(num==9) shark = new Shark(i,j,2,0);
			}
		}
		if(fish_cnt==0) flag = false;
		
		int time = 0;
		while(flag) {
			// find edible fishes
			find();
			if(pq.isEmpty()) break;	// mistake
				
			// shark moves & eat
			Fish fish = pq.poll();
			map[shark.x][shark.y] = 0; // mistake
			shark.x = fish.x;
			shark.y = fish.y;
			shark.eat++;
			time += fish.cnt;
			
			// update
			//map[fish.x][fish.y] = -1;
			map[fish.x][fish.y] = 0;
			if(shark.eat >= shark.size) {
				shark.eat = 0; //
				shark.size++;
			}
			fish_cnt--;
			
			if(fish_cnt==0) break;
			if(!isEdible()) break;
		}
		System.out.println(time);
	}
	
	static void find() {
		pq = new PriorityQueue<Fish>(new Comparator<Fish>() {
			@Override
			public int compare(Fish fish1, Fish fish2) {
				if(fish1.cnt < fish2.cnt) return -1;
				else if(fish1.cnt == fish2.cnt) {
					if(fish1.x < fish2.x) return -1;
					else if(fish1.x == fish2.x)
						if(fish1.y < fish2.y) return -1;
						else return 1;
					else return 1;
				}
				else return 1;
			}
		});
		boolean[][] visited = new boolean[n][n];
		q = new LinkedList<Fish>();
		q.add(new Fish(shark.x, shark.y, shark.size, 0));
		visited[shark.x][shark.y] = true; //
		int cnt = Integer.MAX_VALUE;
		//BFS
		while(!q.isEmpty()) {
			Fish fish  = q.poll();
			int x = fish.x;
			int y = fish.y;
			for(int i=0;i<4;i++) {
				int dx = x+move_x[i];
				int dy = y+move_y[i];
				int fish_cnt = fish.cnt + 1;
				if(dx>=0 && dx<n && dy>=0 && dy<n && map[dx][dy]>=0 && !visited[dx][dy] && map[dx][dy]<=shark.size && fish_cnt<=cnt) { // move
					int fish_size = map[dx][dy];
					q.add(new Fish(dx,dy,fish_size,fish_cnt));
					visited[dx][dy] = true;
					if(fish_size<shark.size && map[dx][dy]>0) {	// edible
						cnt = Math.min(fish_cnt, cnt);
						pq.add(new Fish(dx,dy,fish_size,fish_cnt));
					}
				}
			}
		}
	}
	
	static boolean isEdible() {
		boolean edible = false;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j] < shark.size && map[i][j]>0) { // mistake	
					edible = true;
					break;
				}
			}
			if(edible) break;
		}
		return edible;
	}

}
