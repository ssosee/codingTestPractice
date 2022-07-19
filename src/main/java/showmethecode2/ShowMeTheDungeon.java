package showmethecode2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShowMeTheDungeon {
    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("p");
        list.add("p");
        list.add("l");
        list.add("e");

        List<String> list2 = new ArrayList<>();

        list.forEach(System.out::println);
        System.out.println("====================");
    }

    static class Dto {
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;
    }
}
