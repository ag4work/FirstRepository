package service;

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

//    public TariffDTO getTariffById(Integer tariffId);
//    public Set<TariffDTO> getAllTariffs();
//    public void addTariff(TariffDTO tariffDTO);
////    public void removeTariff(Integer tariffId);

}
