package samsung;
import java.io.*;
import java.util.*;
public class BJ_17144_ByeByeDust {
	
	static int r,c,t;
	static int[][] map;
	static int[][] new_map;
	static int dust = 0;
	static boolean flag = true;
	static int row_cleaner_up = 0;
	static int row_cleaner_down = 0;
	static int[] spread_x = {-1,0,1,0};
	static int[] spread_y = {0,1,0,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] st1 = br.readLine().split(" ");
		r = Integer.parseInt(st1[0]);
		c = Integer.parseInt(st1[1]);
		t = Integer.parseInt(st1[2]);
		map = new int[r][c];
		for(int i=0;i<r;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<c;j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if(num==-1) {
					if(row_cleaner_up == 0) row_cleaner_up = i;
					else row_cleaner_down = i;
				}else {
					if(map[i][j]!=0) dust+=map[i][j];
				}
			}
		}
		if(dust==0) flag = false;
		int time = 1;
		while(flag && time<=t) {
			
			// spread
			boolean empty_check = spread();
			if(empty_check) {
				dust = 0;
				break;
			}
			
			// clean
			clean(true); //up
			clean(false);//down
			
			
			copyArray();
			time++;
		}
		System.out.println(dust);
	}
	static boolean spread() {
		new_map = new int[r][c];
		boolean isEmpty = true;
		for(int x=0;x<r;x++) {
			for(int y=0;y<c;y++) {
				int current_dust = map[x][y];
				if(current_dust == -1) new_map[x][y] = -1;	// mistake
				if(current_dust>0) {
					isEmpty = false;
					int cnt = 0;
					int spread_dust = current_dust/5;
					for(int i=0;i<4;i++) {
						int dx = x+spread_x[i];
						int dy = y+spread_y[i];
						if(dx<0 || dx>=r || dy<0 || dy>=c || map[dx][dy]==-1) continue;
						else {
							cnt++;
							new_map[dx][dy]+=spread_dust;
						}
					}
					//new_map[x][y] = current_dust - (spread_dust*cnt); //mistake
					new_map[x][y] += current_dust - (spread_dust*cnt);
				}
			}
		}
		return isEmpty;
	}
	
	static void clean(boolean up) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(0);
		
		int[] move_x;
		int[] move_y;
		int start_row;
		//up
		if(up) {
			start_row = row_cleaner_up;
			move_x = new int[]{0,-1,0,1};
			move_y = new int[]{1,0,-1,0};
		}
		//down
		else {
			start_row = row_cleaner_down;
			move_x = new int[]{0,1,0,-1};
			move_y = new int[]{1,0,-1,0};
		}
		
		int x = start_row;
		int y = 0;
		int idx = 0;
		while(true) {
			int dx = x+move_x[idx];
			int dy = y+move_y[idx];
			
			//if(new_map[dx][dy]==-1) break;
			
			if(dx<0 || dx>=r || dy<0 || dy>=c) {
				idx++;
				dx = x+move_x[idx];
				dy = y+move_y[idx];
			}
			
			if(new_map[dx][dy]==-1) break; // mistake
			
			int current = new_map[dx][dy];
			int prev = q.poll();
			q.offer(current);
			new_map[dx][dy] = prev;
			x = dx;
			y = dy;
		}
	}
	
	static void copyArray(){
		dust = 0;
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				int val = new_map[i][j];
				map[i][j] = val;
				if(val>0) dust+=val; 
			}
		}
	}
}
