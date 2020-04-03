# 조합 (Combination)
* 참고자료 : https://bcp0109.tistory.com/15
<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
public class Combination {

	static void combination(int[] arr, boolean[] visited, int depth, int r) {
		if(r==0) {
			for(int i=0;i<arr.length;i++) {
				if(visited[i]) System.out.print(arr[i]+" ");
			}
			System.out.println();
			return;
		}
		for(int i=depth;i<arr.length;i++) {
			visited[i] = true;
			combination(arr,visited,i+1,r-1);
			visited[i] = false;
		}
	}

	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5};
		boolean[] visited = new boolean[arr.length];
		combination(arr,visited,0,3);
	}

}
```

<br/><br/>
