package samsung;
import java.io.*;
import java.util.*;
public class BJ_17140_2DimensionalArray {
	
	static class Node{
		int num;
		int cnt;
		Node(int num, int cnt){
			this.num = num;
			this.cnt = cnt;
		}
	}
	
	static int r,c,k;
	static int[][] map;
	static int[][] result_map;
	static int time = 0;
	static boolean flag = true;
	static int row_cnt = 3;
	static int col_cnt = 3;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		map = new int[3][3];
		for(int i=0;i<3;i++) {
			if(i==0) r=Integer.parseInt(st1.nextToken())-1;
			else if(i==1) c=Integer.parseInt(st1.nextToken())-1;
			else k=Integer.parseInt(st1.nextToken());
		}
		for(int i=0;i<3;i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++) {
				map[i][j] = Integer.parseInt(st2.nextToken());
				if(r<=2 && c<=2 && map[r][c] == k) flag=false;	// exception
			}
		}
		
		while(flag && time<=100) {
			time++;
			
			// R operation
			if(row_cnt >= col_cnt) {
				if(time==1) operation(true,map);
				else operation(true,result_map);
			}
			
			// C operation
			else {
				//result_map = operation(false,map);	
				operation(false,result_map);
			}
			
			if(r<=row_cnt-1 && c<=col_cnt-1 && result_map[r][c]==k) {
				flag = false; // exception 
			}
		}
		
		// could not find
		if(flag == true) time=-1;
		
		System.out.println(time);
	}
	
	// should not count the zero(0)
	
	static void operation(boolean isR, int[][] map) {
		PriorityQueue<Node> priority_q = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node node1, Node node2) {
				if(node1.cnt < node2.cnt) return -1;
				else if(node1.cnt == node2.cnt) {
					if(node1.num <= node2.num) return -1;
					else return 1;
				}else return 1;
			}
			
		});
		HashMap<Integer,Integer> hashmap;
		Queue<int[]> q;
		if(isR) {
			q = new LinkedList<int[]>();
			int R_cnt = row_cnt;
			int max_col_cnt = Integer.MIN_VALUE;
			for(int i=0;i<R_cnt;i++) {
				hashmap = new HashMap<Integer,Integer>();
				for(int num:map[i]) {
					if(num==0) continue;
					if(hashmap.containsKey(num)) {
						int previous_value = hashmap.get(num);
						hashmap.replace(num, previous_value+1);
					}else {
						hashmap.put(num, 1);
					}
				}
				Iterator<Integer> iter = hashmap.keySet().iterator();
				while(iter.hasNext()) {
					if(priority_q.size() > 50) {
						break;
					}
					int num = (int) iter.next();
					int cnt = hashmap.get(num);
					priority_q.offer(new Node(num,cnt));
				}
				max_col_cnt = Math.max(priority_q.size()*2, max_col_cnt);
				int new_arr_length = priority_q.size()*2;
				int[] new_arr = new int[new_arr_length];
				int idx = 0;
				while(!priority_q.isEmpty()) {
					Node node = priority_q.poll();
					new_arr[idx] = node.num;
					idx++;
					new_arr[idx] = node.cnt;
					idx++;
				}
				q.offer(new_arr);
				//max_col_cnt = Math.max(priority_q.size()*2, max_col_cnt); //location
				
			}
			col_cnt = max_col_cnt;
			result_map = new int[row_cnt][col_cnt];
			for(int row=0;row<row_cnt;row++) {
				int[] temp_arr = q.poll();
				for(int col=0;col<temp_arr.length;col++) {
					result_map[row][col] = temp_arr[col];
				}
				//result_map[row] = temp_arr.clone();
			}
		}else {
			q = new LinkedList<int[]>();
			int C_cnt = col_cnt;
			int max_row_cnt = Integer.MIN_VALUE;
			for(int i=0;i<C_cnt;i++) {
				hashmap = new HashMap<Integer,Integer>();
				
				for(int row=0;row<row_cnt;row++) {
					int num = map[row][i];
					if(num==0) continue;
					if(hashmap.containsKey(num)) {
						int previous_value = hashmap.get(num);
						hashmap.replace(num, previous_value+1);
					}else {
						hashmap.put(num, 1);
					}
				}
				
				Iterator<Integer> iter = hashmap.keySet().iterator();
				while(iter.hasNext()) {
					if(priority_q.size() > 50) {
						break;
					}
					int num = (int) iter.next();
					int cnt = hashmap.get(num);
					priority_q.offer(new Node(num,cnt));
				}
				
				max_row_cnt = Math.max(priority_q.size()*2, max_row_cnt);
				int new_arr_length = priority_q.size()*2;
				int[] new_arr = new int[new_arr_length];
				int idx = 0;
				while(!priority_q.isEmpty()) {
					Node node = priority_q.poll();
					new_arr[idx] = node.num;
					idx++;
					new_arr[idx] = node.cnt;
					idx++;
				}
				q.offer(new_arr);
				//max_row_cnt = Math.max(priority_q.size()*2, max_row_cnt);
			}
			row_cnt = max_row_cnt;
			result_map = new int[row_cnt][col_cnt];
			for(int col=0;col<col_cnt;col++) {
				int[] temp_arr = q.poll();
				for(int row=0;row<temp_arr.length;row++) {
					result_map[row][col] = temp_arr[row];
				}
			}
		}
		//return result_map;
	}

}
