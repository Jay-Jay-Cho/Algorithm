package samsung;
import java.io.*;
import java.util.*;
public class BJ_17822_Disk {
	
	static class Pos{
		int x;
		int y;
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M, T;
	static int x,d,k;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] first_line = br.readLine().split(" ");
		N = Integer.parseInt(first_line[0]);
		M = Integer.parseInt(first_line[1]);
		T = Integer.parseInt(first_line[2]);
		map = new int[N+1][M];
		
		for(int row=1;row<=N;row++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int col=0;col<M;col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int t=0;t<T;t++) {
			boolean change = false;
			Queue<Pos> q = new LinkedList<Pos>();
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st2.nextToken());	// which disk
			d = Integer.parseInt(st2.nextToken());	// 0 = clock, 1 = reverse_clock
			k = Integer.parseInt(st2.nextToken());	// k = turn count
			
			// rotate
			for(int i=1;i<=N;i++) {
				if(i%x == 0) {
					int[] copy_arr = map[i].clone();
					for(int idx=0;idx<M;idx++) {
						int new_idx;
						if(d==1) new_idx = (idx+k) % M;	// reverse_clock
						else new_idx = (M-k+idx) % M;	// clock
						map[i][idx] = copy_arr[new_idx];
					}
				}
			}
			
			// remove
			// inner
			for(int i=1;i<=N;i++) {
				for(int j=0;j<M-1;j++) {
					if(map[i][j] != 9999 && map[i][j+1] != 9999 && map[i][j] == map[i][j+1]) {
						change = true;
						q.offer(new Pos(i,j));
						q.offer(new Pos(i,j+1));
					}
				}
				if(map[i][0] != 9999 && map[i][M-1] != 9999 && map[i][0] == map[i][M-1]) {
					change = true;
					q.offer(new Pos(i,0));
					q.offer(new Pos(i,M-1));
				}
			}
			
			// outer
			for(int j=0;j<M;j++) {
				for(int i=1;i<=N-1;i++) {
					if(map[i][j] != 9999 && map[i+1][j] != 9999 && map[i][j]==map[i+1][j]) {
						change = true;
						q.offer(new Pos(i,j));
						q.offer(new Pos(i+1,j));
					}
				}
			}
			
			if(change) {
				while(!q.isEmpty()){
					Pos pos = q.poll();
					map[pos.x][pos.y] = 9999;
				}
			}else {
				int sum = 0;
				int cnt = 0;
				for(int i=1;i<=N;i++) {
					for(int j=0;j<M;j++) {
						if(map[i][j]!=9999) {
							sum+=map[i][j];
							cnt++;
						}
					}
				}
				if(cnt == 0) continue; // mistake
				
				double avg = (double)sum/cnt;	// mistake
				for(int i=1;i<=N;i++) {
					for(int j=0;j<M;j++) {
						if(map[i][j]!=9999) {
							if(map[i][j]==avg) continue;
							else if(map[i][j] > avg){
								map[i][j] = map[i][j]-1;
							}else {
								map[i][j] = map[i][j]+1;
							}
						}
					}
//					for(int val:map[i]) {	// mistake
//						if(val!=9999) {
//							if(val > avg) val-=1;
//							if(val < avg) val+=1;
//						}
//					}
				}
			}
			
		}
		
		int sum = 0;
		for(int i=1;i<=N;i++) {
			for(int val:map[i]) {
				if(val!=9999) sum += val;
			}
		}
		System.out.println(sum);
		
	}

}
