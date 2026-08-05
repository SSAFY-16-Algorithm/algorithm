/*
- 베스트 앨범 : 장르 별로 가장 많이 재생된 노래를 2개씩 모은 것
- 노래는 고유 번호로 구분
- 노래를 수록하는 기준
- 1) 속한 노래가 많이 재생된 장르를 먼저 수록
- 2) 장르 내에서 많이 재생된 노래를 먼저 수록
- 3) 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록

- input: genres / plays
- output: 베스트 앨범에 들어갈 노래의 고유 번호의 list

예시)
genres
["classic", "pop", "classic", "classic", "pop"]

plays
[500, 600, 150, 800, 2500]

return
[4, 1, 3, 0]
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answerList = new ArrayList<>();
        
        // 2개의 HashMap을 사용해서 문제를 풀고자 함
        // 1) 장르 : 총 재생된 횟수
        HashMap<String, Integer> genreMap = new HashMap<>();
        // 2) 장르 : [[고유번호, 재생횟수]]
        HashMap<String, List<int[]>> genreSongMap = new HashMap<>();
        
        int N = genres.length; // N : 노래 개수
        
        for (int i=0; i<N; i++) {
            // getOrDefault 사용 시 map에 장르가 존재하는지 구분하지 않아도 됨
        	genreMap.put(genres[i], genreMap.getOrDefault(genres[i], 0) + plays[i]);
        	
        	if (!genreSongMap.containsKey(genres[i])) {
        		genreSongMap.put(genres[i], new ArrayList<>()); // 없으면 새 리스트 생성
        	}
        	genreSongMap.get(genres[i]).add(new int[]{i, plays[i]});
        }
        
        List<Map.Entry<String, Integer>> genreList = new ArrayList<>(genreMap.entrySet());
        //genreList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue())); // 오름차순
        genreList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue())); // 내림차순
        
        
        for (Map.Entry<String, Integer> genre : genreList) {
        	List<int[]> tmp = genreSongMap.get(genre.getKey());
        	tmp.sort((o1, o2) -> {
                if (o2[1] == o1[1]) {
                    return o1[0] - o2[0]; // 고유 번호가 낮은 노래를 먼저 수록 (오름차순)
                }
                return o2[1] - o1[1]; // 장르 내에서 많이 재생된 노래를 먼저 수록 (내림차순)
            });
        	
        	answerList.add(tmp.get(0)[0]);
            
            if (tmp.size() >= 2) {
            	answerList.add(tmp.get(1)[0]);
            }
        }
        
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
}