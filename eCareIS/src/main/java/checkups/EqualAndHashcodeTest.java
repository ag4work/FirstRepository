package checkups;

import entity.Option;

/**
 * Created by Alexey on 05.07.2015.
 */
public class EqualAndHashcodeTest {
    public static void main(String[] args) {
        Option option1 = new Option();
        Option option2 = new Option();
        option1.setTitle(new String("hello"));
        option2.setTitle(new String("hello"));

        if (option1.equals(option2)) System.out.println("Options are equals");
        System.out.println(option1.hashCode());
        System.out.println(option2.hashCode());


    }
}
