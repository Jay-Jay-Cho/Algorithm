package samsung;
import java.io.*;
import java.util.*;
public class BJ_16235_TreeInvestment {
	
	static class Tree{
		int x;
		int y;
		int age;
		Tree(int x, int y, int age){
			this.x = x;
			this.y = y;
			this.age = age;
		}
	}
	
	static int n,m,k;
	static int[][] current_soil;
	static int[][] add_soil;
	static List<Tree>[][] tree_map;
	static int tree_cnt = 0;
	static Queue<Tree> dead_tree;
	static int[] move_x = {-1,-1,0,1,1,1,0,-1};
	static int[] move_y = {0,1,1,1,0,-1,-1,-1};
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st1.nextToken());
		m = Integer.parseInt(st1.nextToken());
		k = Integer.parseInt(st1.nextToken());
		current_soil = new int[n][n];
		add_soil = new int[n][n];
		tree_map = new ArrayList[n][n];
		for(int i=0;i<n;i++) {
			Arrays.fill(current_soil[i], 5);
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				add_soil[i][j] = Integer.parseInt(st2.nextToken()); // null pointer
				tree_map[i][j] = new ArrayList<Tree>();
			}
		}
		for(int i=0;i<m;i++) {
			StringTokenizer st3 = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st3.nextToken())-1;
			int y = Integer.parseInt(st3.nextToken())-1;
			int age = Integer.parseInt(st3.nextToken());
			if(x<0 || x>=n || y<0 || y>=n) continue;
			tree_map[x][y].add(new Tree(x,y,age));
			tree_cnt++;
		}
		
		int year = 0;
		while(year<k) { // mistake year<=k
			// spring
			spring();
			if(tree_cnt == 0) break;
			
			// summer
			summer();
			
			// fall
			fall();
			
			// winter
			winter();
			
			year++;
		}
		System.out.println(tree_cnt);
	}
	static void spring() {
		dead_tree = new LinkedList<Tree>();
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(tree_map[i][j].isEmpty()) continue;
				else {
					int last_idx = tree_map[i][j].size()-1;
					for(int idx=last_idx;idx>=0;idx--) {
						Tree tree = tree_map[i][j].get(idx);
						if(current_soil[i][j]>=tree.age) {
							current_soil[i][j] -= tree.age;
							tree.age++;
							tree_map[i][j].set(idx, new Tree(i,j,tree.age));
						}else {
							dead_tree.add(tree);
							tree_map[i][j].remove(idx);
						}
					}
				}
			}
		}
		tree_cnt -= dead_tree.size();
	}
	
	static void summer() {
		while(!dead_tree.isEmpty()) {
			Tree tree = dead_tree.poll();
			int soil = tree.age/2;
			current_soil[tree.x][tree.y] += soil;
		}
	}
	
	static void fall() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(tree_map[i][j].isEmpty()) continue;
				else {
					
					int q_size = tree_map[i][j].size();
					for(int r=0;r<q_size;r++) {
						Tree tree = tree_map[i][j].get(r);
						if(tree.age%5 == 0) {
							for(int idx=0;idx<8;idx++) {
								int dx = tree.x + move_x[idx];
								int dy = tree.y + move_y[idx];
								if(dx<0 || dx>=n || dy<0 || dy>=n) continue;
								else {
									tree_map[dx][dy].add(new Tree(dx,dy,1));
									tree_cnt++;
								}
							}
						}
					}
				}
			}
		}
	}
	
	static void winter() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				current_soil[i][j] += add_soil[i][j];
			}
		}
	}

}
