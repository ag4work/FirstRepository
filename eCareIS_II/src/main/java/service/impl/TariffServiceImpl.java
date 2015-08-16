package service.impl;

import dao.*;
import entity.Contract;
import entity.Option;
import entity.Tariff;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.DTO.OptionDTO;
import service.DTO.TariffDTO;
import service.OptionService;
import service.TariffService;
import utils.Constants;
import utils.Mappers.TariffMapper;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexey on 04.07.2015.
 */
@Service
public class TariffServiceImpl implements TariffService {
    @Autowired
    private TariffDAO tariffDAO;
    @Autowired
    private OptionDAO optionDAO;
    @Autowired
    private ContractDAO contractDAO;
    @Autowired
    private OptionService optionService;

    private Logger logger = Logger.getLogger(TariffServiceImpl.class);

    @Override
    @Transactional
    public TariffDTO getTariffById(Integer tariffId) {
        return TariffMapper.EntityToDTOWithSets(tariffDAO.get(tariffId));
    }

    @Override
    @Transactional
    public Set<TariffDTO> getAllTariffs() {
        Set<TariffDTO> tariffDTOs = new HashSet<TariffDTO>();
        for (Tariff tariff : tariffDAO.getAll()) {
            tariffDTOs.add( TariffMapper.EntityToDTOWithSets(tariff));
        }
        return tariffDTOs;
    }

    @Override
    @Transactional
    public void addTariff(TariffDTO tariffDTO) {
        if (tariffDTO==null) return;
        tariffDAO.add(TariffMapper.DTOToEntity(tariffDTO));
    }

    @Override
    @Transactional
    public void addOptionAsPossibleForTariff(Integer tariffId, Integer optionId) {
        Set<OptionDTO> allRequiredOptionTree = optionService.getRequiredOptionTree(optionId);
        Tariff tariff = tariffDAO.get(tariffId);
        for (OptionDTO requiredOption : allRequiredOptionTree){
            tariff.getPossibleOption().add(optionDAO.get(requiredOption.getOptionId()));
        }
        tariffDAO.update(tariff);
    }



    @Override
    @Transactional
    public void removeOptionAndAllDependentOptionsTreeAsPossibleForTariff(
            Integer tariffId, Integer optionId) {

        Set<OptionDTO> allDependentOptionTree = optionService.getDependentOptionTree(optionId);
        Tariff tariff = tariffDAO.get(tariffId);
        for (OptionDTO dependentOption : allDependentOptionTree){
            tariff.getPossibleOption().remove(optionDAO.get(dependentOption.getOptionId()));
        }
        tariffDAO.update(tariff);
    }

    @Override
    @Transactional
    public void removeTariffAndMoveContractsToBaseTariff(Integer tariffId) {
        if (tariffId==Constants.DEFAULT_TARIFF_ID){
            logger.warn("Attempt to delete default tariff");
            return;
        }
        Set<Contract> contractsHasThisTariff = tariffDAO.getContractsByTariff(tariffId);
        tariffDAO.delete(tariffId);
        for (Contract contract : contractsHasThisTariff) {
            contract.setTariff(tariffDAO.get(Constants.DEFAULT_TARIFF_ID));
            // TODO figure out - may be  just "=null" ?
            Set<Option> contractOptions = new HashSet<Option>(contract.getChosenOption());
            contract.getChosenOption().removeAll(contractOptions);
            contractDAO.update(contract);
        }
    }


}
