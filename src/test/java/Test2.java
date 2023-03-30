import org.junit.jupiter.api.Test;

public class Test2 {
    public String solution(String[] H, String[] C) {
        String answer = "";

        String BACK = "BACK";
        String NEXT = "NEXT";

        int point = H.length - 1;
        for(int i = 0; i < C.length; i++) {
            if(C[i].equals(BACK) && point > 0) {
                point--;
            } else if(point < H.length - 1){
                point++;
            }
        }

        answer = H[point];

        return answer;
    }

    @Test
    void test() {
        solution(new String[]{"www.google.com", "www.yahoo.com", "www.midasit.com"}, new String[]{"BACK","BACK","NEXT"});
    }
}
