import java.util.*;

// Doubly Linked List 방식
class Solution {
    public String solution(int n, int k, String[] cmd) {
        Stack<Integer> cStack = new Stack<>();
        boolean[] isDeleted = new boolean[n];
        
        int[] prev = new int[n];
        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        next[n - 1] = -1;
                
        int current = k;
        
        for (String c : cmd) {
            switch(c.charAt(0)) {
                case 'U': {
                    int steps = Integer.parseInt(c.substring(2));
                    while (steps-- > 0) {
                        current = prev[current];
                    }
                    break;
                }
                case 'D': {
                    int steps = Integer.parseInt(c.substring(2));
                    while (steps-- > 0) {
                        current = next[current];
                    }
                    break;
                }
                case 'C': {
                    cStack.push(current);
                    isDeleted[current] = true;
                    
                    int targetPrev = prev[current];
                    int targetNext = next[current];
                    
                    if (targetPrev != -1) next[targetPrev] = targetNext;
                    if (targetNext != -1) prev[targetNext] = targetPrev;
                    
                    current = (targetNext != -1) ? targetNext : targetPrev;
                    break;
                }
                case 'Z': {
                    int target = cStack.pop();
                    isDeleted[target] = false;
                    
                    int targetPrev = prev[target];
                    int targetNext = next[target];
                    
                    if (targetPrev != -1) next[targetPrev] = target;
                    if (targetNext != -1) prev[targetNext] = target;
                    
                    break;
                }
            }
        }
        
        StringBuilder sb = new StringBuilder(n);
		
		for(int i = 0; i < n; i++) {
    		sb.append(isDeleted[i] ? 'X' : 'O');
		}
        
        return sb.toString();
    }
}

// ArrayList 방식 (효율성 테스트 실패 - 시간 초과)
// import java.util.*;

// class Solution {
//     public String solution(int n, int k, String[] cmd) {
//         Stack<Integer> cStack = new Stack<>();
        
//         List<Integer> table = new ArrayList<>();
//         for (int i = 0; i < n; i++) {
//             table.add(i);
//         }
        
//         int current = k;
//         int steps;
        
//         for (String c : cmd) {
//             switch(c.charAt(0)) {
//                 case 'U':
//                     steps = Integer.parseInt(c.substring(2));
//                     if (current < steps)
//                         current = 0;
//                     else
//                         current -= steps;
//                     break;
//                 case 'D':
//                     steps = Integer.parseInt(c.substring(2));
//                     if (table.size() - current <= steps)
//                         current = table.size() - 1;
//                     else
//                         current += steps;
//                     break;
//                 case 'C':
//                     cStack.push(table.get(current));
//                     table.remove(current);
//                     if (current == table.size())
//                         current--;
//                     // else 필요 x -> 자동으로 다음 행 가르킴
//                     break;
//                 case 'Z':
//                     int recover = cStack.pop();
//                     for (int i = 0; i < table.size(); i++) {
//                         if (table.get(i) > recover) {
//                             table.add(i, recover);
//                             if (current >= i)
//                                 current++;
//                             break;
//                         }
//                     }
//                     if (!table.contains(recover))
//                         table.add(recover);
//                     break;
//             }
//         }
        
//         StringBuilder sb = new StringBuilder();
        
//         for(int i = 0; i < n; i++)
//         {
//             sb.append('X');
//         }

//         for (int row : table) {
//             sb.setCharAt(row, 'O');
//         }
//         return sb.toString();
//     }
// }
