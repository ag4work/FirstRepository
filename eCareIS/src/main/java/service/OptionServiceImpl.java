package service;

import DAO.OptionDAO;
import DAO.OptionDAOImpl;
import entity.Option;
import service.DTO.OptionDTO;
import utils.EntityManagerFactorySingleton;
import utils.Mappers.OptionMapper;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexey on 04.07.2015.
 */
public class OptionServiceImpl implements OptionService {

    OptionDAO optionDAO = new OptionDAOImpl(EntityManagerFactorySingleton.getInstance());

    @Override
    public OptionDTO getOptionById(Integer optionId) {
        if (optionId==null) return null;
        OptionDTO optionDTO = OptionMapper.EntityToDTOWithSet(
                optionDAO.get(optionId));
        return optionDTO;
    }

    @Override
    public Set<OptionDTO> getAllOptions() {
        Set<Option> options = new HashSet<Option>(optionDAO.getAll());
        return OptionMapper.EntitySetToDTOSet(options);
    }

    @Override
    public void addOption(OptionDTO optionDTO) {
        optionDAO.add(OptionMapper.DTOToEntity(optionDTO));
    }






}
