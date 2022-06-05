package programmers;

import java.util.HashMap;
import java.util.Map;

/***
 * 입력된 번호 중 같은 번호를 포함하고 있으면 false, 없으면 true
 */
public class PhoneNumberList {
    public static void main(String[] args) {

    }

    class Solution {
        public boolean solution(String[] phone_book) {

            Map<String, Integer> map = new HashMap<>();

            for(int i = 0; i < phone_book.length; i++) {
                map.put(phone_book[i], i);
            }

            for (int i = 0; i<phone_book.length; i++){
                for(int j = 0; j < phone_book[i].length(); j++) {
                    if(map.containsKey(phone_book[i].substring(0, j))) {
                        return false;
                    }
                }
            }

            return true;
        }
    }
}
