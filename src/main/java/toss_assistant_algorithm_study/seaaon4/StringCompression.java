package toss_assistant_algorithm_study.seaaon4;

import java.util.*;

public class StringCompression {

    public static void main(String[] args) {
        Solution solution = new Solution();
        //System.out.println(solution.compress(new char[]{'a','a','b','b','c','c','c'}));
        //System.out.println(solution.compress(new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b'}));
        System.out.println(solution.compress(new char[]{'a','a','a','b','b','a','a'}));
    }

    static class Solution {
        public int compress(char[] chars) {
            if(chars.length == 1) return 1;

            StringBuilder sb = new StringBuilder();
            int count = 0;
            int index = 0;
            char prev = chars[0];
            for(int i = 0; i < chars.length; i++) {
                char now = chars[i];

                if(prev == now) {
                    count++;
                } else {
                    chars[index++] = prev;
                    sb.append(prev);

                    if(count > 1) {
                        String countStr = String.valueOf(count);
                        for (int j = 0; j < countStr.length(); j++) {
                            chars[index++] = countStr.charAt(j);
                        }
                        sb.append(countStr);
                    }

                    count = 1;
                }

                prev = chars[i];

                if(i == chars.length - 1) {
                    chars[index++] = prev;
                    sb.append(prev);

                    if(count > 1) {
                        String countStr = String.valueOf(count);
                        for (int j = 0; j < countStr.length(); j++) {
                            chars[index++] = countStr.charAt(j);
                        }
                        sb.append(countStr);
                    }
                }
            }

            return sb.toString().length();
        }

        public int compress1(char[] chars) {

            if(chars.length == 1) return 1;

            Map<Character, Integer> map = new HashMap<>();
            for(char ch : chars) {
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            }

            StringBuilder sb = new StringBuilder();
            Set<Character> characterSet = map.keySet();
            int index = 0;
            for(Character ch : characterSet) {
                chars[index++] = ch;
                if(map.get(ch) > 1) {
                    String count = map.get(ch).toString();
                    for(int i = 0; i < count.length(); i++) {
                        chars[index++] = count.charAt(i);
                    }
                    sb.append(ch).append(map.get(ch));
                } else {
                    sb.append(ch);
                }
            }

            return sb.length();
        }
    }
}
