package checkups;

import DAO.OptionDAO;
import DAO.OptionDAOImpl;
import DAO.TariffDAO;
import DAO.TariffDAOImpl;
import entity.Option;
import entity.Tariff;
import service.DTO.OptionDTO;
import service.DTO.TariffDTO;
import service.TariffService;
import service.TariffServiceImpl;
import utils.EntityManagerFactorySingleton;
import utils.Mappers.TariffMapper;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexey on 05.07.2015.
 */
public class PossOptionAddToTariffTest {
    static  TariffService tariffService = new TariffServiceImpl();

    public static void main(String[] args) {
        Integer tariffId = 1;
        Set<OptionDTO> tariffPossibleOptions = tariffService.getTariffById(tariffId).getPossibleOption();
        System.out.println(tariffPossibleOptions);

    }

    private static void addPossOption(){
        TariffDAO tariffDAO = new TariffDAOImpl(EntityManagerFactorySingleton.getInstance());
        OptionDAO optionDAO = new OptionDAOImpl(EntityManagerFactorySingleton.getInstance());
        Integer optionId = 1;
        Integer tariffId = 1;
        Option option = optionDAO.get(optionId);
        Tariff tariff = tariffDAO.get(tariffId);
        tariff.getPossibleOption().add(option);
        tariffDAO.update(tariff);

    }

}
