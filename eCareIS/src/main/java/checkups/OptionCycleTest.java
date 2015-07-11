package checkups;

import entity.Option;
import exceptions.CycleInOptionsDependencyException;
import exceptions.InconsistentOptionDependency;
import service.DTO.OptionDTO;
import service.OptionService;
import service.OptionServiceImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexey on 11.07.2015.
 */
public class OptionCycleTest {
    public static void main(String[] args) {
        OptionServiceImpl optionService = new OptionServiceImpl();
    //    System.out.println(optionService.isAddingDependencyCouseACycle(19, 22));

        Set<OptionDTO> optionDTOSet = optionService.getAllOptions();

//        for (OptionDTO opt1 : optionDTOSet)
//            for (OptionDTO opt2 : optionDTOSet)
//            {
//                boolean res = optionService.isInconsistencyPossible(opt1.getOptionId(), opt2.getOptionId());
//                System.out.println(opt1 +" " + opt2 + " "+ res);
//            }



//        try {
//            optionService.addDependency(19,21);
//        } catch (InconsistentOptionDependency inconsistentOptionDependency) {
//            inconsistentOptionDependency.printStackTrace();
//        } catch (CycleInOptionsDependencyException e) {
//            e.printStackTrace();
//        }
//        for (OptionDTO opt1 : optionDTOSet)
//            for (OptionDTO opt2 : optionDTOSet)
//            {
//                boolean res = optionService.isAddingDependencyCouseACycle(opt1.getOptionId(), opt2.getOptionId());
//                System.out.println(opt1 +" " + opt2 + " "+ res);
//            }
//        for (OptionDTO opt1 : optionDTOSet)
//            for (OptionDTO opt2 : optionDTOSet)
//            {
//                boolean res = optionService.optionDependencySetChecked(opt1.getOptionId(), opt2.getOptionId());
//                System.out.println(opt1 +" " + opt2 + " "+ res);
//            }

    }
}
