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
    boolean isOptionIncludingAllRequiredConsistentWithSet(
            Integer optionId, Set<OptionDTO> options);
    void delete(Integer optionId);
    void addDependency(Integer baseOptId, Integer dependentOptId) throws
            InconsistentOptionDependency, CycleInOptionsDependencyException;
    void removeDependency(Integer baseOptionId, Integer dependentOptionId);
    void addInconsistency(Integer optionId1, Integer optionId2) throws OptionInconsistencyImpossibleException;
    void removeInconsistency(Integer optionId1, Integer optionId2);

}
