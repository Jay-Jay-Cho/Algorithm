package dfs_and_bfs;

import java.util.Queue;
import java.util.Arrays;
import java.util.LinkedList;
public class Programmers_WordsConversion {
	
	class Node{
		int count;
		String value;
		Node(String value, int count){
			this.value = value;
			this.count = count;
		}
	}
	
	static boolean isNext(String curr, String next, int n) {
		int cnt=0;
		for(int i=0;i<n;i++) {
			if(curr.charAt(i)!=next.charAt(i)) {
				cnt++;
			}
			if(cnt>1) {
				return false;
			}
		}
		return true;
	}
	
	public int bfs(String begin, String target, String[] words) {
		int answer = 0;
		int arr_length = words.length;
		int target_length = target.length();
		boolean[] visited = new boolean[arr_length+1];
		
		if(!Arrays.asList(words).contains(target)) {
			return 0;
		}

		Queue<Node> q = new LinkedList<>();
		q.add(new Node(begin,-1));
		while(!q.isEmpty()) {
			Node temp = q.poll();
			if(temp.value.equals(target)) {
				answer =  temp.count+1;
				break;
			}
				for(int i=0;i<arr_length;i++) {
					if(!visited[i] && isNext(temp.value,words[i],target_length)) {
						q.add(new Node(words[i],temp.count+1));
						visited[i] = true;
					}
				}
			
		}
		return answer;
		
	}
	
	

	public static void main(String[] args) {
		String begin = "hit";
		String target = "cog";
		String[] words = {"hot","dot","dog","lot","log"};
				
		Programmers_WordsConversion a = new Programmers_WordsConversion();
		int ans = a.bfs(begin, target, words);
		System.out.println(ans);
	}

}
