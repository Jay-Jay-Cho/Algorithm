package brute_force;

import java.util.ArrayList;

public class Programmers_PrimeNumber {
	
	static ArrayList<Integer> list = new ArrayList<>();
	
	static void permutation(String[] arr, int depth, int r) {
		if(r==depth && !arr[0].equals("0")) {
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<r;i++) sb.append(arr[i]);
			int value = Integer.parseInt(sb.toString());
			if(!list.contains(value))list.add(value);
			return;
		}
		for(int i=depth;i<arr.length;i++) {
			swap(arr,depth,i);
			permutation(arr,depth+1,r);
			swap(arr,depth,i);
		}
	}
	
	static void swap(String[] arr, int depth, int i) {
		String temp = arr[depth];
		arr[depth] = arr[i];
		arr[i] = temp;
	}
	
	static int getAnswer(String numbers) {
		String[] arr = numbers.split("");
		for(int i=1;i<=arr.length;i++) {
			permutation(arr,0,i);
		}
		
		// 소수 찾기 
		int answer = 0;
		for(int num:list) {
			int cnt = 0;
			for(int i=1;i<=num;i++) {
				if(cnt>2) break;
				if(num%i==0) cnt++;
			}
			if(cnt==2) answer++;
		}
		for(int a:list) System.out.print(a+" ");
		return answer;
	}
	

	
	

	public static void main(String[] args) {
		String numbers = "011";
		getAnswer(numbers);

	}

}
