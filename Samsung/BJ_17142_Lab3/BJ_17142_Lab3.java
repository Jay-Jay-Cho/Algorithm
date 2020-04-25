package samsung;
import java.io.*;
import java.util.*;
public class BJ_17142_Lab3 {
	
	static class Pos{
		int x;
		int y;
		Pos(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M;
	static int[][] map;
	static ArrayList<Pos> list;
	static int[] sub_virus;
	static Stack<String> stack;
	static int[] cnt_arr = new int[4];
	static int cnt_sum = 0;
	static int answer = Integer.MAX_VALUE;
	static int[] move_x = {-1,0,1,0};
	static int[] move_y = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] first_line = br.readLine().split(" ");
		N = Integer.parseInt(first_line[0]);
		map = new int[N][N];
		M = Integer.parseInt(first_line[1]);
		list = new ArrayList<Pos>();
		
		
		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				int value = Integer.parseInt(st.nextToken());
				map[i][j] = value;
				cnt_arr[value]++;
				if(value==2) list.add(new Pos(i,j));
			}
		}
		
		// Exceptional Case = no virus
		for(int i=0;i<3;i++) {
			cnt_sum+=cnt_arr[i];
		}
		if(cnt_sum == cnt_arr[0] || cnt_sum == cnt_arr[1]) {
			answer = -2;
		}
		
		
		int size = list.size();
		sub_virus = new int[size];
		boolean[] used = new boolean[size];
		for(int i=0;i<size;i++) sub_virus[i]=i;
		stack = new Stack<String>();
		combination(sub_virus,used,0,M);
		
		
		
		while(!stack.isEmpty() && answer!=-2) { //mistake
			int empty_cnt = cnt_arr[0];
			String initial_virus = stack.pop();
			
			// copy the map
			int[][] copy_map = new int[N][N]; // location mistake 
			for(int i=0;i<N;i++) {
				copy_map[i] = map[i].clone();
			}
			boolean[][] visited = new boolean[N][N];
			for(int i=0;i<N;i++) {
				Arrays.fill(visited[i],false);
			}
			
			Queue<Pos> q = new LinkedList<Pos>();
			
			boolean flag = true;
			int time = 1;
			while(flag) {
				boolean change = false; // mistake
				if(time==1) {
					for(int idx=0;idx<M;idx++) {
						Pos pos = list.get(initial_virus.charAt(idx)-'0');
						int x = pos.x;
						int y = pos.y;
						visited[x][y] = true;
						for(int i=0;i<4;i++) {
							int dx = x+move_x[i];
							int dy = y+move_y[i];
							if(dx>=0 && dx<N && dy>=0 && dy<N && copy_map[dx][dy]!=1 && !visited[dx][dy]) {
								if(copy_map[dx][dy]==0) {
									empty_cnt--;
									q.offer(new Pos(dx,dy));
									copy_map[dx][dy] = 2; // mistake
								}else {
									q.offer(new Pos(dx,dy));
								}
								change = true;
								visited[dx][dy] = true;
							}
						}
					}
				}else {
					int q_size = q.size(); //mistake
					for(int k=0;k<q_size;k++) {
						Pos pos = q.poll();
						int x = pos.x;
						int y = pos.y;
						for(int i=0;i<4;i++) {
							int dx = x+move_x[i];
							int dy = y+move_y[i];
							if(dx>=0 && dx<N && dy>=0 && dy<N && copy_map[dx][dy]!=1 && !visited[dx][dy]) {
								if(copy_map[dx][dy]==0) {
									empty_cnt--;
									q.offer(new Pos(dx,dy));
									copy_map[dx][dy] = 2; // mistake
								}else {
									q.offer(new Pos(dx,dy));
								}
								visited[dx][dy] = true;
								change = true;
							}
						}
						//q.offer(pos); -----> 시간초과 원흉  
					}
				}
				if(time>=answer) flag=false;
				if(empty_cnt<=0) {
					if(time==1 && cnt_arr[0]==0) {
						answer = 0; //mistake
					}
					else answer =  Math.min(answer, time);
					flag = false;
				}
				if(!change) {
					flag = false;
				}
				time++;
			}
		}
		if(answer == Integer.MAX_VALUE) answer = -1;
		System.out.println(answer);
	}
	
	static void combination(int[] arr, boolean[] visited, int depth, int r) {
		if(r==0) {
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<arr.length;i++) {
				if(visited[i]) {
					sb.append(arr[i]);
				}
			}
			stack.push(sb.toString());
			return;
		}
		
		for(int i=depth;i<arr.length;i++) {
			visited[i] = true;
			combination(arr,visited,i+1,r-1);
			visited[i] = false; // backtracking
		}
	}
	
	
	
}
