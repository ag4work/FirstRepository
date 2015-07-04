package utils.Mappers;

import entity.Option;
import service.DTO.OptionDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexey on 03.07.2015.
 */
public class OptionMapper {

//private Set<OptionDTO> dependentOption;
//private Set<OptionDTO> inconsistentOption;
//private Set<TariffDTO> tariffHasThisOption;
//private Set<ContractDTO> contracts;

    public static Option DTOToEntity(OptionDTO optionDTO) {

        if (optionDTO == null) return null;

        Option option = new Option();

        option.setOptionId(optionDTO.getOptionId());
        option.setTitle(optionDTO.getTitle());
        option.setMonthlyCost(optionDTO.getMonthlyCost());
        option.setActivationCharge(optionDTO.getActivationCharge());

        return option;
    }

    public static OptionDTO EntityToDTO(Option option) {
        if (option == null) return null;

        OptionDTO optionDTO = new OptionDTO();

        optionDTO.setOptionId(option.getOptionId());
        optionDTO.setTitle(option.getTitle());
        optionDTO.setMonthlyCost(option.getMonthlyCost());
        optionDTO.setActivationCharge(option.getActivationCharge());

        return optionDTO;
    }

//    public static Option DTOToEntityWithSets(OptionDTO optionDTO) {
//        if (optionDTO == null) return null;
//        Option option = DTOToEntity(optionDTO);
//
//    }
    public static Set<Option> DTOSetToEntitySet(Set<OptionDTO> optionDTOs){
        if (optionDTOs == null) return null;
        Set<Option> options = new HashSet<Option>();
        for (OptionDTO optionDTO : optionDTOs){
            options.add(OptionMapper.DTOToEntity(optionDTO));
        }
        return options;
    }

    public static Set<OptionDTO> EntitySetToDTOSet(Set<Option> options){
        if (options==null) return null;
        Set<OptionDTO> optionDTOs = new HashSet<OptionDTO>();
        for (Option option : options){
            optionDTOs.add(OptionMapper.EntityToDTO(option));
        }
        return optionDTOs;
    }


}

