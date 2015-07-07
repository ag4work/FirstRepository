package checkups;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexey on 07.07.2015.
 */
public class EmptySetCheck {
    public static void main(String[] args) {
        Set<Integer> integers = Collections.EMPTY_SET;
//        Set<Integer> integers = null;
        if (integers==null || integers.isEmpty()) System.out.println("is empty");
        for (Integer i : integers){
            System.out.println(i);
        }
    }
}
