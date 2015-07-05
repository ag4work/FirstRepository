package service;

import DAO.OptionDAO;
import DAO.OptionDAOImpl;
import DAO.TariffDAO;
import DAO.TariffDAOImpl;
import entity.Option;
import entity.Tariff;
import service.DTO.TariffDTO;
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
}
