import java.util.*;

// PriorityQueue 방식
class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> apq = new PriorityQueue<>();
        PriorityQueue<Integer> dpq = new PriorityQueue<>(Collections.reverseOrder());
        
        for (String operation : operations) {
            char op = operation.charAt(0);
            int num = Integer.valueOf(operation.substring(2));
            if (op == 'I') {
                apq.add(num);
                dpq.add(num);
            }
            else if (op == 'D') {
                if (num == -1) {
                    dpq.remove(apq.poll());
                } else if (num == 1) {
                    apq.remove(dpq.poll());
                }
            }
        }
        
        int[] answer = new int[2];
        
        if (apq.isEmpty())
            return answer;
        
        answer[0] = dpq.peek();
        answer[1] = apq.peek();
        
        return answer;
    }
}

// TreeMap 방식
// class Solution {
//     public int[] solution(String[] operations) {
//         TreeMap<Integer, Integer> map = new TreeMap<>();
        
//         for (String operation : operations) {
//             char op = operation.charAt(0);
//             int num = Integer.valueOf(operation.substring(2));
//             if (op == 'I')
//                 map.put(num, map.getOrDefault(num, 0) + 1);
//             else if (op == 'D') {
//                 if (map.isEmpty())
//                     continue;
                
//                 if (num == -1) {
//                     int key = map.firstKey();
//                     if (map.get(key) == 1)
//                         map.remove(key);
//                     else
//                         map.put(key, map.get(key) - 1);
//                 } else if (num == 1) {
//                     int key = map.lastKey();
//                     if (map.get(key) == 1)
//                         map.remove(key);
//                     else
//                         map.put(key, map.get(key) - 1);
//                 }
//             }
//         }
        
//         int[] answer = new int[2];
        
//         if (map.isEmpty())
//             return answer;
        
//         answer[0] = map.lastKey();
//         answer[1] = map.firstKey();
        
//         return answer;
//     }
// }
