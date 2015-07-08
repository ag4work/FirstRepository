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


    public boolean isOptionsConsistentIncludingAllRequired(Integer optionId1, Integer optionId2) {
        Option option1 = optionDAO.get(optionId1);
        Option option2 = optionDAO.get(optionId2);
        Set<Option> DepTree1 = OptionServiceUtil.
                getAllRequiredOptionTree(option1);
        Set<Option> DepTree2 = OptionServiceUtil.
                getAllRequiredOptionTree(option2);
        for (Option optCounter1: DepTree1){
            for (Option optCounter2: DepTree2){
                if (optCounter1.getInconsistentOption().
                        contains(optCounter2)){
                    return false;
                }
            }
        }
        return true;
    }


    public boolean isOptionIncludingAllRequiredConsistentWithSet(Integer optionId, Set<OptionDTO> options) {
        for (OptionDTO optionDTO: options)
            if (!isOptionsConsistentIncludingAllRequired(optionId, optionDTO.getOptionId()))
                return false;
        return true;
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
