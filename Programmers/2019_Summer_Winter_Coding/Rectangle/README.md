# [멀쩡한 사각형](https://programmers.co.kr/learn/courses/30/lessons/62048)
* **참고자료** : https://co-da-nam.tistory.com/30

<br>

## &#10095;&#10095;&#10095; 전체 코드
```java
class Solution {
    public long solution(int w, int h) {
        long answer = 0;

        double radian = (double)w/(double)h;
        for(int i=0;i<h;i++) {
        	long temp = (long)Math.floor(radian*i);
        	answer += temp;
        }

        return answer*2;
    }
}
```
<br><br>

## &#10095;&#10095;&#10095; 설명
* Level 2
* 시뮬레이션
* 가로의 길이 W와 세로의 길이 H가 주어질 때, 사용할 수 있는 정사각형의 개수를 return
<br><br>


## &#10095;&#10095;&#10095; 접근법   
#### 1. 대각선의 기울기 구하기
#### 2. W의 길이만큼 반복하면서, 각 W마다 사각형의 갯수 구하기
* 1차 방정식: y = 기울기*x
* 여기서, x 자리에 W를 대입하면 정사각형 갯수가 나옴
<br><br>


## &#10095;&#10095;&#10095; 풀이
#### 1. 대각선의 기울기 구하기
```java
double radian = (double)w/(double)h;
```

#### 2. W의 길이만큼 반복하면서, 각 W마다 사각형의 갯수 구하기
```java
for(int i=0;i<h;i++) {
  long temp = (long)Math.floor(radian*i);
  answer += temp;
}
```
<br><br>

## &#10095;&#10095;&#10095; 실수
#### 1. 기울기보다, 각도를 활용하려 했음.
* 각도보다, 기울기가 훨씬 직관적임.... 되도록 지양

<br><br>


## &#10095;&#10095;&#10095; 꿀팁
#### 두 점 사이의 각도 구하기
```java
private static double getAngle(int x1,int y1, int x2,int y2){
   int dx = x2 - x1;
   int dy = y2 - y1;

   double rad= Math.atan2(dx, dy);
   double degree = (rad*180)/Math.PI ;

   return degree;
}
```

#### 데이터 타입 변환
* 변수 앞에 직접 캐스팅을 해도 되지만, `valueOf`를 활용하는 방법도 있음.
```java
int intValue;
Long.valueOf(intValue);
Double.valueOf(intValue);
```


<br><br>

## &#10095;&#10095;&#10095; 숙지해야할 점
<br><br>
