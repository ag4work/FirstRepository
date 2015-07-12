package checkups;

import DAO.OptionDAO;
import DAO.OptionDAOImpl;
import entity.Option;
import service.DTO.OptionDTO;
import service.OptionService;
import service.OptionServiceImpl;
import utils.EntityManagerFactorySingleton;

/**
 * Created by Alexey on 07.07.2015.
 */
public class optionsCheck {
    public static void main(String[] args) {
//        OptionDAOTest();
        OptionService optionService = OptionServiceImpl.getInstance();
        OptionDTO optionDTO = optionService.getOptionById(4);
        System.out.println(optionDTO.getDependentOption());

        OptionDTO optionDTO2 = optionService.getOptionById(6);
        System.out.println(optionDTO2.getRequiredOption());

    }

    private static void OptionDAOTest() {
        OptionDAO optionDAO = new OptionDAOImpl(EntityManagerFactorySingleton.getInstance());
        Option option = optionDAO.get(6);
        System.out.println(option.getRequiredOption());
        Option option1 = optionDAO.get(4);
        System.out.println(option1.getDependentOption());
    }
}
