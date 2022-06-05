package groom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//자신의 식량의 반을 준다.(진우가 먼저 준다.)
//식량이 홀수이면 반을 나눈후 내림 처리한다.
//처음 식량은 첫번째 줄에 주어진다.
//n번째 날의 각각 가지고 있는 식량은?
public class GoodBrother {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String strFood = br.readLine();
        String strDay = br.readLine();

        Calculate calculate = new Calculate(strFood, strDay);
        calculate.init();
        calculate.transaction();

        System.out.println(calculate.getJinwooFood()+" "+calculate.getSunwooFood());

    }
}
class Calculate {

    private String strFood; //입력 값
    private String strDay; //날짜
    private int jinwooFood; //진우 식량
    private int sunwooFood; //선우 식량
    private int day = 0;

    public Calculate(String strFood, String strDay) {
        this.strFood = strFood;
        this.strDay = strDay;
    }

    //초기 설정
    public void init() {
        String[] str = strFood.split(" ");
        this.jinwooFood = Integer.parseInt(str[0]);
        this.sunwooFood = Integer.parseInt(str[1]);
        this.day = Integer.parseInt(strDay);
    }

    public void transaction() {

        for(int i = 0; i < day; i++) {
            if((i+1) % 2 != 0) {
                //진우식량이 홀수 이면
                if(this.jinwooFood%2 != 0) {
                    int temp = this.jinwooFood;
                    this.jinwooFood = (int) Math.floor(this.jinwooFood / 2);
                    this.sunwooFood += (temp - this.jinwooFood);
                }
                else {
                    this.jinwooFood = this.jinwooFood / 2;
                    this.sunwooFood += this.jinwooFood;
                }
            }
            else {
                //선우 식량이 홀수이면
                if(this.sunwooFood%2 != 0) {
                    int temp = this.sunwooFood;
                    this.sunwooFood = (int) Math.floor(this.sunwooFood / 2);
                    this.jinwooFood += (temp - this.sunwooFood);
                }
                else {
                    this.sunwooFood = this.sunwooFood / 2;
                    this.jinwooFood += this.sunwooFood;
                }
            }
        }
    }

    public int getJinwooFood() {
        return this.jinwooFood;
    }

    public int getSunwooFood() {
        return this.sunwooFood;
    }

}
