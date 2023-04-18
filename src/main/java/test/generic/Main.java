package test.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class Food {}
class Vegetable extends Food {}
class Fruit extends Food {}
class Apple extends Fruit {}
class Banana extends Fruit {}
class FruitBox <T  extends Fruit> {
    private List<T> fruits = new ArrayList<>();

    public void add(T fruit) {
        fruits.add(fruit);
    }

    public List<T> getFruits() {
        return fruits;
    }
}

// Food로 상한 경계 설정
class FoodBox<T extends Food> {
    private List<T> foods = new ArrayList<>();

    public void add(T food) {
        foods.add(food);
    }
    // T의 모든 상위 타입 가능
    public void print(List<? super T> list) {
        for(Object o : list) {
            System.out.println(o.getClass());
        }
    }

    public List<T> getFoods() {
        return foods;
    }
}

class Category<T> {

    private T t;
    private List<T> list = new ArrayList<>();

    // producer
    public void pushAll(Collection<? extends T> collection) {
        // 안전하게 값 저장
        for(T t : collection) {
            list.add(t);
        }
    }

    // consumer
    public void popAll(Collection<? super T> collection) {
        collection.addAll(list);
        list.clear();
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}

class CategoryHelper {

    // Fruit 상한 제한
    // Fruit의 하위 클래스만 가능
    public void popFruit(Category<? extends Fruit> category) {
        // category의 최상위 타입은 Noodle
        Fruit fruit = category.getT();
        // category.setT(new Noodle());
        /**
         * 현재 Category의 타입 파라미터를 Fruit 의 하위 클래스로 제한을 두었습니다.
         * 만약에 Category의 타입 파라미터가 Apple 라고 할 경우
         *
         * category.setT(new Fruit());
         * 은 하위 타입(Apple)에 상위 타입(Fruit)을 저장하려는 시도를 하는 것 입니다.!!
         * 따라서 컴파일 오류!
         */
    }

    // Fruit 하한 제한
    // Fruit의 상위 클래스만 가능
    public void pushFruit(Category<? super Fruit> category) {
        /**
         * 현재 Category 타입 파라미터를 Noodle의 상위 클래스로 제한을 두었습니다.
         * 만약에 Category의 타입 파라미터가 Pasta라고 할 지라도
         * category.getT();을 하면 Noodle보다 상위 타입이 올수도 있기 때문에
         * Noodle noodle = category.getT();는 불가 합니다.
         *
         * 단, 타입 캐스팅을 하는 경우에는 가능합니다.
         */
        Fruit fruit = (Fruit) category.getT();
        // Noodle noodle = category.getT(); // 불가능

        // Fruit 하위 타입은 저장 가능(하위 타입이기 때문)
        category.setT(new Apple());
        category.setT(new Banana());

        // Fruit 상위 타입은 저장 불가(어떤 상위 타입이 올지 모르기 때문)
        // category.setT(new Food());
    }
}

public class Main {
    public static void main(String[] args) {
        FruitBox<Apple> appleFruitBox = new FruitBox<>();
        appleFruitBox.add(new Apple());

        FoodBox<Food> foodBox = new FoodBox<>();
        foodBox.add(new Vegetable());
        foodBox.add(new Apple());
        foodBox.add(new Banana());

        foodBox.print(foodBox.getFoods());

        /**
         * 결과
         * class test.generic.Vegetable
         * class test.generic.Apple
         * class test.generic.Banana
         */
    }
}
//class FruitBox {
//    private Object fruit;
//
//    public FruitBox(Object fruit) {
//        this.fruit = fruit;
//    }
//
//    public Object getFruit() {
//        return fruit;
//    }
//}
