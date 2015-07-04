package service;

import GenericBasedDAO.TariffDAO;
import GenericBasedDAO.TariffDAOImpl;
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

    @Override
    public TariffDTO getTariffById(Integer tariffId) {
        return TariffMapper.EntityToDTO(tariffDAO.get(tariffId));
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
}
