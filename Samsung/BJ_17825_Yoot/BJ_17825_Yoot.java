package samsung;
import java.io.*;
import java.util.*;
public class BJ_17825_Yoot {
	
	static Node start;
	static Node[] yoot_arr;
	static int answer;
	static int[] dice;
	
	static class Node{
		int value;
		boolean isLocated;
		boolean isEnd;
		Node next;
		Node blue;
		
		Node(int value){
			this.value = value;
			isLocated = false;
			isEnd = false;
			next = null;
			blue = null;
		}
		
		Node addNext(int value) {
			Node nextNode = new Node(value);
			this.next = nextNode;
			return this.next;
		}
		
		static Node getNode(int value) {
			Node temp = start;
			while(true) {
				if(temp==null) return null;
				if(temp.value==value) return temp;
				temp = temp.next;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		dice = new int[10];
		for(int i=0;i<10;i++) {
			dice[i] = Integer.parseInt(input[i]);
		}
		
		
		init();
		answer = Integer.MIN_VALUE;
		dfs(0,0);
		
		System.out.println(answer);
	}
	
	static void dfs(int depth, int score) {
		if(depth == 10) {
			answer = Math.max(answer, score);
			return;
		}
		
		for(int i=0;i<4;i++) {
			int move = dice[depth];
			Node current = yoot_arr[i];
			
			
			Node temp = current;
			for(int j=0;j<move;j++) {
				if(j==0 && temp.blue!=null) temp = temp.blue;
				else {
					temp = temp.next;
				}
			}
			if(temp.isLocated && !temp.isEnd) {
				continue;
			}else {
				current.isLocated = false;
				temp.isLocated = true;
				score += temp.value;
				yoot_arr[i] = temp;
				
				dfs(depth+1,score);
				
				yoot_arr[i] = current;
				score -= temp.value;
				temp.isLocated = false;
				current.isLocated = true;
			}
			
			
		}
	}
	
	static void init() {
		start = new Node(0);
		Node end = null;
		Node center = new Node(25);
		
		yoot_arr = new Node[4];
		for(int i=0;i<4;i++) {
			yoot_arr[i] = start;
		}
		
		Node temp = start;
		for(int i=2;i<=40;i+=2) {
			temp = temp.addNext(i);
		}
		
		// end
		end = temp.addNext(0); // mistake
		end.next = end;
		end.isEnd = true;
		
		// first blue
		Node n = Node.getNode(10);
		n = n.blue = new Node(13);
		n = n.addNext(16);	
		n = n.addNext(19);
		n.next = center;
		
		// second blue
		Node n2 = Node.getNode(20);
		n2 = n2.blue = new Node(22);
		n2 = n2.addNext(24);
		n2.next = center;
		
		// third blue
		Node n3 = Node.getNode(30);
		n3 = n3.blue = new Node(28);
		n3 = n3.addNext(27);
		n3 = n3.addNext(26);
		n3.next = center;
		
		// center
		center = center.addNext(30);
		center = center.addNext(35);
		center.next = Node.getNode(40);
	}

}
