package checkups.ValidationTest;

import javax.validation.constraints.NotNull;

/**
 * Created by Alexey on 27.06.2015.
 */
public class TestBean {
    @NotNull
    private Integer a;

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }
}
