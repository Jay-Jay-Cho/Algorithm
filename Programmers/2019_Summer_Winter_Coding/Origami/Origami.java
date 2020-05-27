package summer_winter_coding_2019;
import java.util.*;
public class Origami {
	
	static class Node{
		int pre_index;
		int index;
		boolean isCenter;
		boolean isLeft;
		Node(int pre_index, int index, boolean isCenter, boolean isLeft){
			this.pre_index = pre_index;
			this.index = index;
			this.isCenter = isCenter;
			this.isLeft = isLeft;
		}
	}
	
	static int[] solution(int n) {
		int size = (int) (Math.pow(2, n)-1);
        int[] answer = new int[size];
        boolean[] visited = new boolean[size];
        Arrays.fill(answer, -2);
        Queue<Node> q = new LinkedList<Node>();
        visited[(size-1)/2] = true;
        q.offer(new Node(size,(size-1)/2,true,true));
        while(!q.isEmpty()) {
        	Node node = q.poll();
        	int idx = node.index;
        	if(node.isCenter || node.isLeft) {
        		answer[idx] = 0;
        		int left_idx = (idx-1)/2;
        		int right_idx = (idx + node.pre_index)/2;
        		if(left_idx>=0 && !visited[left_idx]) {
        			visited[left_idx] = true;
        			q.offer(new Node(idx,left_idx,false,true));
        		}
        		if(right_idx<node.pre_index && !visited[right_idx]) {
        			visited[right_idx] = true;
        			q.offer(new Node(idx,right_idx,false,false));
        		}
        	}else {
        		answer[idx] = 1;
        		int left_idx = (node.pre_index + idx)/2;
        		int right_idx = (idx + size)/2;
        		if(left_idx>node.pre_index && !visited[left_idx]) {
        			visited[left_idx] = true;
        			q.offer(new Node(idx,left_idx,false,true));
        		}
        		if(right_idx<size && !visited[right_idx]) {
        			visited[right_idx] = true;
        			q.offer(new Node(idx,right_idx,false,false));
        		}
        	}
        	
        }
        
        
        
        return answer;
    }

	static int[] solution2(int n) {
		int size = (int) (Math.pow(2, n)-1);
		int[] answer = new int[size];
		StringBuilder sb = new StringBuilder("0");
		int cnt = n;
		while(cnt>1) {
			String str = sb.toString();
			sb.append(0);
			for(int i=str.length()-1;i>=0;i--) {
				if(str.charAt(i)=='0') {
					sb.append(1);
				}else {
					sb.append(0);
				}
			}
			cnt--;
		}
		String[] str_arr = sb.toString().split("");
		for(int i=0;i<size;i++) answer[i] = Integer.parseInt(str_arr[i]);
		
		return answer;
	}
	
	public static void main(String[] args) {
		int[] answer = solution2(3);
		for(int val:answer) System.out.print(val+" ");

	}

}
