package service;

import DAO.OptionDAO;
import DAO.OptionDAOImpl;
import entity.Option;
import org.apache.log4j.Logger;
import service.DTO.OptionDTO;
import utils.EntityManagerFactorySingleton;
import utils.Mappers.OptionMapper;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexey on 04.07.2015.
 */
public class OptionServiceImpl implements OptionService {

    OptionDAO optionDAO = new OptionDAOImpl(EntityManagerFactorySingleton.getInstance());

    @Override
    public OptionDTO getOptionById(Integer optionId) {
        if (optionId==null) return null;
        OptionDTO optionDTO = OptionMapper.EntityToDTOWithSet(
                optionDAO.get(optionId));
        return optionDTO;
    }

    @Override
    public Set<OptionDTO> getAllOptions() {
        Set<Option> options = new HashSet<Option>(optionDAO.getAll());
        return OptionMapper.EntitySetToDTOSetWithSets(options);
    }

    @Override
    public void addOption(OptionDTO optionDTO) {
        optionDAO.add(OptionMapper.DTOToEntity(optionDTO));
    }

    @Override
    public Set<OptionDTO> getDependentOptionTree(Integer optionId) {
        return OptionMapper.EntitySetToDTOSet(
                OptionServiceUtil.getAllDependentOptionTree(optionDAO.get(optionId)));
    }
    @Override
    public Set<OptionDTO> getRequiredOptionTree(Integer optionId) {
        return OptionMapper.EntitySetToDTOSet(
                OptionServiceUtil.getAllRequiredOptionTree(optionDAO.get(optionId)));
    }

    private final static class OptionServiceUtil{
        static Logger logger = Logger.getLogger(OptionServiceUtil.class);

        private static Set<Option> allDependentOptionTree;
        private static Set<Option> allRequiredOptionTree;

        private static void addDependentOptions(Option option){
            if (option==null) return;
            if (allDependentOptionTree.contains(option)){
                logger.warn("Cycle on dependent options. We've come again to " +
                        "option with id:"+option.getOptionId());
                return;
            }
            allDependentOptionTree.add(option);

            Set<Option> dependentOptionSet = option.getDependentOption();
            if (dependentOptionSet ==null || dependentOptionSet.isEmpty()){
                return;
            }
            for (Option dependentOption : dependentOptionSet){
                addDependentOptions(dependentOption);
            }
        }
        public static Set<Option> getAllDependentOptionTree(Option option){
            logger.info("getting AllDependentOptionTree for optionId" +
                    option.getOptionId());
            allDependentOptionTree = new HashSet<Option>();
            addDependentOptions(option);
            return allDependentOptionTree;
        }

        private static void addRequiredOptions(Option option){
            if (option==null) return;
//            logger.info(option.getOptionId());
            if (allRequiredOptionTree.contains(option)){
                logger.warn("Cycle on required options. We've come again to " +
                        "option with id:"+option.getOptionId());
                return;
            }
            allRequiredOptionTree.add(option);
//            logger.info(allDependentOptionTree);

            Set<Option> requiredOptionSet = option.getRequiredOption();
            if (requiredOptionSet ==null || requiredOptionSet.isEmpty()){
                return;
            }
            for (Option requiredOption : requiredOptionSet){
                addRequiredOptions(requiredOption);
            }
        }
        public static Set<Option> getAllRequiredOptionTree(Option option){
            logger.info("getting AllRequiredOptionTree for optionId" +
                    option.getOptionId());
            allRequiredOptionTree = new HashSet<Option>();
            addRequiredOptions(option);
            return allRequiredOptionTree;
        }
    }
}
