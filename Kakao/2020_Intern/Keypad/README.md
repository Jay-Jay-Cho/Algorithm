# [키패드 누르기](https://programmers.co.kr/learn/courses/30/lessons/67256)
* **참고자료** : 엄슴

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
static HashMap<String,Integer> map = new HashMap<String,Integer>();
static HashSet<Integer> leftSet = new HashSet<Integer>();
static HashSet<Integer> rightSet = new HashSet<Integer>();
static String leftPos = "*";
static String rightPos = "#";

static void init() {
	map.put("12", 1); map.put("15", 2); map.put("18", 3); map.put("10", 4);
	map.put("42", 2); map.put("45", 1); map.put("48", 2); map.put("40", 3);
	map.put("72", 3); map.put("75", 2); map.put("78", 1); map.put("70", 2);
	map.put("*2", 4); map.put("*5", 3); map.put("*8", 2); map.put("*0", 1);

	map.put("22", 0); map.put("25", 1); map.put("28", 2); map.put("20", 3);
	map.put("52", 1); map.put("55", 0); map.put("58", 1); map.put("50", 2);
	map.put("82", 2); map.put("85", 1); map.put("88", 0); map.put("80", 1);
	map.put("02", 3); map.put("05", 2); map.put("08", 1); map.put("00", 0);

	// mistake
	map.put("32", 1); map.put("35", 2); map.put("38", 3); map.put("30", 4);
	map.put("62", 2); map.put("65", 1); map.put("68", 2); map.put("60", 3);
	map.put("92", 3); map.put("95", 2); map.put("98", 1); map.put("90", 2);
	map.put("#2", 4); map.put("#5", 3); map.put("#8", 2); map.put("#0", 1);

	leftSet.add(1); leftSet.add(4); leftSet.add(7);
	rightSet.add(3); rightSet.add(6); rightSet.add(9);
}

static String solution(int[] numbers, String hand) {
     StringBuilder sb = new StringBuilder(numbers.length);

      init();
      for(int number:numbers) {
      	if(leftSet.contains(number)){
      		leftPos = Integer.toString(number);
      		sb.append("L");
      		continue;
      	}

      	if(rightSet.contains(number)) {
      		rightPos = Integer.toString(number);
      		sb.append("R");
      		continue;
      	}
      	int leftD = map.get(leftPos.concat(Integer.toString(number)));
      	int rightD = map.get(rightPos.concat(Integer.toString(number)));

      	if(leftD > rightD) {
      		rightPos = Integer.toString(number);
      		sb.append("R");
      	}else if(leftD < rightD) {
      		leftPos = Integer.toString(number);
      		sb.append("L");
      	}else {
      		if(hand.equals("left")) {
      			leftPos = Integer.toString(number);
          		sb.append("L");
      		}else {
      			rightPos = Integer.toString(number);
          		sb.append("R");
      		}
      	}

      }

      return sb.toString();
  }
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level 2
* 시뮬레이션
* 각 번호를 누른 엄지손가락이 왼손인 지 오른손인 지를 나타내는 연속된 문자열 형태로 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
#### 1. 좌/우 번호별로 가운데 키패드 번호까지의 거리를 담은 HashMap 및 기타 변수 선언
#### 2. numbers 배열을 순회하면서 적합한 손을 할당
* `1, 4, 7`은 무조건 왼손
* `3, 6, 9`는 무조건 오른손
* `2, 5, 8, 0`은 무조건 상황에 맞게 거리가 가까운 손
* 각 경우가 끝날 때마다, 현재 손의 위치를 갱신
#### 3. 각 경우가 끝날 때마다 StringBuilder에 추가

<br><br>


## &#10095;&#10095;&#10095; 풀이
#### 1. 좌/우 번호별로 가운데 키패드 번호까지의 거리를 담은 HashMap 선언
```java
// 가운데 키패드 번호까지의 거리를 담은 Map
static HashMap<String,Integer> map = new HashMap<String,Integer>();
static HashSet<Integer> leftSet = new HashSet<Integer>(); // 1, 4, 7
static HashSet<Integer> rightSet = new HashSet<Integer>(); // 3, 6, 9

// 현재 왼/오른손 위치
static String leftPos = "*";
static String rightPos = "#";
```

