package inflearn.hashandtree;

import java.util.*;

public class test4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String t = sc.nextLine();
        System.out.println(new Solution().getAnswer(s, t));
    }
    static class Solution {
        public int getAnswer(String s, String t) {
            Map<Character, Integer> map = new HashMap<>();
            Map<Character, Integer> map2 = new HashMap<>();
            int answer = 0;

            for(int i = 0; i < t.length() - 1; i++) {
                map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0)+1);
            }

            for(int i = 0; i < t.length(); i++) {
                map2.put(t.charAt(i), map2.getOrDefault(t.charAt(i), 0)+1);
            }

            int lt = 0;
            for(int rt = t.length() - 1; rt < s.length(); rt++) {
                map.put(s.charAt(rt), map.getOrDefault(s.charAt(rt), 0)+1);
                if(map.equals(map2)) answer++;
                map.put(s.charAt(lt), map.get(s.charAt(lt)) - 1);
                if(map.get(s.charAt(lt)) == 0) {
                    map.remove(s.charAt(lt));
                }
                lt++;
            }

            return answer;
        }
    }
}
