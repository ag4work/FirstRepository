package service;

import org.springframework.stereotype.Service;
import service.DTO.ContractDTO;
import service.DTO.TariffDTO;

import java.util.Set;

/**
 * Created by Alexey on 04.07.2015.
 */
@Service
public interface TariffService {
    /**
     * returna a TariffDTO by userID
     * @param tariffId tariffId
     * @return TariffDTO
     */
    public TariffDTO getTariffById(Integer tariffId);

    /**
     * returns all tariffs
     * @return set of TariffDTO
     */
    public Set<TariffDTO> getAllTariffs();

    /**
     * Add new tariff
     * @param tariffDTO  tariffDTO
     */
    public void addTariff(TariffDTO tariffDTO);

    /**
     * Adds the options and all it's required tree to the tariff
     * @param tariffId  tariffId
     * @param optionId  optionId
     */
    public void addOptionAsPossibleForTariff(Integer tariffId, Integer optionId);

    /**
     * Remove the tariff and move it's contracts to "Base" tariff
     * @param tariffId tariffId
     */
    void removeTariffAndMoveContractsToBaseTariff(Integer tariffId);

    /**
     * Remove the options and all it's required tree from the tariff
     * @param tariffId tariffId
     * @param optionId optionId
     */
    public void removeOptionAndAllDependentOptionsTreeAsPossibleForTariff(
            Integer tariffId, Integer optionId);
}
