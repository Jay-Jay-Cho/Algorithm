package kakao_2020_intern;
import java.util.*;
public class MaxFormula {
	
	static HashSet<String> set = new HashSet<String>();
	static ArrayList<String> list = new ArrayList<String>();
	
	static void swap(int[] arr, int depth, int i) {
		int temp = arr[depth];
		arr[depth] = arr[i];
		arr[i] = temp;
	}
	
	static void permutation(int[] arr, int depth, int r) {
		if(r==depth) {
			StringBuilder sb = new StringBuilder(r);
			for(int i=0;i<r;i++) {
				sb.append(arr[i]);
			}
			list.add(sb.toString());
			return;
		}
		for(int i=depth;i<arr.length;i++) {
			swap(arr,depth,i);
			permutation(arr,depth+1,r);
			swap(arr,depth,i);
		}
	}
	
	static long solution(String expression) {
        long answer = 0;
		// 연산자 갯수 파악 
        for(int i=0;i<expression.length();i++) {
        	char c = expression.charAt(i);
        	if(!Character.isDigit(c)) set.add(c+"");
        }
        int operatorCnt = set.size();
        String[] tempArr = new String[operatorCnt];
        int index=0;
        for(String s:set) {
        	tempArr[index++] = s;
        }
        		
        // 연산자 갯수에 맞게 순열 경우의 수 List에 넣기 
        int permArr[] = new int[operatorCnt];
        for(int i=0;i<operatorCnt;i++) permArr[i]=i;
        permutation(permArr,0,operatorCnt);
        
        
        //split operand & operator
        ArrayList<String> outerList = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<expression.length();i++) {
        	char c = expression.charAt(i);
        	if(Character.isDigit(c)) {
        		sb.append(c+"");
        		continue;
        	}
        	
        	outerList.add(sb.toString());
        	sb = new StringBuilder();
        	outerList.add(c+"");
        }
        outerList.add(sb.toString());
        
        
        // List를 순회하며 최댓값 갱
        for(int i=0;i<list.size();i++) {
        	ArrayList<String> innerList = (ArrayList<String>) outerList.clone();
        	for(int j=0;j<operatorCnt;j++) {
        		String currOperator = tempArr[list.get(i).charAt(j)-'0'];
        		for(int k=0;k<innerList.size();k++) {
        			if(innerList.get(k).equals(currOperator+"")) {
        				long leftOp = Long.parseLong(innerList.get(k-1)); // mistake
        				long rightOp = Long.parseLong(innerList.get(k+1));  // mistake
        				long result = cal(leftOp,rightOp,currOperator);
        				innerList.remove(k+1);
        				innerList.remove(k);
        				innerList.remove(k-1);
        				//innerList.set(k-1, Integer.toString(result));
        				innerList.add(k-1, Long.toString(result));
        				k--; // 실수 
        			}
        		}
        		
        	}
        	answer = Math.max(answer, Math.abs(Long.parseLong(innerList.get(0)))); // mistake
        }
        
        
        return answer;
    }
	
	static long cal(long op1, long op2, String operator) {
		switch(operator) {
			case "*": return op1*op2;
			case "-": return op1-op2;
			case "+": return op1+op2;
		}
		
		return 1;
	}

	public static void main(String[] args) {
		String express = "1-43";
		long ans = solution(express);
		System.out.println(ans);

	}

}
