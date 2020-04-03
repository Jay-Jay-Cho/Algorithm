# 순열 (Permutation)
* 참고자료 : https://bcp0109.tistory.com/14?category=848939
<br>

## &#10095;&#10095;&#10095; 전체 코드
#### 순서 X (백트래킹)
```java
static void swap(int[] arr, int depth, int i) {
		int temp = arr[depth];
		arr[depth] = arr[i];
		arr[i] = temp;
	}

static void permutation(int[] arr, int depth, int r) {
	if(r==depth) {
		for(int i=0;i<r;i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		return;
	}
	for(int i=depth;i<arr.length;i++) {
		swap(arr,depth,i);
		permutation(arr,depth+1,r);
		swap(arr,depth,i);
	}
}
```

#### 순서 O (배열)
```java
static void permutation_order(int[] arr, int[] output, boolean[] visited, int depth, int n, int r) {
    if (depth == r) {
    	for(int i=0;i<r;i++) {
			System.out.print(output[i]+" ");
		}
    	System.out.println();
        return;
    }

    for (int i=0; i<n; i++) {
        if (visited[i] != true) {
            visited[i] = true;
            output[depth] = arr[i];
            permutation_order(arr, output, visited, depth + 1, n, r);       
            output[depth] = 0; // 이 줄은 없어도 됨
            visited[i] = false;
        }
    }
}
```

#### main
```java
public static void main(String[] args) {
	int[] arr = {1,2,3,4,5};
	int r = 3;
	//without order
	permutation(arr,0,r);


	//with order
	int n = arr.length;
	int[] output = new int[n];
	boolean[] visited = new boolean[n];
	permutation_order(arr, output, visited, 0, n, r);
}
```


<br><br>
