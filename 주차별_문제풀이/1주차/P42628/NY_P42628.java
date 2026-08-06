import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        // 기본형: 우선순위가 낮은 숫자가 먼저 나옴 (작은 숫자)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (String operation : operations) {
            //System.out.println(operation);
            
            // 0. operation에서 명령어와 데이터를 구분
            String[] temp = operation.split(" ");
            String oper = temp[0]; // 명령어
            int data = Integer.parseInt(temp[1]); // 데이터
            
            // 명령어에 따른 케이스 분류
            if (oper.equals("I")) { // 삽입
                pq.offer(data);
            } else if (oper.equals("D") && !pq.isEmpty()) { // 삭제
                if (data == 1) { // 최댓값 삭제
                    pq.remove(Collections.max(pq));
                } else if (data == -1) { // 최솟값 삭제
                    pq.poll();
                }
            }
        }
        
        if (pq.size() > 0) { // 큐가 비어있지 않다면
            answer[0] = Collections.max(pq);
            answer[1] = pq.peek();
        }
        
        return answer;
    }
}