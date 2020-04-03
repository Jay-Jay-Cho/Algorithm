# 크루스칼 (Kruskal)
* 활용문제 : [섬 연결하기](https://programmers.co.kr/learn/courses/30/lessons/42861)
* 참고자료 : https://woongsin94.tistory.com/184

<br>

## &#10095;&#10095;&#10095; 설명
#### - 간선(edge)을 기준으로 MST를 구하는 방식
#### - 최소 비용의 간선들만을 가져오되, Union-Bind 방식을 통해 Cycle 체크를 하면서 추가
<br><br>

## &#10095;&#10095;&#10095; 전체 코드
```java
// Kruskal
public int solution(int n, int[][] costs) {
  int answer = 0;

  // union bind를 위해, 모든 정점의 부모를 자기 자신으로 초기화
  parent = new int[n];
  for(int i=0;i<n;i++) parent[i] = i;

  // cost를 오름차순으로 정렬
  Arrays.sort(costs,new Comparator<int[]>() {
    public int compare(int[] a, int[] b) {
      if(a[2]<=b[2]) return -1;
      else return 1;
    }
  });

  int cnt = 0;
  int i = 0;
  // 종료조건 : MST의 간선의 수 = 노드의 수 - 1
  while(cnt<n-1) {
    if(!isSame(costs[i][0],costs[i][1])) {
      union(costs[i][0],costs[i][1]);
      cnt++;
      answer += costs[i][2];
    }
    i++;
  }

  return answer;
}
```
<br><br>


<br/><br/>
