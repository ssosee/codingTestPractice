package dev;

import java.util.*;

public class test22 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] queries = {"desktop1 request", "desktop2 request", "desktop3 request", "desktop3 request", "desktop1 release", "desktop3 request"};
        solution.solution(2, queries);
    }
    static class Solution {
        static final String IP_PREFIX = "192.168.0.";
        static final String REJECT = "reject";
        public String[] solution(int n, String[] queries) {
            List<String> response = new ArrayList<>();
            Map<String, Boolean> ipList = new HashMap<>();
            for(int i = 1; i <= n; i++) {
                //true: 할당가능, false: 할당불가
                ipList.put(IP_PREFIX+i, true);
            }
            //ip 할당
            Map<String, String> map = new HashMap<>();
            for(int i = 0; i < queries.length; i++) {
                String[] str = queries[i].split(" ");
                //요청
                if(str[1].equals("request")) {
                    //모든 ip 사용중
                    int cnt = 0;
                    for(int ii = 1; ii <= ipList.size(); ii++) {
                        if(!ipList.get(IP_PREFIX+ii)) {
                            cnt++;
                        }
                    }
                    if(cnt == ipList.size()) {
                        response.add(str[0]+" "+REJECT);
                    }
                    //할당 받은 적 없음
                    else if(map.get(str[0]) == null) {
                        for(int j = 1; j <= ipList.size(); j++) {
                            if(ipList.get(IP_PREFIX+j)) {
                                map.put(str[0], IP_PREFIX+j);
                                response.add(str[0]+" "+IP_PREFIX+j);
                                ipList.put(IP_PREFIX+j, false);
                                break;
                            }
                        }
                    }
                    //할당 받은 적 있음
                    else {
                        response.add(str[0]+" "+map.get(str[0]));
                    }
                }
                //반납
                else {
                    ipList.put(map.get(str[0]), true);
                }
            }

            String[] answer = new String[response.size()];
            for(int i = 0; i < response.size(); i++) {
                answer[i] = response.get(i);
            }

            return answer;
        }
    }
    static class Desktop {
        private String name;
        private String ipAddr;
    }
}
