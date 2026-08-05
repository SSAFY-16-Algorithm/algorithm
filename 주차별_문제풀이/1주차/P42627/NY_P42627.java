import java.util.Arrays;
import java.util.PriorityQueue;

/******************************************************************************
 * [디스크 컨트롤러 알고리즘 핵심 요약]
 * 
 * 1. 우선순위 조건 (우선순위 큐 / 최소 힙 사용):
 *    - 1순위: 작업 소요시간 오름차순 (작은 것부터)
 *    - 2순위: 작업 요청시각 오름차순 (빠른 것부터)
 *    - 3순위: 작업 번호 오름차순 (작은 것부터)
 * 
 * 2. 시뮬레이션 흐름:
 *    - 1) 모든 작업을 '요청 시각' 순으로 미리 정렬한다.
 *    - 2) 현재 시각(current_time)보다 작거나 같은 시각에 요청된 작업을
 *         모두 대기 큐(우선순위 큐)에 넣는다.
 *    - 3) 대기 큐가 비어있지 않다면:
 *         - 가장 우선순위가 높은 작업을 꺼내 수행한다.
 *         - current_time += 소요시간
 *         - 반환시간(종료시각 - 요청시각)을 total에 누적한다.
 *    - 4) 대기 큐가 비어있다면:
 *         - 다음 남아있는 작업의 '요청 시각'으로 current_time을 이동시킨다.
 *    - 5) 모든 작업이 완료될 때까지 (2)~(4) 과정을 반복한다.
 * 
 * 3. 최종 정답:
 *    - total_turnaround_time / 전체 작업 수 (정수 버림)
 ******************************************************************************/

class Solution {
	
	// 작업을 관리하기 위한 클래스를 구현 (작업 번호, 요청 시각, 소요 시간)
	// 이때 비교 규칙도 같이 구현한다!
	static class Job implements Comparable<Job> {
		int num, reqTime, durTime; // 작업 번호, 요청 시각, 작업 소요 시간
		
		public Job(int num, int reqTime, int durTime) { // 생성자
			this.num = num;
			this.reqTime = reqTime;
			this.durTime = durTime;
		}
		
		// 비교하는 메서드 재정의
		@Override
		public int compareTo(Job o) { // compareTo : "두 객체 중 누가 더 앞(우선순위가 높은)에 와야 하는가?
			// 우선순위 조건
			// 1순위: 작업 소요시간 오름차순 (작은 것부터)
			// 2순위: 작업 요청시각 오름차순 (빠른 것부터)
			// 3순위: 작업 번호 오름차순 (작은 것부터)
			if (this.durTime == o.durTime) { // 작업 소요시간이 같다면 -> 2순위로
				if (this.reqTime == o.reqTime) { // 작업 요청시각도 같다면 -> 3순위로
					return this.num - o.num;
				}
				return this.reqTime - o.reqTime;
			}
			return this.durTime - o.durTime;
		}
	}
	
	static Job[] jobList;
	
    public int solution(int[][] jobs) {
        int answer = 0; // 모든 요청 작업의 반환 시간의 평균
        int N = jobs.length;
        jobList = new Job[N]; // 길이는 N
        PriorityQueue<Job> pq = new PriorityQueue<>(); // 대기 큐(우선순위 큐)
        
        // jobList 배열 초기화
		for (int i=0; i<N; i++) {
			jobList[i] = new Job( i, jobs[i][0], jobs[i][1] ); // 작업 번호, 요청 시각, 작업 소요 시간
		}
        
		// 1) 모든 작업을 '요청 시각' 순으로 미리 정렬한다.
		Arrays.sort(jobList, (o1, o2) -> o1.reqTime - o2.reqTime);
		
		int currentTime = 0, totalTrunaroundTime = 0, completedCnt = 0, jobIdx = 0; // 현재 시각, 누적 반환 시간, 완료된 작업 수, 배열 탐색용 인덱스
		
		while (completedCnt < N) { // completedCnt가 N보다 작을 동안
			// 대기 큐 채우기
			while (jobIdx < N && jobList[jobIdx].reqTime <= currentTime) { // jobIdx가 N을 넘어서지 않고 요청 시각도 현재 시각보다 이전인 경우동안만
				pq.offer(jobList[jobIdx++]);
			}
			
			// 작업 수행하기(대기 큐가 비어있지 않는다면) -> 조건문
			if (!pq.isEmpty()) { // pq는 현재 시간에 처리가능한 작업들 존재
				Job nowWork = pq.poll();
				currentTime += nowWork.durTime; // currentTime 조정
				totalTrunaroundTime += (currentTime - nowWork.reqTime); // totalTrunaroundTime
				completedCnt++;
			} else { // currentTime 조정 (처리 가능한 작업이 없다면)
				currentTime = jobList[jobIdx].reqTime;
			}
		}
		
		answer = totalTrunaroundTime / N;
        return answer;
    }
}