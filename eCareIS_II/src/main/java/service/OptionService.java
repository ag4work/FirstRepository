package service;

import exceptions.CycleInOptionsDependencyException;
import exceptions.InconsistentOptionDependency;
import exceptions.OptionInconsistencyImpossibleException;
import org.springframework.stereotype.Service;
import service.DTO.OptionDTO;
import java.util.Set;

/**
 * Created by Alexey on 04.07.2015.
 */
@Service
public interface OptionService {
    /**
     * return options by id.
     * @param optionId optionId
     * @return
     */
    OptionDTO getOptionById(Integer optionId);

    /**
     * returns all available options.
     * @return set of options
     */
    Set<OptionDTO> getAllOptions();

    /**
     * add new option
     * @param optionDTO optionDTO
     */
    void addOption(OptionDTO optionDTO);

    /**
     * returns the set of options which all dependent of
     * given option. one by second, second by third and so on.
     * @param optionId optionId
     * @return set of options
     */
    Set<OptionDTO> getDependentOptionTree(Integer optionId);

    /**
     * The same as previos bu return set of  all required option
     * Actually it is a tree of linked options
     * @param optionId
     * @return set of options
     */
    Set<OptionDTO> getRequiredOptionTree(Integer optionId);

    /**
     * check for two options Consistent Including their Required
     * trees of options
     * @param optionId1 optionId1
     * @param optionId2 optionId2
     * @return bool
     */
    boolean isOptionsConsistentIncludingAllRequired(Integer optionId1,
                                                           Integer optionId2);
    /**
     * is Option Including All Required Consistent With Set of options.
     * @param optionId optionId
     * @param options options set
     * @return bool
     */
    boolean isOptionIncludingAllRequiredConsistentWithSet(
            Integer optionId, Set<OptionDTO> options);

    /**
     * remove an option
     * @param optionId optionId
     */
    void delete(Integer optionId);

    /**
     * adds dependent option for the base option
     * does quite complex checks before adding
     * @param baseOptId base Option Id
     * @param dependentOptId dependent Option Id
     * @throws InconsistentOptionDependency
     * @throws CycleInOptionsDependencyException
     */
    void addDependency(Integer baseOptId, Integer dependentOptId) throws
            InconsistentOptionDependency, CycleInOptionsDependencyException;

    /**
     * removes dependency of dependent option from base option
     * @param baseOptionId base Option Id
     * @param dependentOptionId dependent Option Id
     */
    void removeDependency(Integer baseOptionId, Integer dependentOptionId);

    /**
     * add inconsistency between two options
     * does quite hard checks inside
     * @param optionId1 option1 Id
     * @param optionId2 option2 Id
     * @throws OptionInconsistencyImpossibleException
     */
    void addInconsistency(Integer optionId1, Integer optionId2) throws
            OptionInconsistencyImpossibleException;

    /**
     * removes inconsistency between two options
     * @param optionId1 option1 Id
     * @param optionId2 option2 Id
     */
    void removeInconsistency(Integer optionId1, Integer optionId2);

}
