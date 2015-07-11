package service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import exceptions.CycleInOptionsDependencyException;
import exceptions.InconsistentOptionDependency;
import exceptions.OptionInconsistencyImpossibleException;
import service.DTO.OptionDTO;
import java.util.Set;

/**
 * Created by Alexey on 04.07.2015.
 */
public interface OptionService {

    public OptionDTO getOptionById(Integer optionId);
    public Set<OptionDTO> getAllOptions();
    public void addOption(OptionDTO optionDTO);
    public Set<OptionDTO> getDependentOptionTree(Integer optionId);
    public Set<OptionDTO> getRequiredOptionTree(Integer optionId);
    public boolean isOptionsConsistentIncludingAllRequired(Integer optionId1,
                                                           Integer optionId2);
    public boolean isOptionIncludingAllRequiredConsistentWithSet(
            Integer optionId, Set<OptionDTO> options);
    public void delete(Integer optionId);
    public void addDependency(Integer baseOptId, Integer dependentOptId) throws
            InconsistentOptionDependency, CycleInOptionsDependencyException;
    public void removeDependency(Integer baseOptionId, Integer dependentOptionId);
    public void addInconsistency(Integer optionId1, Integer optionId2) throws OptionInconsistencyImpossibleException;
    public void removeInconsistency(Integer optionId1, Integer optionId2);

}
