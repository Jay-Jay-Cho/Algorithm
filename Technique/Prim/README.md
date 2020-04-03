# 프림 (Prim)
<br>

## &#10095;&#10095;&#10095; 설명
#### Kruskal 알고리즘과 달리, 간선(edge)이 아닌 정점(vertex)을 기준으로 MST를 구하는 방식
* 일단 아무 정점이나 MST list에 넣고,
* list에 들어있는 노드들과 연결된 다른 노드들 중 가장 짧은 간선을 가진 노드를 MST 배열에 추가
#### Cycle 방지를 위해 boolean visit 배열 사용
* Kruskal은 Union-Find.....

<br><br>



## &#10095;&#10095;&#10095; 전체 코드
```java
public int solution(int n, int[][] costs) {
    int answer = 0;

    boolean[] visit = new boolean[n];
    int[][] maps = new int[n][n];
    ArrayList<Integer> list = new  ArrayList<Integer>();

    for(int i=0;i<costs.length;i++) {
      int from = costs[i][0];
      int to = costs[i][1];
      int cost = costs[i][2];
      maps[from][to] = cost;
      maps[to][from] = cost;
    }

    // initialize
    list.add(0);
    visit[0] = true;

    while(list.size()<n) {
      int min = Integer.MAX_VALUE;
      int min_idx = -1;

      for(int i=0;i<list.size();i++) {
        int v = list.get(i);
        for(int j=0;j<n;j++) {
          if(maps[v][j]!=0 && !visit[j] && min > maps[v][j]) {
            min = maps[v][j];
            min_idx = j;
          }
        }
      }
      list.add(min_idx);
      visit[min_idx] = true;
      answer+=min;
    }

    return answer;
}
```
<br><br>



<br/><br/>
