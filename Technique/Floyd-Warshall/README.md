# 플로이드-워셜 (Floyd-Warshall)
* 참고자료 : https://manzoo.tistory.com/11
<br>

## &#10095;&#10095;&#10095; 설명
#### - 모든 꼭짓점(정점) 사이의 최단거리를 구하는 방법
#### - 따라서, 시간 복잡도는 O(V^3)
* 가장 바깥쪽 반복문 : 거쳐가는 꼭짓점
* 두 번째 반복문 : 출발하는 꼭짓점
* 세 번째 반복문 : 도착하는 꼭짓점
#### 순회하기 전, 해아할 것
* 2차원 배열에서 자기 자신(i==j)은 0으로 초기화하고, 나머지는 무한대(e.g.`MAX_VALUE` 또는 `9999`)로 선언
<br><br>




## &#10095;&#10095;&#10095; 전체 코드
```java
static int solution(int n, int[][] results) {

    int infinite = Integer.MAX_VALUE/3;
    int answer = 0;
    int[][] map = new int[n+1][n+1];

    // 순회 전 초기화 작업
    // 자기 자신은 0으로 초기화
    // 나머지는 infinite
    for(int i=1;i<map.length;i++) {
    	for(int j=1;j<map.length;j++) {
    		if(i==j) map[i][j] = 0;
    		else map[i][j] = infinite;
    	}
    }

    // 입력 값 넣기
    for(int i=0;i<results.length;i++){
        int win = results[i][0];
        int lose = results[i][1];
        map[win][lose]=1;
    }  

    // 알고리즘 순회
    for(int k=1;k<=n;k++){
        for(int i=1;i<=n;i++) {
        	for(int j=1;j<=n;j++) {
            // k를 거쳐서 가는 경로가 (i -> k -> j)
            // 직접 가는 경로보다 짧다면 (i -> j)
        		if(map[i][j] > map[i][k]+map[k][j]) {
        			map[i][j] = map[i][k]+map[k][j]; // 갱신
        		}
        	}
        }
    }

    return answer;
}

```
<br><br>



<br/><br/>
