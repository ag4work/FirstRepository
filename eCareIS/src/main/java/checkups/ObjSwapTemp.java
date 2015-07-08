package checkups;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 08.07.2015.
 */
public class ObjSwapTemp {
    public static void main(String[] args) {
        List<A> list = new ArrayList<A>();
        list.add(new A(2));
        list.add(new A(3));
        list.add(new A(4));

        A e = new A(4);
        System.out.println(list);
        System.out.println(e);

        int index = list.indexOf(e);

        A temp = list.get(0);

        list.set(0,list.get(index));
        list.set(index,temp);

        e=temp;

        System.out.println(list);
        System.out.println(e);
    }
}

class A {
    Integer k;

    public A(Integer k) {
        this.k = k;
    }

    public Integer getK() {
        return k;
    }

    public void setK(Integer k) {
        this.k = k;
    }

    @Override
    public String toString() {
        return "A{" +
                "k=" + k +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        A a = (A) o;

        if (k != null ? !k.equals(a.k) : a.k != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return k != null ? k.hashCode() : 0;
    }
}