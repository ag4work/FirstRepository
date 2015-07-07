package checkups;

import service.OptionService;
import service.OptionServiceImpl;

/**
 * Created by Alexey on 07.07.2015.
 */
public class OptionDependentTreeFindTest {
    public static void main(String[] args) {
        OptionService optionService = new OptionServiceImpl();
//        System.out.println(optionService.getDependentOptionTree(6));
//        System.out.println(optionService.getDependentOptionTree(6).size());
        System.out.println(optionService.getRequiredOptionTree(3));


    }
}
