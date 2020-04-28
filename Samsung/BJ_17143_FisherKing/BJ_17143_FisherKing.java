package samsung;
import java.io.*;
import java.util.*;
public class BJ_17143_FisherKing {
	
	static class Shark{
		int x;
		int y;
		int speed;
		int direction;
		int size;
		Shark(int x, int y, int speed, int direction, int size){
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.direction = direction;
			this.size = size;
		}
	}
	
	static int r,c,m;
	static Shark[][] map;
	static boolean flag = true;
	static int fisher = 0;
	static int weight = 0;
	static Queue<Shark> survived_sharks;
	static int[] move_x = {0,-1,1,0,0}; //
	static int[] move_y = {0,0,0,1,-1};
	static int stay_vertical;
	static int stay_horizontal;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] st1 = br.readLine().split(" ");
		r = Integer.parseInt(st1[0]);
		stay_vertical = r*2-2;
		c = Integer.parseInt(st1[1]);
		stay_horizontal = c*2-2;
		m = Integer.parseInt(st1[2]);
		if(m==0) flag = false;
		map = new Shark[r][c];
		PriorityQueue<Shark> temp_survived_sharks = new PriorityQueue<Shark>(new Comparator<Shark>() {
			@Override
			public int compare(Shark shark1, Shark shark2) {
				if(shark1.size > shark2.size) return -1;
				else return 1;
			}
		});
		survived_sharks = new LinkedList<Shark>();
		for(int i=0;i<m;i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st2.nextToken())-1; //
			int y = Integer.parseInt(st2.nextToken())-1; //
			int speed = Integer.parseInt(st2.nextToken());
			int direction = Integer.parseInt(st2.nextToken());
			int size = Integer.parseInt(st2.nextToken());
			Shark shark = new Shark(x,y,speed,direction,size);
			map[x][y] = shark;
			survived_sharks.add(shark);
		}
		
		
		
		
		while(flag) {
			
			// catch shark
			for(int row=0;row<r;row++) {
				if(map[row][fisher]!=null) {
					weight+=map[row][fisher].size;
					map[row][fisher]=null;	// remove from array
					// remove from q
					while(true) {
						Shark shark = survived_sharks.poll();
						if(shark.x == row && shark.y == fisher) {
							break;
						}
						else survived_sharks.offer(shark);
					}
					m--;
					break;
				}
			}
			
			// sharks move
			int size = survived_sharks.size();
			for(int i=0;i<size;i++) {
				Shark shark = survived_sharks.poll();
				int x = shark.x;
				int y = shark.y;
				int direction = shark.direction;
				int speed;
				if(direction<=2) {
					speed = shark.speed%stay_vertical;
				}
				else {
					speed = shark.speed%stay_horizontal;
				}
				
				
				int shark_size = shark.size;
				for(int move=0;move<shark.speed;move++) {
					int dx = x+move_x[direction];
					int dy = y+move_y[direction];
					if(dx<0 || dx>=r || dy<0 || dy>=c) { // out the range
						direction = changeDirection(direction);
						x = x+move_x[direction]; // x = dx+move_x[direction];
						y = y+move_y[direction];
					}else {
						x = dx;
						y = dy;
					}
				}
				//survived_sharks.add(new Shark(x,y,speed,direction,shark_size));
				temp_survived_sharks.offer(new Shark(x,y,speed,direction,shark_size));
			}
			
			// empty the map
			for(int row=0;row<r;row++) {
				Arrays.fill(map[row], null);
			}
			// locate the shark
			int q_size = temp_survived_sharks.size();
			for(int i=0;i<q_size;i++) {
				Shark shark = temp_survived_sharks.poll();
				int x = shark.x;
				int y = shark.y;
				
				
				if(map[x][y]==null) {
					map[x][y] = shark;
					survived_sharks.offer(shark);
				}
				else m--;
			}
			fisher++;	// fisher moves
			if(fisher>=c || m<=0) flag  = false; // while condition mistake
		}
		System.out.println(weight);
	}
	
	static int changeDirection(int num) {
		if(num==1) return 2;
		else if(num==2) return 1;
		else if(num==3) return 4;
		else return 3;
	}

}
