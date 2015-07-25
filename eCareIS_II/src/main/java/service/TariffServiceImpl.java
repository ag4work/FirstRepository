package service;

import DAO.*;
import entity.Contract;
import entity.Option;
import entity.Tariff;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.DTO.OptionDTO;
import service.DTO.TariffDTO;
import utils.Constants;
import utils.Mappers.TariffMapper;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexey on 04.07.2015.
 */
@Service
public class TariffServiceImpl implements TariffService{
    @Autowired
    TariffDAO tariffDAO;
    @Autowired
    OptionDAO optionDAO;
    @Autowired
    ContractDAO contractDAO;
    @Autowired
    OptionService optionService;

    Logger logger = Logger.getLogger(TariffServiceImpl.class);

//    private TariffServiceImpl() {
//    }
//
//    private static class LazyHolder{
//        public static final TariffServiceImpl INSTANCE = new TariffServiceImpl();
//    }
//
//    public static TariffServiceImpl getInstance(){
//        return LazyHolder.INSTANCE;
//    }



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