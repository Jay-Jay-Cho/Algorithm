package summer_winter_coding_2019;
import java.util.*;
public class LandMovement {
	
	static int[] parent;
	
	static int find(int x) {
		if(x == parent[x]) return x;
		else {
			int y = find(parent[x]);
			parent[x] = y;
			return y;
		}
	}

	// 합집합 = 부모 노드를 하나로 통일
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

	// 같은 집합인지 확인
	static boolean isSame(int x, int y) {
		return find(x) == find(y);
	}
	
	static class Node{
		int x;
		int y;
		int h;
		Node(int x, int y, int h){
			this.x = x;
			this.y = y;
			this.h = h;
		}
	}
	
	static class Border{
		int num1;
		int num2;
		int diff;
		Border(int num1, int num2, int diff){
			this.num1 = num1;
			this.num2 = num2;
			this.diff = diff;
		}
	}
	
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	static int solution(int[][] land, int height) {
        int answer = 0;
        int n = land.length;
        int[][] map = new int[n][n];
        
        ArrayList<Border> list = new ArrayList<Border>();
        // segmentation
        int num = 1;
        for(int i=0;i<n;i++) {
        	for(int j=0;j<n;j++) {
        		if(map[i][j]==0) {
        			Queue<Node> q = new LinkedList<Node>();
        			q.offer(new Node(i,j,land[i][j]));
        			while(!q.isEmpty()) {
        				Node node = q.poll();
        				map[node.x][node.y] = num;
        				for(int k=0;k<4;k++) {
        					int xx = node.x+dx[k];
        					int yy = node.y+dy[k];
        					if(xx>=0 && xx<n && yy>=0 && yy<n){
        						Node temp_node = new Node(xx,yy,land[xx][yy]);
        						if(map[xx][yy]==0 && Math.abs(node.h-land[xx][yy])<=height) {
        							q.add(temp_node);
        						}
        						if(map[xx][yy]!=0 && Math.abs(node.h-land[xx][yy])>height) {
        							int temp_num = map[xx][yy];
        							int diff = Math.abs(land[node.x][node.y]-land[xx][yy]);
        							if(temp_num!=num) {
        								list.add(new Border(temp_num,num,diff));
        							}
        						}
        					}
        				}
        			}
        			num++;
        		}
        	}
        }
        
        if(list.size()==0) return 0;
        
        Collections.sort(list, new Comparator<Border>() {
			public int compare(Border b1, Border b2) {
				if(b1.diff<b2.diff) return -1;
				else if(b1.diff==b2.diff) {
					if(b1.num1<=b2.num1) return -1;
					else return 1;
				}else return 1;
			}
        	
        });
        
        parent = new int[num];
        for(int i=1;i<parent.length;i++) {
        	parent[i] = i;
        }
        
        int cnt = 0;
		int i = 0;
		while(cnt<(num-1)-1) {
			if(!isSame(list.get(i).num1,list.get(i).num2)) {	// MST 미포함 간선이면, 
		      union(list.get(i).num1,list.get(i).num2);	// MST에 포함 
		      cnt++;
		      answer += list.get(i).diff;
		    }
		    i++;
		}
        
        
        return answer;
    }

	public static void main(String[] args) {
		int[][] land = {{1, 4, 8, 10}, {5, 5, 5, 5}, {10, 10, 10, 10}, {10, 10, 10, 20}};
		int h = 3;
		int[][] land2 = {{10, 11, 10, 11}, {2, 21, 20, 10}, {1, 20, 21, 11}, {2, 1, 2, 1}};
		int h2 = 1;
		int answer = solution(land2,h2);
		System.out.println(answer);
	}

}
