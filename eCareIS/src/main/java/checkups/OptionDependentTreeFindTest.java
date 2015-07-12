package checkups;

import service.DTO.OptionDTO;
import service.OptionService;
import service.OptionServiceImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexey on 07.07.2015.
 */
public class OptionDependentTreeFindTest {
    public static void main(String[] args) {
        OptionService optionService = OptionServiceImpl.getInstance();
//        System.out.println(optionService.getDependentOptionTree(6));
//        System.out.println(optionService.getDependentOptionTree(6).size());
        // System.out.println(optionService.getRequiredOptionTree(3));

//        System.out.println(optionService.isOptionsConsistentIncludingAllRequired(2,4));
        Set<OptionDTO> optionDTOs = new HashSet<OptionDTO>();
        OptionDTO optionDTO1 = optionService.getOptionById(4);
        OptionDTO optionDTO2 = optionService.getOptionById(2);
        optionDTOs.add(optionDTO1);
        optionDTOs.add(optionDTO2);
        System.out.println(optionService.isOptionIncludingAllRequiredConsistentWithSet(11, optionDTOs));


    }

    private static void testConsTwoOptionsWithTree(OptionService optionService) {
        Set<OptionDTO> all1 = optionService.getAllOptions();
        Set<OptionDTO> all2 = optionService.getAllOptions();
        for (OptionDTO op1 : all1) {
            for (OptionDTO op2 : all1) {
                System.out.println(op1.getTitle() + " " +op2.getTitle()+":"+
                        optionService.
                                isOptionsConsistentIncludingAllRequired(op1.getOptionId(), op2.getOptionId()));
            }
        }
    }
}
