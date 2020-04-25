package samsung;
import java.io.*;
import java.util.*;
public class BJ_17779_Gerrymandering {

	static int N;
	static int[][] map;
	static int[][] copy_map;
	static int answer = Integer.MAX_VALUE;
	static int d1;
	static int d2;
	
	static class Pair{
		int d1;
		int d2;
		Pair(int d1, int d2){
			this.d1 = d1;
			this.d2 = d2;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// mistake
		ArrayList<Pair> list = new ArrayList<Pair>(); 
		for(int i=1;i<=N-1;i++) {
			for(int j=1;j<=N-1;j++) {
				if(i+j<=N-1) list.add(new Pair(i,j));
			}
		}
		
		// find x,y,d1,d2
		for(int x=0;x<N;x++) {
			for(int y=0;y<N;y++) {
				for(int list_idx=0;list_idx<list.size();list_idx++) {
					Pair pair = list.get(list_idx);
					d1 = pair.d1;
					d2 = pair.d2;
					
					if(0<=x && x<x+d1+d2 && x+d1+d2<=N-1) {
						if(0<=y-d1 && y-d1<y && y<y+d2 && y+d2<=N-1) {
							int[] count_region = new int[6];
							
							copy_map = new int[N][N];
							int[] x_arr = {x,x,x+d1,x+d2};
							int[] y_arr = {y,y,y-d1,y+d2};
							int[] move = {d1,d2,d2,d1};
							for(int i=0;i<4;i++) {
								int cnt = 0;
								int temp_x = x_arr[i];
								int temp_y = y_arr[i];
								while(cnt<=move[i]) { // mistake
									if(i==0 || i==3) {
										int target_x = temp_x+cnt;
										int target_y = temp_y-cnt;
										if(copy_map[target_x][target_y]!=5) { // mistake
											copy_map[temp_x+cnt][temp_y-cnt] = 5;
											count_region[5] += map[temp_x+cnt][temp_y-cnt];
										}
									}
									else{
										int target_x = temp_x+cnt;
										int target_y = temp_y+cnt;
										if(copy_map[target_x][target_y]!=5) {
											copy_map[temp_x+cnt][temp_y+cnt] = 5;
											count_region[5] += map[temp_x+cnt][temp_y+cnt];
										}
									}
									cnt++;
								}
							}
							for(int i=0;i<N;i++) {
								Queue<Integer> q = new LinkedList<Integer>();
								for(int j=0;j<N;j++) {
									if(copy_map[i][j] == 5) q.offer(j);
								}
								if(q.size()<=1) continue;
								else {
									int from = q.poll();
									int to = q.poll();
									for(int idx=from+1;idx<to;idx++) {
										copy_map[i][idx] = 5;
										count_region[5] += map[i][idx];
									}
								}
							}
							
							// fill 1~4
							for(int i=0;i<N;i++) {
								for(int j=0;j<N;j++) {
									if(copy_map[i][j]==5) continue;
									else {
										int region = getRegion(i,j,x,y,d1,d2);
										copy_map[i][j] = region;
										count_region[region] += map[i][j];
									}
								}
							}
							// calculate sum
							int max = Integer.MIN_VALUE;
							int min = Integer.MAX_VALUE;
							for(int i=1;i<=5;i++) {
								max = Math.max(max, count_region[i]);
								min = Math.min(min, count_region[i]);
							}
							
							answer = Math.min(answer, Math.abs(max-min));
						}
					}
				}
			}
		}
		
		System.out.println(answer);
	}
	
	static int getRegion(int r, int c, int x, int y, int d1, int d2) {
		if(0<=r && r<x+d1 && 0<=c && c<=y) return 1;
		else if(0<=r && r<=x+d2 && y<c && c<=N+1) return 2;
		else if(x+d1<=r && r<=N+1 && 0<=c && c<y-d1+d2) return 3;
		else return 4;
	}

}
