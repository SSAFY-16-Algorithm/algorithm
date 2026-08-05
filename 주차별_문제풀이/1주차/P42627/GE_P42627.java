import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        // index 포함 배열 생성 및 요청 시각 기준 오름차순 정렬
        int[][] sortedJobs = new int[jobs.length][3];
        for (int i = 0; i < jobs.length; i++) {
            sortedJobs[i][0] = i;
            sortedJobs[i][1] = jobs[i][0];
            sortedJobs[i][2] = jobs[i][1];
        }
        Arrays.sort(sortedJobs, (a, b) -> Integer.compare(a[1], b[1]));
        
        // 우선순위 정렬 대기 큐
        PriorityQueue<int[]> diskQueue = new PriorityQueue<>((a, b) -> {
            if (a[2] != b[2]) return Integer.compare(a[2], b[2]);
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });
        
        int currentTime = 0;
        int totalTurnaroundTime = 0;
        int completedCount = 0;
        int jobIdx = 0;
        
        while(completedCount < sortedJobs.length) {
            // 현재 시각까지 요청된 모든 작업을 대기 큐에 삽입
            while (jobIdx < sortedJobs.length && sortedJobs[jobIdx][1] <= currentTime) {
                diskQueue.offer(sortedJobs[jobIdx]);
                jobIdx++;
            }
            
            // 대기 큐가 비어있다면 다음 작업의 요청 시각으로 시간 이동
            if (diskQueue.isEmpty()) {
                currentTime = sortedJobs[jobIdx][1];
                continue;
            }
            
            // 우선순위대로 작업 처리
            int[] currentJob = diskQueue.poll();
            currentTime += currentJob[2];
            totalTurnaroundTime += currentTime - currentJob[1];
            completedCount++;
        }
        
        return totalTurnaroundTime / sortedJobs.length;
    }
}
