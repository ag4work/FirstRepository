package service;

import dao.OptionDAO;
import entity.Option;
import exceptions.CycleInOptionsDependencyException;
import exceptions.InconsistentOptionDependency;
import exceptions.OptionInconsistencyImpossibleException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.DTO.OptionDTO;
import utils.Mappers.OptionMapper;

import java.util.HashSet;
import java.util.Set;

@Service
public final class OptionServiceImpl implements OptionService {

    /**
     * OptionDAO instance.
     */
    @Autowired
    private OptionDAO optionDAO;
    /**
     * log4j instance.
     */
    private Logger logger = Logger.getLogger(OptionServiceImpl.class);


    @Override
    public OptionDTO getOptionById(final Integer optionId) {
        if (optionId == null) {
            return null;
        }
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
    @Transactional
    public void addOption(final OptionDTO optionDTO) {
        optionDAO.add(OptionMapper.DTOToEntity(optionDTO));
    }

    @Override
    public Set<OptionDTO> getDependentOptionTree(final Integer optionId) {
        return OptionMapper.EntitySetToDTOSet(
                OptionServiceUtil.getAllDependentOptionTree(optionDAO.get(
                        optionId)));
    }
    @Override
    public Set<OptionDTO> getRequiredOptionTree(final Integer optionId) {
        return OptionMapper.EntitySetToDTOSet(
                OptionServiceUtil.getAllRequiredOptionTree(optionDAO.get(
                        optionId)));
    }

    /**
     * is Options Consistent Including All Required options.
     * @param optionId1 optionId1
     * @param optionId2 optionId2
     * @return bool
     */
    @Override
    public boolean isOptionsConsistentIncludingAllRequired(
            final Integer optionId1, final Integer optionId2) {
        Option option1 = optionDAO.get(optionId1);
        Option option2 = optionDAO.get(optionId2);
        Set<Option> DepTree1 = OptionServiceUtil.
                getAllRequiredOptionTree(option1);
        Set<Option> DepTree2 = OptionServiceUtil.
                getAllRequiredOptionTree(option2);
        for (Option optCounter1: DepTree1) {
            for (Option optCounter2: DepTree2) {
                if (optCounter1.getInconsistentOption().
                        contains(optCounter2)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * is Option Including All Required Consistent With Set of options.
     * @param optionId optionId
     * @param options options set
     * @return bool
     */
    @Override
    public boolean isOptionIncludingAllRequiredConsistentWithSet(
            final Integer optionId, final Set<OptionDTO> options) {
        for (OptionDTO optionDTO: options) {
            if (!isOptionsConsistentIncludingAllRequired(optionId, optionDTO.
                    getOptionId())) {
                return false;
            }
        }
        return true;
    }
    @Transactional
    @Override
    public void delete(final Integer optionId) {
        optionDAO.delete(optionId);
    }

    @Transactional
    @Override
    public void removeDependency(final Integer baseOptionId,
                                 final Integer dependentOptionId) {
        Option baseOption = optionDAO.get(baseOptionId);
        Option dependentOption = optionDAO.get(dependentOptionId);
        baseOption.getDependentOption().remove(dependentOption);
        optionDAO.update(baseOption);
    }
    @Transactional
    @Override
    public void addInconsistency(final Integer optionId1,
                                 final Integer optionId2)
            throws OptionInconsistencyImpossibleException {
        logger.info("addInconsistency, opt Ids:" + optionId1 + " " + optionId2);
        if (isInconsistencyPossible(optionId1, optionId2)) {
            Option option1 = optionDAO.get(optionId1);
            Option option2 = optionDAO.get(optionId2);
            option1.getInconsistentOption().add(option2);
            option2.getInconsistentOption().add(option1);
            optionDAO.update(option1);
            optionDAO.update(option2);
        } else {
            logger.warn("Inconsistency can't be set due to existing "
                    + "dependency in trees");
            throw new OptionInconsistencyImpossibleException();
        }
    }
    @Transactional
    @Override
    public void removeInconsistency(final Integer optionId1,
                                    final Integer optionId2) {
        Option option1 = optionDAO.get(optionId1);
        Option option2 = optionDAO.get(optionId2);
        option1.getInconsistentOption().remove(option2);
        option2.getInconsistentOption().remove(option1);
        optionDAO.update(option1);
        optionDAO.update(option2);
    }

    /**
     * Adding an option as dependent to another, base option.
     * @param baseOptId base Option Id
     * @param dependentOptId dependent Option Id
     * @return bool
     */
    @Transactional
    @Override
    public void addDependency(final Integer baseOptId, final Integer
            dependentOptId) throws InconsistentOptionDependency,
            CycleInOptionsDependencyException {
        logger.info("addDependency, opt Ids:" + baseOptId + " "
                + dependentOptId);
        if (!isAddingDependencyCouseACycle(baseOptId, dependentOptId)) {
            if (optionDependencySetChecked(baseOptId,
                    dependentOptId)) {
                Option baseOption = optionDAO.get(baseOptId);
                Option dependentOption = optionDAO.get(dependentOptId);
                baseOption.getDependentOption().add(dependentOption);
                optionDAO.update(baseOption);
            } else {
                logger.warn("dependency can't be set due to inconsistency in "
                        + "trees");
                throw new InconsistentOptionDependency();
            }
        } else {
            logger.warn("dependency can't be set due to it will cause a cycle");
            throw new CycleInOptionsDependencyException();
        }
        logger.info("addDependency added successfully");
    }

    /**
     * the method check for possibility to make two options inconsistent
     * All you need for that is to check that there is no option
     * that depends from option1 and option2 at the same time.
     * In other words we get all depdendent option tree by first option
     * as well as second option. Then we check that those two sets (or tree)
     * don't intersect (e.g. they don't have common options)
     * @param option1Id option1Id
     * @param option2Id option2Id
     * @return bool
     */

    private boolean isInconsistencyPossible(final Integer option1Id,
                                           final Integer option2Id) {
        Option option1 = optionDAO.get(option1Id);
        Option option2 = optionDAO.get(option2Id);
        Set<Option> opt1DependencyTree = OptionServiceUtil.
                getAllDependentOptionTree(option1);
        Set<Option> opt2DependencyTree = OptionServiceUtil.
                getAllDependentOptionTree(option2);

        for (Option opt1: opt1DependencyTree) {
            if (opt2DependencyTree.contains(opt1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check for possible cycles after  dependency
     * Base idea of the check is to get all dependent options for
     * given dependentOptId. And if that tree contains baseOptId
     * there will be a cycle.
     * This method is first of two checks before adding dependency
     * between two options
     * @param baseOptId baseOptionId
     * @param dependentOptId dependentOptionId
     * @return bool
     */
    
    private boolean isAddingDependencyCouseACycle(final Integer baseOptId,
                                                  final Integer
                                                          dependentOptId) {
        OptionDTO baseOption = getOptionById(baseOptId);
        Set<OptionDTO> dependentOptionsTreeForDependentOption =
                getDependentOptionTree(dependentOptId);
        return dependentOptionsTreeForDependentOption.contains(baseOption);
    }

    /**
     * Second and last method before adding two options dependency.
     * Adding a dependency we need to union three trees.
     * 1. required options for given base option - Set A
     * 2. required options for given dependent option  - Set B
     * 3. dependent options(yes!) for given dependent(yes!) option Set B'
     * Then we need to check that each option from A is not inconsistent
     * with each option from union B and B'
     * @param baseOptId baseOptionId
     * @param dependentOptId dependentOptionId
     * @return bool
     */

    private boolean optionDependencySetChecked(
            final Integer baseOptId, final Integer dependentOptId) {
        Option baseOption = optionDAO.get(baseOptId);
        Option dependentOption = optionDAO.get(dependentOptId);
        Set<Option> baseReqTree = OptionServiceUtil.
                getAllRequiredOptionTree(baseOption);
        Set<Option> depOptAndDepTree = OptionServiceUtil.
                getAllDependentOptionTree(dependentOption);
        Set<Option> depOptAndReqTree = OptionServiceUtil.
                getAllRequiredOptionTree(dependentOption);
        Set<Option> unionOfTreesOfDependentOption = depOptAndDepTree;
        unionOfTreesOfDependentOption.addAll(depOptAndReqTree);

        for (Option opt1: baseReqTree) {
            for (Option opt2 : unionOfTreesOfDependentOption) {
                if (opt1.getInconsistentOption().contains(opt2)) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Option Service Util class. it Finds dependent and
     * required option trees
     */
    private final static class OptionServiceUtil {
        /**
         * logger instance.
         */
        private static Logger logger = Logger.getLogger(
                OptionServiceUtil.class);
        /**
         * utility variable which stores allDependentOptionTree.
         */
        private static Set<Option> allDependentOptionTree;
        /**
         * utility variable which stores allRequiredOptionTree.
         */
        private static Set<Option> allRequiredOptionTree;


        private OptionServiceUtil() {
        }

        /**
         * this method fills list above with all dependent options
         * for given option.
         * @param option option
         */
        private static void addDependentOptions(final Option option) {
            if (option == null) {
                return;
            }
            if (allDependentOptionTree.contains(option)) {
                logger.warn("Cycle on dependent options. We've come again to "
                        + "option with id:" + option.getOptionId());
                return;
            }
            allDependentOptionTree.add(option);

            Set<Option> dependentOptionSet = option.getDependentOption();
            if (dependentOptionSet == null || dependentOptionSet.isEmpty()) {
                return;
            }
            for (Option dependentOption : dependentOptionSet) {
                addDependentOptions(dependentOption);
            }
        }

        /**
         * return  Dependent Option Tree.
         * @param option option
         * @return bool
         */
        public static synchronized Set<Option> getAllDependentOptionTree(final Option option) {
            logger.info("getting AllDependentOptionTree for optionId"
                    + option.getOptionId());
            allDependentOptionTree = new HashSet<Option>();
            addDependentOptions(option);
            return allDependentOptionTree;
        }

        /**
         * this method fills list above with all required options
         * for given option.
         * @param option option
         */
        private static void addRequiredOptions(final Option option) {
            if (option == null) {
                return;
            }
            if (allRequiredOptionTree.contains(option)) {
                logger.warn("Cycle on required options. We've come again to "
                        + "option with id:" + option.getOptionId());
                return;
            }
            allRequiredOptionTree.add(option);

            Set<Option> requiredOptionSet = option.getRequiredOption();
            if (requiredOptionSet == null || requiredOptionSet.isEmpty()) {
                return;
            }
            for (Option requiredOption : requiredOptionSet) {
                addRequiredOptions(requiredOption);
            }
        }

        /**
         * return AllRequiredOptionTree.
         * @param option option
         * @return set of options
         */
        public static synchronized Set<Option> getAllRequiredOptionTree(final Option option){
            logger.info("getting AllRequiredOptionTree for optionId"
                    + option.getOptionId());
            allRequiredOptionTree = new HashSet<Option>();
            addRequiredOptions(option);
            return allRequiredOptionTree;
        }

    }



}
