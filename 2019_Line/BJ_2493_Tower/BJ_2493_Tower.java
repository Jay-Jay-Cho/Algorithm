package heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_2493_Tower {
	
	static void solution(int N, int[] arr) {
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
		int[] answer = new int[N];
		heap.add(N-1);
		answer[0] = 0;
		if(N!=1) {
			for(int i=N-2;i>=0;i--) {
				int target_height = arr[i];
				while(!heap.isEmpty()) {
					int temp = heap.peek();
					if(target_height >= arr[temp]) {
						answer[temp] = i+1;
						heap.poll();
					}else break;
				}
				heap.add(i);
			}
			while(!heap.isEmpty()) {
				answer[heap.poll()] = 0;
			}
		}
			
		for(int i=0;i<N;i++) {
			System.out.print(answer[i]+" ");
		}
	}
	
	
	static void solution2(int N, int[] arr) {
		Stack<Integer> stack = new Stack<Integer>();
		int[] answer = new int[N];
		stack.push(N-1);
		if(N!=1) {
			for(int i=N-2;i>=0;i--) {
				int target_height = arr[i];
				while(!stack.isEmpty()) {
					int item = arr[stack.peek()];
					if(target_height >= item) {
						answer[stack.peek()] = i+1;
						stack.pop();
					}else break;
				}
				stack.push(i);
			}
		}
		while(!stack.isEmpty()) {
			answer[stack.pop()] = 0;
		}
		for(int i=0;i<N;i++) {
			System.out.print(answer[i]+" ");
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st2.nextToken());
		}
		
		solution2(N,arr);
		
		
		
	}

}
