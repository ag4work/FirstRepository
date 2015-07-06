package service;

import DAO.*;
import entity.Contract;
import entity.Option;
import entity.Tariff;
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
        Option option = optionDAO.get(optionId);
        Tariff tariff = tariffDAO.get(tariffId);
        tariff.getPossibleOption().add(option);
        tariffDAO.update(tariff);
    }

    @Override
    public void removeOptionAsPossibleForTariff(Integer tariffId, Integer optionId) {
        Option option = optionDAO.get(optionId);
        Tariff tariff = tariffDAO.get(tariffId);
        tariff.getPossibleOption().remove(option);
        tariffDAO.update(tariff);
    }

    @Override
    public void removeTariffAndMoveContractsToBaseTariff(Integer tariffId) {
        //todo make a check it is now a base tariff!
        Tariff tariff = tariffDAO.get(tariffId);
        Set<Contract> contractsHasThisTariff = tariff.getContractHasThisTariff();
        tariffDAO.delete(tariffId);
        for (Contract contract : contractsHasThisTariff) {
            contract.setTariff(tariffDAO.get(Constants.DEFAULT_TARIFF_ID));
            contractDAO.update(contract);
        }
    }
}
