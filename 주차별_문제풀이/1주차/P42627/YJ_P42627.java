import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int N = jobs.length, answer = 0;
        
        // int[] = { 번호, 요청 시각, 소요 시간 }
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[2] != o2[2]) return o1[2] - o2[2];
            if (o1[1] != o2[1]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
        
        Arrays.sort(jobs, (o1, o2) -> {
            if (o1[0] != o2[0]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        });
        
        Deque<int[]> waitQueue = new ArrayDeque<>();
        
        // time: 현재 시간, idx: waitQueue에 넣은 디스크 개수, cnt: 작업 완료한 디스크 개수
        int time = 0, idx = 0, cnt = 0;
        
        while (cnt < N) {
            // waitQueue에 현재 시간보다 크거나 같은 디스크 추가
            while (true) {
                if (idx >= N || jobs[idx][0] > time) break;
                waitQueue.offer(new int[] { idx, jobs[idx][0], jobs[idx++][1] });
            }
            
            // waitQueue에 있는 디스크 꺼내서 작업 큐에 추가
            while (!waitQueue.isEmpty()) {
                pq.offer(waitQueue.poll());
            }
            
            // 만약 작업 큐에 디스크가 없으면 시간++
            if (pq.isEmpty()) {
                time++;
                continue;
            }
            
            int[] cur = pq.poll();

            cnt++;
            if (time < cur[1]) {
                time = cur[1]; 
            }
            
            time += cur[2];
            answer += (time - cur[1]);
        }
        
        return answer / N;
    }
}
