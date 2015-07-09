package service;

import DAO.*;
import entity.Contract;
import entity.Option;
import entity.Tariff;
import org.apache.log4j.Logger;
import service.DTO.OptionDTO;
import service.DTO.TariffDTO;
import utils.Constants;
import utils.EntityManagerFactorySingleton;
import utils.Mappers.TariffMapper;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexey on 04.07.2015.
 */
public class TariffServiceImpl implements TariffService{

    TariffDAO tariffDAO = new TariffDAOImpl(EntityManagerFactorySingleton.getInstance());
    OptionDAO optionDAO = new OptionDAOImpl(EntityManagerFactorySingleton.getInstance());
    ContractDAO contractDAO = new ContractDAOImpl(EntityManagerFactorySingleton.getInstance());
    OptionService optionService = new OptionServiceImpl();
    Logger logger = Logger.getLogger(TariffServiceImpl.class);

    @Override
    public TariffDTO getTariffById(Integer tariffId) {
        return TariffMapper.EntityToDTOWithSets(tariffDAO.get(tariffId));
    }

    @Override
    public Set<TariffDTO> getAllTariffs() {
        Set<TariffDTO> tariffDTOs = new HashSet<TariffDTO>();
        for (Tariff tariff : tariffDAO.getAll()) {
            tariffDTOs.add( TariffMapper.EntityToDTOWithSets(tariff));
        }
        return tariffDTOs;
    }

    @Override
    public void addTariff(TariffDTO tariffDTO) {
        if (tariffDTO==null) return;
        tariffDAO.add(TariffMapper.DTOToEntity(tariffDTO));
    }

    @Override
    public void addOptionAsPossibleForTariff(Integer tariffId, Integer optionId) {
        Set<OptionDTO> allRequiredOptionTree = optionService.getRequiredOptionTree(optionId);
        Tariff tariff = tariffDAO.get(tariffId);
        for (OptionDTO requiredOption : allRequiredOptionTree){
            tariff.getPossibleOption().add(optionDAO.get(requiredOption.getOptionId()));
        }
        tariffDAO.update(tariff);
    }



    @Override
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
    public void removeTariffAndMoveContractsToBaseTariff(Integer tariffId) {
        if (tariffId==Constants.DEFAULT_TARIFF_ID){
            logger.warn("Attempt to delete default tariff");
            return;
        }
        Tariff tariff = tariffDAO.get(tariffId);
        Set<Contract> contractsHasThisTariff = tariff.getContractHasThisTariff();
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
