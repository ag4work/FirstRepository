package utils.Mappers;

import entity.Option;
import service.DTO.OptionDTO;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexey on 03.07.2015.
 */
public class OptionMapper {


    private OptionMapper() {
    }

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

    public static OptionDTO EntityToDTOWithSet(Option option){
        if (option == null) return null;
        OptionDTO optionDTO = EntityToDTO(option);
        optionDTO.setDependentOption(OptionMapper.EntitySetToDTOSet(
                option.getDependentOption()));
        optionDTO.setInconsistentOption(OptionMapper.EntitySetToDTOSet(
                option.getInconsistentOption()));
        optionDTO.setRequiredOption(OptionMapper.EntitySetToDTOSet(
                option.getRequiredOption()));
        return optionDTO;

    }


    public static Set<Option> DTOSetToEntitySet(Set<OptionDTO> optionDTOs){
        if (optionDTOs == null) return null;
        Set<Option> options = new HashSet<Option>();
        for (OptionDTO optionDTO : optionDTOs){
            options.add(OptionMapper.DTOToEntity(optionDTO));
        }
        return options;
    }

    public static Set<OptionDTO> EntitySetToDTOSet(Set<Option> options){
        if (options==null) return Collections.EMPTY_SET;
        Set<OptionDTO> optionDTOs = new HashSet<OptionDTO>();
        for (Option option : options){
            optionDTOs.add(OptionMapper.EntityToDTO(option));
        }
        return optionDTOs;
    }

    public static Set<OptionDTO> EntitySetToDTOSetWithSets(Set<Option> options){
        if (options==null) return null;
        Set<OptionDTO> optionDTOs = new HashSet<OptionDTO>();
        for (Option option : options){
            optionDTOs.add(OptionMapper.EntityToDTOWithSet(option));
        }
        return optionDTOs;
    }




}

