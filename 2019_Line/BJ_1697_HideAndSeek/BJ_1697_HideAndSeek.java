package dfs_and_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1697_HideAndSeek {
	
	static class Subin{
		int position;
		int time;
		StringBuilder path = new StringBuilder();
		Subin(int p, String str){
			position = p;
			path.append(str);
		}
	}
	
	static int solution(int subin, int sister) {
		long start = System.currentTimeMillis();


		int answer = 0;
		
		boolean[] visit = new boolean[100001];
		
		Queue<Subin> q = new LinkedList<Subin>();
		q.add(new Subin(subin,String.valueOf(subin)));
		visit[subin] = true;
		int q_size;
		int time = 0;
		boolean flag = true;
		while(flag) {
			q_size = q.size();
			for(int i=0;i<q_size;i++) {
				Subin temp = q.poll();
				StringBuilder path = temp.path;
				if(temp.position == sister) {
					answer = time;
					System.out.println(path.toString());
					flag = false;
				}
				String prev = path.toString();
				
				if(temp.position-1>=0 && temp.position-1<=100000 && !visit[temp.position-1]) {
					q.add(new Subin(temp.position-1,new StringBuilder(prev).append(String.valueOf(temp.position-1)).toString()));
					visit[temp.position-1] = true;
				}
				if(temp.position+1>=0 && temp.position+1<=100000 && !visit[temp.position+1]) {
					q.add(new Subin(temp.position+1,new StringBuilder(prev).append(String.valueOf(temp.position+1)).toString()));
					visit[temp.position+1] = true;
				}
				if(temp.position*2>=0 && temp.position*2<=100000  && !visit[temp.position*2]) {
					q.add(new Subin(temp.position*2,new StringBuilder(prev).append(String.valueOf(temp.position*2)).toString()));
					visit[temp.position*2] = true;
				}
			}
			time++;
		}
		
		long end = System.currentTimeMillis();
		System.out.println( "실행 시간 : " + ( end - start )/1000.0 );

		return answer;
		
	}
	
	static int solution2(int subin, int sister) {
		long start = System.currentTimeMillis();
		int answer = 0;
		
		boolean[][] visit = new boolean[100001][2];
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(subin);
		visit[subin][0] = true;
		int q_size;
		int time = 0;
		boolean flag = true;
		while(flag) {
			q_size = q.size();
			for(int i=0;i<q_size;i++) {
				int temp_subin = q.poll();
				if(temp_subin == sister) {
					answer = time;
					flag = false;
				}
				if(temp_subin-1>=0 && temp_subin-1<=100000 && !visit[temp_subin-1][time%2]) {
					q.add(temp_subin-1);
					visit[temp_subin-1][time%2] = true;
				}
				if(temp_subin+1>=0 && temp_subin+1<=100000 && !visit[temp_subin+1][time%2]) {
					q.add(temp_subin+1);
					visit[temp_subin+1][time%2] = true;
				}
				if(temp_subin*2>=0 && temp_subin*2<=100000  && !visit[temp_subin*2][time%2]) {
					q.add(temp_subin*2);
					visit[temp_subin*2][time%2] = true;
				}
			}
			time++;
		}
		
		long end = System.currentTimeMillis();
		System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
		
		return answer;
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		int subin = Integer.parseInt(st.nextToken());
		int sister = Integer.parseInt(st.nextToken());
		int answer = solution(subin,sister);
		System.out.println(answer);

	}

}
