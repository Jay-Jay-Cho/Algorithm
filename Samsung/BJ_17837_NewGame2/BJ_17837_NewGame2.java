package samsung;
import java.io.*;
import java.util.*;
public class BJ_17837_NewGame2 {
	
	static class Horse{
		int num;
		int x;
		int y;
		int direction;
		Horse(int num, int x, int y, int direction){
			this.num = num;
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
		
	}
	
	static int N, K;
	static int[][] map;
	static Stack<Integer>[][] real_map;
	static int answer = -1;
	static int[] move_x = {0,0,-1,1};
	static int[] move_y = {1,-1,0,0};
	static HashMap<Integer,Horse> location;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st1.nextToken());
		map = new int[N+1][N+1];
		real_map = new Stack[N+1][N+1];
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				real_map[i][j] = new Stack<Integer>();
			}
		}
		
		for(int i=0;i<=N;i++) {
			for(int j=0;j<=N;j++) {
				if(i==0 || j==0) map[i][j]=-1;
			}
		}
		
		K = Integer.parseInt(st1.nextToken());
		for(int i=1;i<=N;i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				map[i][j] = Integer.parseInt(st2.nextToken());
			}
		}
		
		location = new HashMap<Integer,Horse>();
		for(int i=0;i<K;i++) {
			String[] pos_info = br.readLine().split(" ");
			int x = Integer.parseInt(pos_info[0]);
			int y = Integer.parseInt(pos_info[1]);
			int direction = Integer.parseInt(pos_info[2]);
			location.put(i+1, new Horse(i+1,x,y,direction));
			real_map[x][y].push(i+1);
		}
		
		
		int cnt=1;
		boolean flag = true;
		while(cnt<1000 && flag) {
			for(int turn=1;turn<=K;turn++) {
				Horse horse = location.get(turn);
				int x = horse.x;
				int y = horse.y;
				int direction = horse.direction;
				
				
				// extract
				Queue<Integer> q = new LinkedList<Integer>();
				if(real_map[x][y].size()!=1) {
					while(real_map[x][y].peek()!=horse.num) {	//everyday mistake!!!!!!
						q.offer(real_map[x][y].pop());
					}
					q.offer(real_map[x][y].pop());
					int size = q.size();
					Stack<Integer> stack = new Stack<Integer>();
					for(int i=0;i<size;i++) {
						stack.push(q.poll());
					}
					for(int i=0;i<size;i++) {
						q.offer(stack.pop());
					}
					
				}else {
					int num = real_map[x][y].pop();
					q.offer(num);
				}
				
				// move
				int dx = x+move_x[direction-1];
				int dy = y+move_y[direction-1];
				if(dx>N || dx<1 || dy>N || dy<1 || map[dx][dy]==2 ) { //blue
					// change direction
					int new_direction = changeDirection(direction);
					int new_dx = x+move_x[new_direction-1];
					int new_dy = y+move_y[new_direction-1];
					
					if(new_dx>N || new_dx<1 || new_dy>N || new_dy<1 || map[new_dx][new_dy]==2) { //blue
						int size = q.size();
						for(int i=0;i<size;i++) {
							int input = q.poll();
							if(i==0) {
								real_map[x][y].push(input);
								location.replace(input, new Horse(input,x,y,new_direction));
							}else {
								real_map[x][y].push(input);
								Horse orgin_horse = location.get(input);
								location.replace(input, new Horse(input,x,y,orgin_horse.direction));
							}
						}
						location.replace(turn, new Horse(turn,x,y,new_direction));
					}else if(map[new_dx][new_dy]==0) { //white
						moveWhite(q,new_dx,new_dy,new_direction);
					}
					else { //red
						moveRed(q,new_dx,new_dy,new_direction);
					}
				}
				else if(map[dx][dy]==0) { //white
					moveWhite(q,dx,dy);
				}
				//red
				else { 
					moveRed(q,dx,dy);
				}
				
				for(int i=1;i<=N;i++) {
					for(int j=1;j<=N;j++) {
						if(real_map[i][j].size()>=4) {
							answer = cnt;
							flag=false;
						}
					}
				}
			}
			cnt++;
		}
		
		
		System.out.println(answer);
	}
	
	static int changeDirection(int x) {
		if(x==1) return 2;
		else if(x==2) return 1;
		else if(x==3) return 4;
		else return 3;
	}
	
	static void moveRed(Queue<Integer> q, int dx, int dy) {
		Stack<Integer> stack = new Stack<Integer>();
		int size = q.size();
		for(int i=0;i<size;i++) {
			stack.push(q.poll());
		}
		for(int i=0;i<size;i++) {
			int input = stack.pop();
			real_map[dx][dy].push(input);
			Horse orgin_horse = location.get(input);
			location.replace(input, new Horse(input,dx,dy,orgin_horse.direction));			
		}
	}
	
	// blue -> red
	static void moveRed(Queue<Integer> q, int dx, int dy,int new_direction) {
		int size = q.size();
		int change_num = 0;
		Stack<Integer> stack = new Stack<Integer>();
		for(int i=0;i<size;i++) {
			if(i==0) {
				change_num = q.poll();
				stack.push(change_num);
			}else {
				stack.push(q.poll());
			}
			
		}
		for(int i=0;i<size;i++) {
			int input = stack.pop();
			real_map[dx][dy].push(input);
			if(input==change_num) {
				location.replace(input, new Horse(input,dx,dy,new_direction));	
			}else {
				Horse h = location.get(input);
				location.replace(input, new Horse(input,dx,dy,h.direction));	
			}
					
		}
	} 
	
	static void moveWhite(Queue<Integer> q, int dx, int dy) {
		int size = q.size();
		for(int i=0;i<size;i++) {
			int input = q.poll();
			real_map[dx][dy].push(input);
			Horse orgin_horse = location.get(input);
			location.replace(input, new Horse(input,dx,dy,orgin_horse.direction));	
		}
	}
	
	// blue -> white
	static void moveWhite(Queue<Integer> q, int dx, int dy, int new_direction) {
		int size = q.size();
		for(int i=0;i<size;i++) {
			int input = q.poll();
			real_map[dx][dy].push(input);
			if(i==0) {
				location.replace(input, new Horse(input,dx,dy,new_direction));	
			}else {
				Horse h = location.get(input);
				location.replace(input, new Horse(input,dx,dy,h.direction));	
			}
		}
	}
	

}