#### 2. HashMap 초기화
```java
static void init() {
        // 왼쪽 키패드 -> 가운데 키패드
      	map.put("12", 1); map.put("15", 2); map.put("18", 3); map.put("10", 4);
      	map.put("42", 2); map.put("45", 1); map.put("48", 2); map.put("40", 3);
      	map.put("72", 3); map.put("75", 2); map.put("78", 1); map.put("70", 2);
      	map.put("*2", 4); map.put("*5", 3); map.put("*8", 2); map.put("*0", 1);

        // 가운데 키패드 -> 가운데 키패드
      	map.put("22", 0); map.put("25", 1); map.put("28", 2); map.put("20", 3);
      	map.put("52", 1); map.put("55", 0); map.put("58", 1); map.put("50", 2);
      	map.put("82", 2); map.put("85", 1); map.put("88", 0); map.put("80", 1);
      	map.put("02", 3); map.put("05", 2); map.put("08", 1); map.put("00", 0);

      	// 오른손 키패드 -> 가운데 키패드
      	map.put("32", 1); map.put("35", 2); map.put("38", 3); map.put("30", 4);
      	map.put("62", 2); map.put("65", 1); map.put("68", 2); map.put("60", 3);
      	map.put("92", 3); map.put("95", 2); map.put("98", 1); map.put("90", 2);
      	map.put("#2", 4); map.put("#5", 3); map.put("#8", 2); map.put("#0", 1);

      	leftSet.add(1); leftSet.add(4); leftSet.add(7);
      	rightSet.add(3); rightSet.add(6); rightSet.add(9);
}
```

#### 3. numbers 배열을 순회하면서 적합한 손을 할당
```java
static String solution(int[] numbers, String hand) {
    // 각 경우마다 정답을 누적시킬 StringBuilder
    StringBuilder sb = new StringBuilder(numbers.length);

    // HashMap 초기화
    init();
    for(int number:numbers) {
        // 번호가 1,4,7 이면 무조건 왼손
      	if(leftSet.contains(number)){
      		leftPos = Integer.toString(number);
      		sb.append("L");
      		continue;
      	}

        // 번호가 3,6,9면 무조건 오른손
      	if(rightSet.contains(number)) {
      		rightPos = Integer.toString(number);
      		sb.append("R");
      		continue;
      	}

        // 현재 왼/오른손 위치와 가운데 키패드 간 거리 비교
      	int leftD = map.get(leftPos.concat(Integer.toString(number)));
      	int rightD = map.get(rightPos.concat(Integer.toString(number)));

        // 오른손의 위치가 더 가까울 때
      	if(leftD > rightD) {
      		rightPos = Integer.toString(number);
      		sb.append("R");
      	}
        // 왼손의 위치가 더 가까울 때
        else if(leftD < rightD) {
      		leftPos = Integer.toString(number);
      		sb.append("L");
      	}
        // 두 거리가 같다면, 주로 사용하는 손
        else {
      		if(hand.equals("left")) {
      			leftPos = Integer.toString(number);
          		sb.append("L");
      		}else {
      			rightPos = Integer.toString(number);
          		sb.append("R");
      		}
      	}

    }

    return sb.toString();
}
```

<br><br>

## &#10095;&#10095;&#10095; 실수
#### 1. 키패드 간 거리를 구할 때, 가운데 키패드 -> 가운데 키패드 경우를 생각못함.
* 키패드가 눌릴 때마다, 현재 왼/오른손 위치가 갱신되기 때문에
* 좌/우 키패드만 눌리는게 아니라, 이전에 가운데 키패드 번호가 눌리면 다음에 거리를 비교할 때 그 거리를 기준으로 비교가 가능해야 됨.

<br><br>


## &#10095;&#10095;&#10095; 꿀팁
* 경우의 수가 별로 없을 때는, HashMap을 이용해서 분기하자...
* 특정 좌표(`x`,`y`)가 아니라, 이번 문제처럼 특정 값과 값 사이의 거리를 비교하고 싶을 때는, `StringBuilder`와 `HashMap<String,Integer>`을 사용하자
  ```java
  // 1번 키패드에서 2번 키패드 = 12
  // 거리 = 1
  map.put("12", 1);

  // 0번 키패드에서 2번 키패드 = 02
  // 거리 = 3
  map.put("02", 3);
  ```

<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점

<br><br>
